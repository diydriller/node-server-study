## SOP(Same Origin Policy)란?
* url은 protocol , host , port , path로 이루어져있다.
origin은 javascript에서 location.origin으로 확인할 수 있다.
sop는 같은 origin만 자원을 공유할 수 있도록 하는 정책으로 브라우저의 기본 정책이다.

## CORS(Cross Origin Resource Sharing)란?
* 다른 origin의 자원을 공유할 수 있도록 하는 권한체계를 말한다.
* preflight request는 본 요청에 앞서서 OPTIONS 메서드를 통해서 서버로 요청을 보내서
허용된 origin인지 확인한다. 브라우저에서 서버로부터 받은 응답헤더에서 
Access-Control-Allow-Origin을 확인해서 허용된 origin인지 확인한다.
* simple request는 예비요청없이 서버로 요청을 보내서 허용된 origin인지 확인한다.