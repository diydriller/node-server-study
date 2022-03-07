# JPA


## 1. 정의 
* 객체를 테이블에 자동으로 매핑시켜주는 ORM을 사용하기 위한 인터페이스이다.
* EntityManger는 영속성 컨텍스트를 통해 쿼리를 자동으로 생성해서 보내준다.
### 영속성 
* 1차 캐시로 영속성 컨텍스트에 있는 객체라면 데이터베이스를 거치지않고 가져올 수 있다.
* 트랜잭션에서 쓰기지연을 지원해서 commit하는 순간 모아두었던 쿼리문을 보낸다.
* dirty check 기능을 통해 객체의 변화를 감지하면 자동으로 update 쿼리를 생성해서 보낸다.
### EntityManager
* EntityManagerFactory에서 클라이언트 요청으로 스레드가 생성될때마다 EntityManager를 생성하고
사용후 종료한다.
* EntityManager는 실제 트랜잭션 단위를 수행할때 사용되며 thread간 공유되어서는 안된다.
update 작업시 반드시 트랜잭션 안에서 이루어져야한다.


## 2. 엔티티
### 기본키
* Identity 전략은 기본키 생성을 DB에 위임하는 전략으로 em.persist() 호출시
쿼리를 보내고 식별자를 가져온다. 이 전략을 쓰면 jpa의 기능중 쓰기지연을 
사용할 수 없다.
* Sequence 전략은 DB의 sequence object를 사용해서 기본키를 생성하는 방식으로
em.persist() 호출 전 DB의 sequence object에서 기본키를 가져온다. 
최적화 방법으로 allocationSize를 사용하면 한 번에 여러개의 기본키를 메모리로 불러와서
사용할 수 있다.
### 상속
* Joined 전략은 부모엔티티와 자식엔티티를 테이블을 따로 생성하고 조회시 join을 한다.
저장공간을 효율화할 수 있지만 join을 사용하기 때문에 성능면에서는 좋지 않다.
* Single Table 전략은 부모엔티티와 자식엔티티를 하나의 테이블로 생성한다. 
테이블 하나에 모두 저장하기때문에 null인 컬럼이 발생할 수 있지만 조회시 join을 사용하지
않기때문에 성능면에서 좋다.


### 3. 트랜잭션
* ### Lock
* Optimisic Lock은 충돌이 발생하지 않는다는 가정하에 lock을 걸지 않는 방식이다.
@Version을 통해서 충돌을 방지한다.
* Pessimistic Lock은 충돌이 발생한다는 가정하에 lock을 거는 방식이다.
```java
        // 베타락을 건다.
        @Lock(value = LockModeType.PESSIMISTIC_WRITE)
        // scope를 extended로 하면 join table까지 lock이 걸린다.
        @QueryHints({@QueryHint(name = "javax.persistence.lock.scope", value = "EXTENDED")})
```

## 인터뷰
### JPA에서 repository 인터페이스는 어떻게 인스턴스화 되나?
* LocalContainerEntityManagerFactoryBean에서 hibernate property , datasource ,
엔티티 위치 패키지를 지정해주고 hibernate 기반으로 동작한다는 JpaVendor를 설정한다.
```java
      @Bean
      public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
      }
    
      @Bean
      public EntityManagerFactory entityManagerFactory() {
    
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
    
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.spring.model");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
    
        return factory.getObject();
      }
    
      @Bean
      public PlatformTransactionManager transactionManager() {
    
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
      }
```