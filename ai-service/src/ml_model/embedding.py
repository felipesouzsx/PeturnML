import torch, torch.nn.functional as functional_nn
from src.ml_model.clip_model import encode_image, preprocess_image, device


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