import java.util.concurrent.*;

/*
 *
 * This program demonstrates the usage of ThreadPoolExecutor's various configuration parameters, including core pool size, maximum pool size, and keep-alive time. 
 * It uses a custom thread factory to create threads with a specific naming convention.
 * 
 */

public class CustomThreadFactoryDemo {
    public static void main(String[] args) {
        // Creating a custom thread factory
        ThreadFactory threadFactory = new CustomThreadFactory();
        // Creating a ThreadPoolExecutor with custom configuration
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            3, //core pool size
            5,   //maximum pool size
            30,  //keep-alive time
            TimeUnit.SECONDS, //time unit for keep-alive time
            new LinkedBlockingQueue<>(),   //task queue
            threadFactory //thread factory
            );   

        //Submitting tasks to the thread pool
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by thread: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();   //Shutting down the thread pool
    }
   // Custom thread factory to create threads with a specific naming convention
    static class CustomThreadFactory implements ThreadFactory {
        private static int threadCount = 1;

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, "CustomThread-" + threadCount++);
            return thread;
        }
    }
}


    






