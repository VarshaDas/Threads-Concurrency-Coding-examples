package Executors;

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


//        scheduler.scheduleAtFixedRate(() -> {
//            long currentTimeSeconds = System.currentTimeMillis() / 1000; // Convert milliseconds to seconds
//            System.out.println("Task with fixed rate executed at: " + currentTimeSeconds + " seconds");
//            try {
//                Thread.sleep(4000); // Simulating task execution time
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, 0, 3, TimeUnit.SECONDS);



        scheduler.scheduleAtFixedRate(()->{
            long currentTimeSeconds=System.currentTimeMillis()/1000;
            System.out.println("Task with fixed delay executed at:"+currentTimeSeconds+" seconds");
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        },3,3,TimeUnit.SECONDS);


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


