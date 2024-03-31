package Executors.CompletableFuture;


import javax.swing.*;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureMethodsDemo {
    public static void main(String[] args) {
       // runAsync vs supplyAsync

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello varsha";
        });



        CompletableFuture<Void> f2 = CompletableFuture.runAsync(()-> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("run async method");
        });

//        f2.join();


        //thenApply
        CompletableFuture<String> f3 = f1.thenApply(result -> result + " how are you?");
//        System.out.println(f3.join());


        //thencompose

        CompletableFuture<String> f4 = f1.thenCompose(result -> CompletableFuture.supplyAsync(() -> result + "How do you do"));


//        Use thenCompose() when you have a chain of dependent asynchronous computations,
//        and you need to chain them together, passing the result of one computation as the input to the next.

//        Use thenApply() when you want to apply a function to the result of a CompletableFuture without
//        chaining another asynchronous computation.


        //thencombine
        CompletableFuture<Integer> f5 =
                CompletableFuture.supplyAsync(() -> 20).thenCombine(CompletableFuture.supplyAsync(() -> 50), (res1, res2) -> res1 + res2);


        //exeptionally
        CompletableFuture<Object> f6 = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Exception!!");
        }).exceptionally(exception -> {
                    System.out.println("exception occurred");
                    return 0;
        });

        //all of - signal to the main thread - wait till all futures complete
        CompletableFuture.allOf(f1, f2, f3, f4, f5, f6).join();


        System.out.println(f1.join());

        System.out.println(f3.join());
        System.out.println(f4.join());
        System.out.println(f5.join());









    }
}
