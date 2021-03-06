DROP TABLE authors IF EXISTS cascade;
DROP TABLE books IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;
DROP SEQUENCE global_seq1 IF EXISTS;


CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;
CREATE SEQUENCE GLOBAL_SEQ1 AS INTEGER START WITH 100000;

CREATE TABLE authors
(
  id               INTEGER  GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name             VARCHAR(255) NOT NULL,
);

CREATE UNIQUE INDEX authors_unique_name_idx ON authors(name);

CREATE TABLE books
(
  id          INTEGER GENERATED BY DEFAULT AS sequence GLOBAL_SEQ1 PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  author_id     INTEGER      NOT NULL,
  FOREIGN KEY (author_id) REFERENCES authors (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX books_unique_name_author_id_idx ON books (name, author_id)