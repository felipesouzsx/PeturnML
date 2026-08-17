from src.similarity.similarity import get_embedding
from src.database.connection import save_image_embedding_bytes
import numpy, uuid
from PIL import Image
from io import BytesIO


def add_image_from_bytes(img_bytes, name):
  img = Image.open(BytesIO(img_bytes)).convert("RGB")
  return add_image(img, name)


def add_image(image, name):
  img_id = str(uuid.uuid4())
  embedding = get_embedding(image)
  embedding_bytes = embedding.cpu().numpy().astype(numpy.float32).tobytes()
  save_image_embedding_bytes(
    id=img_id,
    name=name,
    embedding_bytes=embedding_bytes
  )
  return img_id