import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CustomThreadFactory implements ThreadFactory {
    private String threadNamePrefix;
    private int threadPriority;

    public CustomThreadFactory(String threadNamePrefix, int threadPriority) {
        this.threadNamePrefix = threadNamePrefix;
        this.threadPriority = threadPriority;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(threadNamePrefix + "-" + thread.getId());
        thread.setPriority(threadPriority);
        return thread;
    }
}


public class ThreadPoolWithCustomThreadFactory {

    public static void main(String[] args) {
        // Create a custom thread factory with a specific name prefix and priority
        CustomThreadFactory customThreadFactory = new CustomThreadFactory("CustomThread", Thread.NORM_PRIORITY);

        // Create a thread pool with the custom thread factory
        ExecutorService executorService = Executors.newFixedThreadPool(5, customThreadFactory);

        // Submit some tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            int taskNumber = i + 1;
            executorService.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by thread: " + Thread.currentThread().getName());
            });
        }

        // Shutdown the thread pool
        executorService.shutdown();
    }
}


