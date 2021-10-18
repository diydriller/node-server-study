package prime;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;

public class PrimeMain {


    public static void main(String[] arg) throws InterruptedException {

        // 멀티 스레드 사용 , synchronized 사용해서 동기화
        int threadCount=8;

        long start1 = System.currentTimeMillis();
        Prime prime=new Prime();
        List<PrimeThread> threadList=new ArrayList<>();

        for(int i=0;i<threadCount;i++){
            threadList.add(new PrimeThread(prime));
        }
        for(PrimeThread t:threadList){
            t.start();
        }
        for(PrimeThread t:threadList){
            t.join();
        }


        long end1 = System.currentTimeMillis();
        System.out.println("prime cnt is "+prime.getPrimeCnt());
        System.out.println("when synchronized multi thread is used , took "+(end1-start1));


        // 싱글 스레드 사용
        long start2 = System.currentTimeMillis();
        int cnt2=0;
        for(int num = 1; num<= PrimeUtil.num; num++){

            if(PrimeUtil.isPrime(num)){
                cnt2++;
            }
            //PrimeUtil.printPrime(num);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("prime cnt is "+cnt2);
        System.out.println("when single thead is used , took "+(end2-start2));

        // 멀티 스레드 사용 , atomic 사용해서 동기화

        long start3 = System.currentTimeMillis();

        ExecutorService service= Executors.newFixedThreadPool(threadCount);

        AtomicInteger integer = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(PrimeUtil.num);
        AtomicInteger cnt3 = new AtomicInteger();


        while(true) {
            int num = integer.incrementAndGet();
            if (num > PrimeUtil.num) break;

            service.execute(() -> {

                if(PrimeUtil.isPrime(num)){
                    cnt3.incrementAndGet();
                }
                //PrimeUtil.printPrime(num);
                latch.countDown();
            });

        }
        latch.await();
        service.shutdown();


        long end3 = System.currentTimeMillis();
        System.out.println("prime cnt is "+cnt3);
        System.out.println("when atomic multi thead is used , took "+(end3-start3));
    }



}
