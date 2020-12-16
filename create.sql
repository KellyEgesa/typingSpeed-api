CREATE DATABASE typing_speed;
\c typing_speed;
CREATE TABLE users (id SERIAL PRIMARY KEY, userName VARCHAR,email VARCHAR, password VARCHAR,wordsPerMinute INTEGER, typingProficiency VARCHAR);
CREATE DATABASE typing_speed_test WITH TEMPLATE typing_speed;