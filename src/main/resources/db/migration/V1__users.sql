create table users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  enabled VARCHAR NOT NULL,
  role VARCHAR NOT NULL,
  CONSTRAINT unique_username UNIQUE (username)
);
CREATE INDEX username_index_users ON users(username);