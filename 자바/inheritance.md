## Object 클래스
* equals 메서드는 객체의 주소값이 같은지 비교한다.
* hashcode 메서드는 해시테이블에서 두 객체가 같은 객체인지 판별할때 사용된다.
equals 메서드로 같은 객체로 판별시 hashcode 값도 같아야하므로 
equals 메서드 재정의시 hashcode 메서드도 재정의해야한다.