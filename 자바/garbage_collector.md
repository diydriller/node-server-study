## Garbage Collection
* jvm의 heap영역에서 사용되지않는 객체를 삭제하는 제거하는 프로세스이다.
* heap영역은 young generation영역과 old generation영역으로 나뉘며 
young generation은 eden영역 , survivor1영역 , survivor2영역으로 나뉜다.
처음 객체가 생성되면 eden영역에 생성되며 가득차면 minor gc가 발생하며 
survivor1영역과 survivor2영역 중 비어있는 곳으로 이동된다. 객체의 age값이
특정 임계치를 넘게 되면 old generation영역으로 이동된다. old genertaion 영역이
가득차면 major gc가 발생한다.

## gc 알고리즘
* reference counting은 객체의 참조카운트를 기록하면서 참조값이 0이 되면 객체를 삭제하는
방법이다.
* mark and sweep은 gc root로부터 참조되는 객체를 추적해서 마킹하는 mark와 마킹되지않는
객체를 삭제하는 sweep으로 이루어지는 방법이다. gc root는 stack영역의 변수 ,
method영역의 static 변수가 될 수 있다.

## gc 종류
* gc가 발생하면 gc를 수행하는 스레드외의 모든 스레드가 멈추는 것을 stop the world라고
  하고 gc는 stop the world가 수행되는 시간을 줄이기 위해 발전되어왔다.
* serial gc는 gc를 처리하는 스레드가 1개이며 mark and sweep 이후 compact과정을 거친다.
* parallel gc는 gc를 처리하는 스레드가 여러개이다.
* cms gc는 application 스레드와 gc 스레드가 동시에 실행된다.
initial mark 단계에서 stop the world가 발생해서 gc root가 참조하는 객체를 마킹한다.
concurrent 단계에서 살아있는 객체가 참조하는 객체들을 추적해서 마킹한다.
remark 단계에서 stop the world가 발생해서 이전 단계에서 확인한 객체들을 다시 추적하면서
추가되거나 삭제된 객체를 확인하고 마킹을 확정한다.
cuncurrent sweep 단계에서 마킹되지않은 객체를 삭제한다.