package Threads;

import java.util.concurrent.Semaphore;

public class BinarySemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1); // Semaphore with 1 permit

        try
        {
            System.out.println("Thread 1 is trying to acquire a permit.");
            semaphore.acquire();
            System.out.println("Thread 1 has acquired a permit.");
            System.out.println("Available permits: " + semaphore.availablePermits());
            Thread.sleep(2000); // Simulate some work
            semaphore.release();
            System.out.println("Thread 1 has released the permit.");
            System.out.println("Available permits: " + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
