INDENT_SIZE = 4


def log(msg, indent=0):
  print(" " * indent * INDENT_SIZE + f"{msg}")


def error(msg, indent = 0):
  log(f"[ERROR] {msg}", indent)


def info(msg, indent = 0):
  log(f"[INFO] {msg}", indent)