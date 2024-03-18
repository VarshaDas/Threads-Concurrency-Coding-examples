package Executors.CompletableFuture;

import java.util.concurrent.*;

public class FuturevsCompletable {

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            // Using Future
            ExecutorService executor = Executors.newFixedThreadPool(1);
            Future<Integer> future = executor.submit(() -> {
                Thread.sleep(3000); // Simulate a long-running task
                return 42;
            });

            // Blocking call to get the result from Future
            int resultFromFuture = future.get();
            System.out.println("Result from Future: " + resultFromFuture);

            // Shutdown the executor
            executor.shutdown();

            // Using CompletableFuture
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000); // Simulate a long-running task
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 42;
            });

            // Non-blocking call to handle the result from CompletableFuture
            completableFuture.thenAccept(result -> {
                System.out.println("Result from CompletableFuture: " + result);
            });

            // Main thread continues executing other tasks concurrently
            for (int i = 0; i < 5; i++) {
                System.out.println("Main thread executing task " + i);
                Thread.sleep(1000);
            }
        }
    }


