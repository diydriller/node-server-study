CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;
CREATE TABLE IF NOT EXISTS testtable(
    id INT,
    PRIMARY KEY(id)
);
SET GLOBAL server_id=3;
SHOW variables LIKE 'server_id';