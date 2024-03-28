package Threads;

import java.util.concurrent.Semaphore;

public class SemaphoreUsageExample {
    static int counter = 0;
    static Semaphore semaphore = new Semaphore(1);

    static void incrementCounter() {
        try {
            semaphore.acquire();
            counter++;
            System.out.println("Counter incremented by thread: " + Thread.currentThread().getId() + ", Counter value: " + counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        // Create multiple threads
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> incrementCounter());
            thread.start();
        }
    }
}
