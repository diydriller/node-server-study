# NGINX
## 1. process 
* worker process의 수는
cpu 코어수로 설정하면 병렬성을 최대로 이용할 수 있다.
또한 worker process를 cpu에 바인딩한다면 process간의 context switch 비용을
절감할 수 있다.
```shell
    worker_processes 4;
    events {
        worker_connections 768;
    }
    # 최대 동시 접속자수 = worker_connections * worker_processes
          
    worker_cpu_affinity 1000 0100 0010 0001;
    # worker process와 cpu 바인딩
```
## 2. proxy 
* keepalive 설정을 통해 connection을 유지할 수 있다. 
```shell
    http{
        # 10초 동안 connection 유지
        keepalive_timeout 60;
        # 1개의 connection으로 처리될 최대 요청 수 
        keepalive_requests 50;
        
        upstream backend_server {
          server http://127.0.0.1:8080
          # connection 개수
          keepalive 16;
        }
        
        server {
          listen 80;
          location / {
            proxy_pass http://backend_server;
            proxy_http_version 1.1;
            proxy_set_header Connection "";
          }
        }
    }
```
* 파일을 주고 받는다면 최대 크기 설정을 해야한다.
```shell
    client_max_body_size 10M;
    # 클라이언트 버퍼 크기 
    client_body_buffer_size 1m;
```

* 캐시 설정
```shell
    # 캐시 데이터 저장경로 , 파일저장방식 , 키를 저장하는 메모리공간의 이름과 크기
    # 캐시 데이터 저장공간의 최대크기(초과시 LRU 적용) , 만료시간(초과시 삭제)  
    proxy_cache_path /var/www/cache/ levels=1:2 keys_zone=cache_zone:100m \ 
    max_size=10g inactive=30d;
    # 캐시 키
    proxy_cache_key "$scheme$proxy_host$request_uri$remote_addr";
    # 캐시를 사용할 HTTP 메서드
    proxy_cache_methods GET POST;
    # 캐시 유효시간(응답코드별로 시간지정)
    proxy_cache_valid 200 6m
    # 응답 헤더에 캐시 상태 추가
    add_header X-Proxy-Cache $upstream_cache_status
    
```

* 버퍼링
```shell
    # 응답 데이터를 클라이언트에게 바로 보내지 않고 메모리에 저장한다.
    proxy_buffering on;
    # 버퍼개수와 버퍼크기 
    proxy_buffers 256 16k;
```