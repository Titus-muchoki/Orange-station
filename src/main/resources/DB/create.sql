CREATE DATABASE garage;
\c garage;
CREATE TABLE clients(id SERIAL PRIMARY KEY, name VARCHAR, email VARCHAR, tel VARCHAR, car VARCHAR, serviceid INTEGER);
CREATE TABLE services(id SERIAL PRIMARY KEY, name VARCHAR);
CREATE TABLE mechanics(id SERIAL PRIMARY KEY, name VARCHAR, charges VARCHAR, clientid INTEGER);
CREATE DATABASE garage_test WITH TEMPLATE garage;