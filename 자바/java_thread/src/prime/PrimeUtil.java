package prime;

public class PrimeUtil {
    // 구하는 소수 개수
    public final static int num=150000;

    // 소수 판별 메서드
    public static boolean isPrime(int num){

        if(num<=1) return false;
        else if(num==2) return true;
        else{
            for(int i=2;i<num;i++){
                if(num%i==0) return false;
            }
            return true;
        }
    }

    // 소수 출력
    public static void printPrime(int num) {
        if (isPrime(num)) {
            System.out.println(num + " is prime");
        } else {
            System.out.println(num + " is not prime");
        }
    }

}
