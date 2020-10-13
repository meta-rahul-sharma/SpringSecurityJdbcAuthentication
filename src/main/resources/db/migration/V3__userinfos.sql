create TABLE userinfos(
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES users(id),
  first_name VARCHAR,
  middle_name VARCHAR,
  last_name VARCHAR,
  email VARCHAR,
  mobile_number VARCHAR
);

insert INTO userinfos(user_id, first_name, middle_name, last_name, email, mobile_number) VALUES (1,'First', 'Middle', 'User', 'itsUser@gmail.com', '8909890899');
insert INTO userinfos(user_id, first_name, middle_name, last_name, email, mobile_number) VALUES (2,'First', 'Middle', 'Admin', 'admin@gmail.com', '9909890899');