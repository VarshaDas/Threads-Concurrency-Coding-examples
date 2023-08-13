package Threads;

import java.util.concurrent.atomic.AtomicInteger;


/*
 Implement a thread-safe counter class in Java that supports increment and decrement operations.
  Multiple threads should be able to increment and decrement the counter concurrently without any race conditions.

 */
public class ThreadSafeIncDec {

   static int count = 0;
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
//        ThreadSafeCounter counter = new ThreadSafeCounter();


        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
//               counter.incrementAndGet();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count--;
//                counter.decrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + count); // Output should be 0 if the counter is thread-safe.
    }

}

class ThreadSafeCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public void decrement() {
        counter.decrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
