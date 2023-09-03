package Threads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // Create a ThreadPoolExecutor with a fixed pool size of 3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks for execution
        for (int i = 1; i <= 100  ; i++) {
            int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
            });
        }

        // Shutdown the executor when tasks are done
       executor.shutdown();
    }
}
