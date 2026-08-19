from fastapi import FastAPI, File, UploadFile, HTTPException
import src.service.embedding as embedding 
import src.logger as logger, time


app = FastAPI()


@app.post("/embedding")
async def create_embedding(image: UploadFile = File(...)):
  logger.info("Creating image embedding")
  start = time.perf_counter()

  img_bytes = await image.read()
  img_embedding = embedding.from_image(img_bytes)

  end = time.perf_counter()
  logger.info(f"Created [{(end - start) * 1000:.2f}ms]")
  return { "embedding": img_embedding }