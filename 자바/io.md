## Serialization
* 객체를 byte array로 변환하는 것을 직렬화라고 하고 이를 위해서는 serializable 인턴페이스를
구현해야 한다.
* 객체가 직렬화될때 클래스 이름과 serial number를 저장하는데 serial number는
역직렬화할때 클래스를 구분하는 용도로 사용된다. serial number를 지정해주지 않으면 자동으로
해시값이 지정이 되는데 만약 이후에 클래스가 달라지게되면 serial number 또한 달라져서
역직렬화시 InvalidClassException이 발생할 수 있다. 따라서 serial number를 명시적으로 지정
해주는 것이 좋다.