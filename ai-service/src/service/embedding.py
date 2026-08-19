from src.ml_model.embedding import get_embedding
import numpy
from PIL import Image
from io import BytesIO


def from_image(img_bytes):
  img = Image.open(BytesIO(img_bytes)).convert("RGB")
  embedding = get_image_embedding(img)
  return embedding


def get_image_embedding(image):
  embedding = get_embedding(image)
  # Usando squeeze para retirar uma dimensão do array
  result = embedding.cpu().numpy().astype(numpy.float32).squeeze(0).tolist()
  return result