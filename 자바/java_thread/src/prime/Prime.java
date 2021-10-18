package prime;

public class Prime {
    private int num=0;
    private int primeCnt=0;

    // 공유자원인 num 동기화
    synchronized public int increaseAndGetNum() {
        num++;
        return num;
    }

    // 공유자원 primeCnt 동기화
    synchronized public int increasePrimeCnt(){
        primeCnt++;
        return primeCnt;
    }

    public int getPrimeCnt(){
        return primeCnt;
    }

}
