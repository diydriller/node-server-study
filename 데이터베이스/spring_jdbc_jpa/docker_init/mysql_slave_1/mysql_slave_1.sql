CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;
CREATE TABLE IF NOT EXISTS testtable(
    id INT,
    PRIMARY KEY(id)
);

SET GLOBAL server_id=4;
SHOW variables LIKE 'server_id';

CHANGE MASTER TO MASTER_HOST='docker_mysql_master' , MASTER_USER='root' , MASTER_LOG_FILE='mysql-dbin.000003' , MASTER_LOG_POS=157;
START SLAVE;



