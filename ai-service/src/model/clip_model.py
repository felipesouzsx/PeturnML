## Script que define o modelo que será usado
import open_clip, torch


device = "cuda" if torch.cuda.is_available() else "cpu"
model_bame = "ViT-B-32"
# O segundo valor de retorno é uma função de pré-processamento para treinamento.
# Como não é meu caso, descarto o valor.
model, _, preprocess = open_clip.create_model_and_transforms(
  model_bame, 
  quick_gelu=True,
  pretrained="openai"
)
model = model.to(device)
model.eval()


def encode_images(images):
  return model.encode_image(images)


def encode_image(image):
  return model.encode_image(image)


def preprocess_image(image_bytes):
  return preprocess(image_bytes)