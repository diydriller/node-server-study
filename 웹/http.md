## HTTP
* ### HTTP 1
* 문자열 그대로 전송
* 매 요청마다 header 구조가 반복된다.
* persistent connection을 사용해서 일정시간동안 connection 닫지 않고 재사용한다.
* ### HTTP 2
* 문자열이 이진데이터로 전송한다.
* 멀티플렉싱을 이용해서 여러 요청을 병렬로 수행한다.
* 서버푸쉬를 사용해서 연관된 리소스의 경우 서버에서 요청없이 보낸다.
* 헤더압축을 사용해서 크기를 줄인다.
* ### Header
* Keep-Alive : 특정시간동안 최대 요청수를 설정해서 connection을 유지한다.
* Etag : 서버 리소스의 버전을 나타내며 브라우저에 캐싱된 etag와 서버 리소스의 etag가
같다면 304 Not Modified로 응답해준다.
* Cache-Control : 캐시옵션을 설정한다.
* Age : max-age 시간내에서 시간이 얼마나 흘렀는지 나타낸다.
* Expires : 캐시 만료시간을 나타낸다.
* Set-Cookie : 캐시를 저장하라는 명령이다.