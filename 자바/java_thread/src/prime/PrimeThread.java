package prime;

import prime.Prime;

public class PrimeThread  extends Thread {

    Prime prime;

    PrimeThread(Prime prime){
        this.prime=prime;
    }

    @Override
    public void run()  {
            while(true){

                int num = prime.increaseAndGetNum();
                if (num > PrimeUtil.num) break;

                if(PrimeUtil.isPrime(num)){
                    prime.increasePrimeCnt();
                }

                //PrimeUtil.printPrime(num);
            }

    }

}
