import sqlite3
from src.image.loader import tensor_from_bytes


PAGE_SIZE = 32


def connect():
  return sqlite3.connect("database.db")


def count_pages():
  connection = connect()
  total = 0
  try:
    cursor = connection.cursor()
    cursor.execute("SELECT COUNT(*) FROM imagens")
    total = cursor.fetchone()[0]
  finally:
    connection.close()
  return (total + PAGE_SIZE - 1)


def create_table():
  connection = connect()
  try:
    cursor = connection.cursor()
    cursor.execute("""
    CREATE TABLE IF NOT EXISTS images (
      id TEXT PRIMARY KEY,
      name TEXT,
      embedding BLOB NOT NULL
    );
    """)
    connection.commit()
  finally:
    connection.close()


def save_image_embedding_bytes(id, name, embedding_bytes):
  connection = connect()
  try:
    cursor = connection.cursor()
    cursor.execute(
      """INSERT INTO images (id, name, embedding) VALUES (?, ?, ?)""",
      (id, name, embedding_bytes)
    )
    connection.commit()
  finally:
    connection.close()


def get_images_bytes(page):
  connection = connect()
  offset = (page - 1) * PAGE_SIZE
  images = []
  try:
    cursor = connection.cursor()
    cursor.execute("""
      SELECT id, name, embedding FROM images ORDER BY id LIMIT ? OFFSET ?;
    """, (PAGE_SIZE, offset))
    images = cursor.fetchall()
  finally:
    connection.close()
  return images


def get_image_embedding(id):
  connection = connect()
  try:
    cursor = connection.cursor()
    cursor.execute('''
      SELECT embedding FROM images WHERE id = ?;
    ''', (id,))
    results = cursor.fetchall()
    return tensor_from_bytes(results[0][0])
  finally:
    connection.close()


create_table()