from src.service.get_similar import get_similar
import src.logger as log


def main():
  img_id = "4e4407fe-0029-4d1a-98d6-c91d17d7b049"
  results = get_similar(img_id)

  for result in results:
    log.info(f"similarity: {result['similarity']}\t\tname: {result['name']}", indent=1)
  

if __name__ == "__main__":
  log.info(f"Testing `get_similar` Service")
  main()