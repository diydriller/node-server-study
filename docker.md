# Docker
## 1. container
* container는 host os와 별도의 가상환경이므로 localhost를 사용해서 통신하면
오류가 발생한다. docker.for.win.localhost 또는 docker.for.mac.localhost 또는
host.docker.internal 또는 서비스이름 또는 해당 ip주소를 사용한다.
```shell
    # network 조회
    docker network ls 
    docker network inspect {NETWORK_NAME}
```
## 2. volume
* 권한문제가 생길경우 권한을 설정해주고 파일보다는 폴더로 지정해준다.
권한설정시 쓰기모드와 실행모드를 허용해주면 보안문제로 ignore 될 수 있으므로
읽기모드만 허용해준다.
