# Redis

## 1. 정의
* key와 value로 데이터를 저장하는 메모리 DB이다.

## 2. 자료구조
* List는 LinkedList 형태로 데이터가 저장된다. 
```shell
    # 마지막에 추가
    rpush mylist A
    # 처음에 추가
    lpush mylist C
    # 마지막 제거
    rpop mylist
    # 처음 제거
    lpop mylist
    # 조회
    lrange mylist 0 -1
```
* Set은 순서가 보장되지 않는 집합 형태로 데이터가 저장된다.
```shell
    # 1~3 추가
    sadd myset 1 2 3 
    # 조회
    smembers myset
```
* SortedSet은 정렬되어서 집합형태로 데이터로 저장된다.
```shell
    # score와 value를 이용해 저장하고 score를 기준으로 정렬되어서 저장된다.
    zadd mysortedset 10 B
    # 조회
    zrange mysortedset 0 -1
    
```
* Hash는 key와 value로 데이터를 저장한다.
```shell
    # key에 field 와 value를 저장한다. 
    hset myhash user hyun
```
* 싱글스레드로 redis의 자료구조는 동기화를 보장한다.

## 3. 내부동작
* 싱글스레드로 동작한다.
* 이벤트루프기반 비동기 논블로킹 방식으로 명령어를 처리한다.
백그라운드에서는 스레드풀을 사용한다.

## 4. 백업
* 첫번째로 AOF(Append Only File)방식으로 조회명령을 제외한 명령어가 실행될때마다 
파일에 기록된다. rewrite 기능을 사용하면 특정시점에 로그파일의 데이터를 다시 써서
파일의 크기를 줄일 수 있다.

## 5. sentinel
* master와 slave 간 데이터 동기화를 비동기로 진행한다.
* sentinel은 master를 감시하며 sentinel간 투표를 통해 master의 장애여부를 판단하며 
장애판단시 slave를 자동으로 master로 승격시킨다.
* WAS에서 승격된 master를 사용하기위해서 HA proxy를 사용한다.

## 6. cluster
* 서버 여러개를 수평적으로 구성하고 hash sharding을 통해 데이터를 저장한다. 
master에 장애발생시 slave를 자동으로 master로 승격시킨다.
* master와 slave 간 데이터 동기화를 비동기로 진행한다.

## 인터뷰
### redis가 싱글스레드임에도 빠른 이유는?
* 싱글스레드이므로 context switch 오버헤드가 절약되고 이벤트 루프 방식으로
동시성을 구현하였다. 
* 병목현상의 원인이 CPU 작업에 있지 않다.
### memcached와의 차이는?
* memcached는 자료구조로 string을 지원하는 반면 redis는 string , hash , set ,
sorted set , list를 지원한다.
* memcached는 멀티스레드이고 redis는 싱글스레드이다.
* memcached는 데이터를 메모리에만 저장하는 반면 redis는 데이터를 메모리와 
disk에 저장할 수 있어서 서버가 shutdown이 되어도 복구할 수 있다.
복구방식에는 순간적으로 메모리의 데이터를 디스크로 저장하는 RDB방식과 
매 operation마다 로그파일에 연산을 기록하는 AOF방식이 있다.