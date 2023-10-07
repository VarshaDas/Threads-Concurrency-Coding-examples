package Threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockAdvantagesExample {
    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable incrementTask = () -> {
            boolean lockAcquired = false;
            try {
                // Attempt to acquire the lock with a timeout of 1 second
                lockAcquired = lock.tryLock(1, TimeUnit.MILLISECONDS);
                if (lockAcquired) {
                    for (int i = 0; i < 10000; i++) {
                        counter++;
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " couldn't acquire the lock.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lockAcquired) {
                    lock.unlock(); // Release the lock
                }
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);
        Thread thread3 = new Thread(incrementTask);
        Thread thread4 = new Thread(incrementTask);


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter (with Reentrant Lock): " + counter);
    }
}
