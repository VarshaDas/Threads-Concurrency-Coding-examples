package Threads;

import java.util.concurrent.*;

public class CustomThreadFactoryExample {

    public static void main(String[] args) {
        // Create a thread pool with a custom thread factory using Executors.newCachedThreadPool()
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool(runnable -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(false); // Set threads to non-daemon
            return thread;
        });

        // Execute tasks in the cached thread pool
        for (int i = 0; i < 5; i++) {
            cachedThreadPool.execute(() -> System.out.println("Task executed by " + Thread.currentThread().getName()));
        }
        cachedThreadPool.shutdown();

        // Create a thread pool with a custom thread factory using Executors.newFixedThreadPool()
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3, runnable -> {
            Thread thread = new Thread(runnable);
            thread.setPriority(Thread.MAX_PRIORITY); // Set threads to maximum priority
            return thread;
        });

        // Execute tasks in the fixed thread pool
        for (int i = 0; i < 5; i++) {
            fixedThreadPool.execute(() -> System.out.println("Task executed by " + Thread.currentThread().getName()));
        }
        fixedThreadPool.shutdown();
    }
}

