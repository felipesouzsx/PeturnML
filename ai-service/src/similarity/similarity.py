import torch, numpy, torch.nn.functional as functional_nn
from src.model.clip_model import encode_image, preprocess_image, device
from src.database.connection import get_images_bytes
from src.image.loader import tensor_from_bytes


def get_embedding(image):
  # Adiciona a dimensão do batch e move a imagem para o dispositivo de processamento
  processed_img = preprocess_image(image).unsqueeze(0).to(device)
  # Desativa cálculo de gradiente, e otimiza a execução para inferência
  with torch.inference_mode():
    embedding = encode_image(processed_img)
    # Normaliza o embedding para que seu vetor tenha norma 1.
    # `dim = -1` indica o último elemento do vetor.
    embedding = functional_nn.normalize(embedding, dim = -1)
  return embedding


def find_similar_images(query_embedding):
  db_data = get_images_bytes(page = 0)
  db_image_embeddings = []
  db_image_data = []

  for data in db_data:
    img_id, img_name, img_embedding_bytes = data
    # Convertendo de bytes pra um Tensor
    img_embedding = tensor_from_bytes(img_embedding_bytes)
    db_image_embeddings.append(img_embedding)
    db_image_data.append({'id': img_id, 'name': img_name})
    
  # Junta os embeddings em um único tensor
  db_image_embeddings = torch.stack(db_image_embeddings)

  similarities = functional_nn.cosine_similarity(
    query_embedding,
    db_image_embeddings,
    dim=1
  )

  # Pega os 10 tensores com similaridade mais alta.
  k = min(10, similarities.numel())
  values, indices = torch.topk(similarities, k=k)

  results = []
  for value, index in zip(values, indices):
    img_data = db_image_data[index.item()]
    results.append({"id": img_data['id'], "name": img_data['name'], "similarity": value.item()})

  return results