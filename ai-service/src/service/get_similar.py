from src.similarity.similarity import find_similar_images
from src.database.connection import get_image_embedding


def get_similar(img_id):
  query_embedding = get_image_embedding(img_id)
  similar = find_similar_images(query_embedding)
  return similar