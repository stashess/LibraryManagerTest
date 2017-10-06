DELETE FROM books;
DELETE FROM authors;
ALTER SEQUENCE global_seq RESTART WITH 100001;
ALTER SEQUENCE global_seq1 RESTART WITH 100001;

INSERT INTO authors (name) VALUES
('George R.R. Martin '),
('J.R.R. Tolkien '),
('Patrick Rothfuss'),
('C.S. Lewis'),
('Robert Jordan'),
('Stephen King ');

INSERT INTO books (name, author_id) VALUES
  ('A Game of Thrones',100001),
  ('The Hobbit',100002),
  ('The Name of the Wind',100003),
  ('The Chronicles of Narnia',100004),
  ('The Eye of the World',100005),
  ('The Gunslinger',100006),
  ('The Lord of the Rings',100002),
  ('Gardens of the Moon',100006);
