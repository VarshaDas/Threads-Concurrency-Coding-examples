package Executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import java.util.concurrent.Future;

public class FutureBlockingDemo {


        static ExecutorService threadPool = Executors.newFixedThreadPool(2);

        public static void main( String args[] ) throws Exception {
            System.out.println( " sum: " + findSumWithException(10));
            threadPool.shutdown();
        }

        static int findSumWithException(final int n) throws ExecutionException, InterruptedException {

            int result = -1;

            Callable<Integer> sumTask = new Callable<Integer>() {

                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 1; i <= n; i++) {
                        sum += i;
                        Thread.sleep(1000);
                    }
                    return sum;
                }
            };

            Future<Integer> f = threadPool.submit(sumTask);

            try {

                while(!f.isDone()) {
                    System.out.println("do some other");
                    Thread.sleep(3000);

                }
                result = f.get();
            } catch (ExecutionException ee) {
                System.out.println("Something went wrong. " + ee);
            }

            return result;
        }

    }

