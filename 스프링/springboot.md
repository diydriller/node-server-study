# Springboot
## 1. 정의  
* 최소한의 설정으로 spring을 사용할 수 있게 해주는 framework이다.
이를 위해서 springboot는 3가지 특징 자동설정 , starter 라이브러리 , 내장서버를 
가지고 있다.
* 자동설정은 @EnableAutoConfiguration이라는 어노테이션을 사용하면 
spring.factories라는 파일에 등록된 자동설정클래스들을 bean객체로 등록해준다.
* starter 라이브러리를 사용하면 의존관계에 있는 다른 라이브러리들을 버전에 맞게
자동으로 가져와준다.
* 내장서버를 가지고 있기때문에 배포를 쉽게 할 수 있다.

## 2. DTO(Data Transfer Object) , VO(Value Object) , Entity
* DTO는 계층간 데이터 교환을 하기위한 객체이다.
* VO는 값을 표현하는 객체이다.
* Entity는 DB table과 매핑되는 객체로 Id를 이용해서 구분한다.
도메인 로직을 포함한다.
* Entity와 DTO는 관심사의 분리라는 관점에서 분리해야한다. 


