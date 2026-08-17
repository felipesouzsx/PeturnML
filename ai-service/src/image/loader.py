from PIL import Image
import numpy, torch


def load_image(image_path):
    return Image.open(image_path).convert("RGB")


def load_images(image_paths):
    images = []

    for image_path in image_paths:
        image = load_image(image_path)
        images.append(image)

    return images


def tensor_from_bytes(embedding_bytes):
    tensor = torch.from_numpy(
        numpy.frombuffer(embedding_bytes, dtype=numpy.float32).copy()
    )
    return tensor