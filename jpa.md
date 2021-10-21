###entity
* @builder 사용시 생성자 에러발생</br>
/ @Builder는 기본적으로 @AllArgConstructor를 사용하는데 다른 생성자를 명시적으로 사용한게 원인</br>
/ @AllArgConstructor 추가해서 해결

* @ToString 사용시 stack overflow 에러발생</br>
/ 연관관계를 가진 필드가 양방향으로 있는 경우 @ToString 사용하면 순환참조가 발생하는게 원인</br>
/ @ToString시 연관관계를 가진 필드는 제외해준다.

###jpa
* 1:N인 연관관계에서 1인 엔티티에서 fetch join 사용시 pagination까지 해야한다면 
countQuery를 사용한다. 이때 application level에서 pagination을 하기때문에 
경고문구가 발생한다.</br>
/ fetch join 대신 join을 사용하고 지연로딩으로 프록시객체를
가져온다. 이때 N+1문제를 해결하기위해서 개별설정인 @BatchSize나 전역설정인 
spring.jpa.hibernate.default_batch_fetch_size를 설정하면 설정한 개수만큼
프록시객체를 한번에 조회해준다.