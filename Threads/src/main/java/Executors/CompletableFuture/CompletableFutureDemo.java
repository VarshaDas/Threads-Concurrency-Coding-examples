package Executors.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        // supplyAsync
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running computation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        // thenApply
        CompletableFuture<String> future2 = future1.thenApply(result -> "Transformed Result: " + result);

        // thenCompose
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> 20)
                .thenCompose(result -> CompletableFuture.supplyAsync(() -> result * 2));

        // thenCombine
        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future5 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> combinedFuture = future4.thenCombine(future5, (result1, result2) -> result1 + result2);

        // exceptionally
        /*CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Exception occurred");
        }).exceptionally(exception -> {
            System.out.println("Exception occurred: " + exception.getMessage());
            return 0;
        });*/

        // allOf
        CompletableFuture<Integer> future7 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future8 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future7, future8);

        // thenAccept
        future1.thenAccept(result -> System.out.println("Result from supplyAsync: " + result));

        // thenRun
        allFutures.thenRun(() -> System.out.println("All futures completed"));

        // Join all futures to wait for their completion
        CompletableFuture.allOf(future1, future2, future3, future4, future5, future7, future8).join();
    }
}

    

