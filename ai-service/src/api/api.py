from fastapi import FastAPI, File, Form, UploadFile
from src.service.get_similar import get_similar
from src.service.add_image import add_image_from_bytes


app = FastAPI()


@app.get("/")
async def home():
  return { "message": "Online" }


@app.post("/embedding")
async def create_embedding(image: UploadFile = File(...)):
  img_bytes = await image.read()
  img_id = add_image_from_bytes(img_bytes, image.filename)
  return { "img_id": img_id }


@app.get("/similar")
async def find_similar(id: str):
  return get_similar(id)