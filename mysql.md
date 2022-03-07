# MySql
## 1. Replication 
* 바이너리 로그 기반 복제 방식을 사용할때 
slave server의 server_id를 master server와 다르게 지정해주고 slave 설정을 해준다.
```shell
  # master host , user , 로그파일 , 로그위치
  CHANGE MASTER TO MASTER_HOST='docker_mysql_master' , MASTER_USER='root' , MASTER_LOG_FILE='mysql-dbin.000003' , MASTER_LOG_POS=157;
  START SLAVE;
```


