## apach란
* 웹서버로 클라이언트의 요청마다 프로세스 또는 스레드를 생성해서 처리한다. 하지만 요청이 
많아질수록 서버의 부하가 커지게 된다.

## nginx란
* 웹서버로 event driven 기반 비동기방식을 사용하기때문에 가벼운 작업은 먼저 처리하는 식으로
여러개의 요청을 효과적으로 처리할 수 있다.
* master process는 설정파일을 읽고 worker process들을 관리한다.
worker process는 요청이 들어오면 처리하는 역할을 한다. worker process의 수는
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
* reverse proxy 서버로 사용되어 클라이언트의 요청을 was로 분배하는 load balancing 역할을
한다.