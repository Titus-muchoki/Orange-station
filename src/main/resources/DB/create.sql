CREATE DATABASE garage;
\c garage;
CREATE TABLE clients(id SERIAL PRIMARY KEY, name VARCHAR, email VARCHAR, tel VARCHAR, car VARCHAR, serviceid VARCHAR);