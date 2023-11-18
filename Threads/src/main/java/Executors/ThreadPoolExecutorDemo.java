import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // Create a ThreadPoolExecutor with initial configuration
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        // Display the initial configuration
        System.out.println("Core Pool Size: " + executor.getCorePoolSize());
        System.out.println("Maximum Pool Size: " + executor.getMaximumPoolSize());
        System.out.println("Keep Alive Time: " + executor.getKeepAliveTime(TimeUnit.SECONDS));
        System.out.println("Queue Size: " + executor.getQueue().size());

        // Submit tasks to the thread pool
        for (int i = 1; i <= 5; i++) {
            Task task = new Task("Task " + i);
            executor.execute(task);
        }

        // Display the current pool size, active threads, and task count
        System.out.println("Pool Size after task submission: " + executor.getPoolSize());
        System.out.println("Active Threads: " + executor.getActiveCount());
        System.out.println("Task Count: " + executor.getTaskCount());

        // Allow some time for tasks to complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the final pool size, active threads, and task count
        System.out.println("Pool Size after task completion: " + executor.getPoolSize());
        System.out.println("Active Threads: " + executor.getActiveCount());
        System.out.println("Task Count: " + executor.getTaskCount());

        // Shut down the executor
        executor.shutdown();
    }

    static class Task implements Runnable {
        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Executing task: " + name);
            try {
                // Simulate some task execution time
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
