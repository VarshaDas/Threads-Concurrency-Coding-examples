import java.util.concurrent.*;
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        //Create a cached thread pool
        ExecutorService executor = Executors.newCachedThreadPool();

        //Simulate a large number of tasks
        for (int i = 1; i <= 15; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " executed by thread: " +
                        Thread.currentThread().getName());
                try {
                    //Simulate some task execution time
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }  
        executor.shutdown();    //Shutdown the thread pool
    }
}
