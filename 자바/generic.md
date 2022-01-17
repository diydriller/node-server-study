## Type
* primitive type은 실제 데이터를 저장하는 타입이고 stack영역에 저장된다.
종류로는 논리형 , 실수형 , 정수형 , 문자형이 있다. null을 저장하기위해서는 wrapper 클래스를
사용한다.
* reference type은 객체의 주소를 저장하는 타입이고 객체는 heap영역에 저장되고 참조타입변수는
stack영역에 저장된다. null을 저장할 수 있다.

##  Generic
* 데이터타입을 일반화하는 것으로 컴파일시점에 타입검사를 하고 런타임시 타입을 제거한다. 
* 타입을 명시할때 primitive type을 사용 못 하므로 wrapper 클래스를 사용해야한다.
* 타입제거시 unbound type은 object로 변환되고 bound type은 첫번째 bound type으로
변환된다.