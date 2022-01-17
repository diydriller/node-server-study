# JPA

## 1. 엔티티

* @Builder는 기본적으로 @AllArgConstructor를 사용하기 때문에 다른 생성자를 사용시 에러가
발생한다. @AllArgConstructor를 추가해서 해결한다.

* @ToString 사용시 연관관계를 가진 필드가 양방향으로 매핑되어있는 경우 순환참조가 발생해서
stackoverflow 에러가 발생한다. 양방향으로 매핑된 연관관계를 가진 필드는 제외해서 해결한다. 

* 엔티티 객체에서 프록시가 들어있는 필드로 로직을 수행할 경우 에러가 발생할 수 있기때문에
dto로 변환해서 사용한다.

## 2. 데이터베이스

* ### 연결
* h2 연결시 처음에 embedded모드로 jdbc:h2:~/database명으로 database를 생성하고
이후로 server모드로 jdbc:h2:tcp://localhost/~/database명으로 접속한다.

* ### 사용 
* collection fetch join 사용시 pagination까지 한다면 데이터를 전부 가져와서
application level에서 limit을 적용하기때문에 부하가 심해진다.
이를 해결하기위해서 지연로딩을 사용하고 N+1문제를 해결하기위해서 개별설정인 
@BatchSize나 전역설정인 spring.jpa.hibernate.default_batch_fetch_size를 
설정하면 설정한 개수만큼 한번에 조회해준다.  



