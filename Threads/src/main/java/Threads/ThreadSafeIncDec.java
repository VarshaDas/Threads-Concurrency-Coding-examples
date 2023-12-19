package Threads;


import java.util.concurrent.atomic.AtomicInteger;

/*
 Implement a thread-safe counter class in Java that supports increment and decrement operations.
 Multiple threads should be able to increment and decrement the counter concurrently without any race conditions.

 */
public class ThreadSafeIncDec {

//    private static volatile int  counter = 0;


    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        Runnable incrementTask = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<1000;i++){
                    counter.incrementAndGet();
                }
            }
        };

        Runnable decrementTask = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<1000;i++){
                    counter.decrementAndGet();
                }
            }
        };

       Thread increment = new Thread(incrementTask);
       Thread decrement = new Thread(decrementTask);

       increment.start();
       decrement.start();

       try {
           increment.join();
           decrement.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }

        System.out.println("Final count = "+counter.get());
    }
}


