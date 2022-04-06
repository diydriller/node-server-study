# JDBC

## 1. JDBC API
* jdbc는 데이터베이스에 접속해서 자바프로그램과 데이터베이스 사이에 데이터를 주고
받을 수 있도록 지원해주는 인터페이스이다.
* Jdbc Driver는 각 dbms 제조사별로 자신들의 dbms에 접근할 수 있도록 jdbc
인터페이스에 명시된 메서드를을 구현한 것이다.
* ### 연결과정
1) Jdbc Driver를 로드한다.
2) database 접속정보를 이용하여 Connection 객체를 얻는다.
3) Connection 객체로부터 쿼리수행을 위한 PreparedStatement 객체를 얻는다.
4) executeQuery 메서드를 수행해서 ResultSet 객체를 받아 데이터를 처리한다.
5) 처리가 완료되면 리소스를 close 메서드를 수행해서 반납한다.

## 2. Connection Pool
* databae에서 Connection 객체를 얻는 과정은 오래 걸리므로 WAS 시작시 미리 일정량의 
connection을 만들어서 pool을 생성하고 pool로부터 Connection 객체를 
가져다쓰고 반납하는 것이다.

## 3. JDBC Template
* database 접속정보를 가진 DataSource를 주입받아서 jdbc template 메서드를 이용해서
쿼리를 수행하고 RowMapper 객체를 통해 쿼리결과값을 객체로 매핑한다.

## 인터뷰
### Statement와 PreparedStatement 차이?
* statement를 사용하면 데이터베이스에서 쿼리문장분석 - 컴파일 - 실행 과정을 거쳐 쿼리를 수행한다.
PreparedStatement를 사용하면 처음 1번만 3단계를 거쳐 쿼리를 수행하고 이후에는 캐시를 사용한다.