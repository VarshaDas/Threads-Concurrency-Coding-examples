import java.util.concurrent.*;

/*
 *
 * This program uses a cached thread pool to process a large number of tasks. 
 * We test its performance and observe how the thread pool adjusts the number of threads based on the workload.
 * 
 * Here, we will create a Cached Thread Pool, and submit 15 tasks to the pool. 
 * Each task prints its ID and the name of the executing thread.
 * 
 * Since a cached thread pool can dynamically adjust the number of threads based on the workload, it is observed that initially, a few threads will be created to process tasks. 
 * As tasks complete, threads may be reused for new tasks, and if the workload is consistently high, the pool may create new threads up to a maximum limit.
 */
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
