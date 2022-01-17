## MVC
* ### 정의
* 데이터와 관려된 model , 사용자에게 보여지는 부부인 view , model과 view를
  중계하는 controller로 이루어진다.
* ### 규칙
* model은 controller와 view에 의존하지 않는다.
* view는 사용자의 조작에 따라 다르게 보여지는 부분에 대해서만 model로부터 데이터를
  받는다.
* controller는 model과 view에 의존해도 된다.
* ### Spring MVC
* 웹브라우저에서 request 요청을 하면 Dispatcher Servlet에서 요청을 받고
  handler mapping을 통해 적절한 Controller로 요청을 보낸다.
* Controller에서는 페이지에 들어가는 데이터인 Model을 완성시켜 Dispatcher Servlet으로
  보낸다.
* Dispatcher Servlet은 View Resolver를 통해 view 파일을 찾고 Model을 이용해서 페이지를
  완성시킨다.
* Interceptor는 Controller에 들어오는 HttpRequest나 Contoller에서 나가는 HttpResponse를
  가로챌 수 있다.


