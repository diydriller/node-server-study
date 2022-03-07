# Spring 
## 1. 정의
* spring은 엔터프라이즈 애플리케이션을 보다 쉽게 만들어주는 framework이다.
### 3대 철학 
* spring은 3가지 개념 IOC(Inversion Of Control) , AOP(Aspect Of Programming) , 
PSA(Portable Service Abstraction)을 가지고 있다.
* IOC란 객체의 생명주기를 외부에서 관리한다는 의미이다. 스프링 컨테이너는 bean객체들을 
싱글톤으로 관리하며 의존관계를 자동으로 주입해준다. 이를 통해 객체간 결합도가 낮아지기
때문에 코드의 재사용성을 높아지고 단위테스트가 용이해진다.
* AOP란 관점지향프로그래밍으로 로직을 관점별로 나누어서 모듈화하는것을 말한다. 이를 통해
코드의 재사용성을 높일 수 있다.
* PSA란 추상화 계층을 사용하는 것으로 기술을 내부적으로 숨기고 개발자에게 편의성을 제공하는
것을 말한다.


  
## 2. Bean
* 스프링 컨테이너에서 싱글톤으로 관리하는 객체를 bean이라고 한다.
### 생명주기
1) 스프링컨테이너 생성 
2) 스프링 빈 생성 
3) 의존관계 주입 
4) 초기화 콜백 
5) 사용 
6) 소멸전 콜백 
7) 스프링 종료
### 의존성 주입
* 생성자 주입방법은 생성자가 호출되는 시점에 의존관계에 있는 bean을 
주입받게 되므로 순환참조 에러를 발견하기 쉽다.


## 인터뷰
### Filter와 Interceptor 차이?
* filter는 dispatcher servlet으로 가기전 호출되고 interceptor는 controller로 가기전 호출된다.

### @Controller @Service @Repository 차이?
* spring에서 Application Context에 bean을 등록할때 @Component 어노테이션을
  클래스에 붙여준다.
* @Controller는 MVC 모델에서 Controller 역할을 하는 어노테이션으로 Dispatcher
  Servlet에서 @Controller 어노테이션이 붙은 클래스를 스캔한다.
* @Service는 business layer를 나타내는 어노테이션이다.
* @Repository는 data access layer를 나타내는 어노테이션으로 spring은 platform에 의존적인
  exception을 spring의 공통화된 unchecked exception인 DataAccessException으로 변환해준다.
  이를 위해서 PersistenceExceptionTranslationPostProcessor를 Application Context에
  등록해줘야한다.
```java
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
```