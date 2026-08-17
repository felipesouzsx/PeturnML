from src.service.add_image import *
import src.image.loader as loader
import os
import src.logger as logger


def main():
  with os.scandir('tests/images/') as entries:
    for entry in entries:
      if entry.is_dir(): continue
      if entry.name.endswith('.gitignore'): continue
      logger.info(f"Entry: {entry.name}", 1)
      add_image(loader.load_image(entry.path), name=entry.name)


if __name__ == "__main__":
  logger.info(f"Testing `add_image` Service")
  main()