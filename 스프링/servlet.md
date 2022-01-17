## Servlet
* ### 정의
* 웹 애플리케이션을 만들때 필요한 인터페이스이다.
* ### CGI(Common Gateway Interface)
* 이전에는 web server를 통해 정적 페이지만을 처리했었지만 CGI가 도입된 이후로
  동적 페이지를 처리할 수 있게 되었다.
* 요청이 들어오면 요청을 처리하는 프로세스와 CGI 구현체가 생성되었는데 비효율적이었다.
  프로세스를 스레드로 변경하고 CGI 구현체를 싱글톤 객체인 Servelt 구현체로 변경함으로써
  성능을 개선하였다.
* ### 생명주기
* init 메서드로 Servlet을 초기화한다.
* service 메서드로 실제 기능이 수행된다.
* destroy 메서드로 Servlet을 제거한다.
* ### 요청 처리
* 요청 URL마다 처리를 위한 Servlet이 필요하고 매핑정보는 web.xml에 기술한다.