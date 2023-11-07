import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.*; 


/*
This program demonstrates the usage of the ScheduledExecutorService to schedule tasks to run periodically at fixed intervals.

 */

public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3); //Creating a ScheduledExecutorService with 3 threads

        Runnable task = () -> {
            System.out.println("Task executed on: "+LocalDateTime.now().toString());
        };

        //schedule the task to run with an initial delay of 1 second and then every 5 seconds
        scheduler.scheduleAtFixedRate(task, 1, 5, TimeUnit.SECONDS);

        //keep the program running for a while to observe the periodic execution
        try {
            Thread.sleep(20 * 1000); //run for 20 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //shutdown the scheduler when done
        scheduler.shutdown();
    }
}
