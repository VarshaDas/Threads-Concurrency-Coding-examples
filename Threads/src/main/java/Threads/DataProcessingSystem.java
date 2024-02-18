package Threads;
import java.util.concurrent.CountDownLatch;

public class DataProcessingSystem {
    private final CountDownLatch latch;

    public DataProcessingSystem(int validationTaskCount) {
        this.latch = new CountDownLatch(validationTaskCount);
        
    }

    public void startValidation() {
        // Create and start three validation tasks
        Thread validator1 = new Thread(new Validator("Validator 1"));
        Thread validator2 = new Thread(new Validator("Validator 2"));
        Thread validator3 = new Thread(new Validator("Validator 3"));

        validator1.start();
        validator2.start();
        validator3.start();

        try {
            // Main thread waits until latch count reaches 0
            latch.await();
            System.out.println("All validation tasks have completed. Data validation completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private class Validator implements Runnable {
        private final String name;

        public Validator(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " is performing validation...");
            try {
                // Simulating validation by sleeping for a random duration
                Thread.sleep((long) (Math.random() * 5000));
                System.out.println(name + " has completed validation.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Decrements the latch count
                latch.countDown();
            }
        }
    }

    public static void main(String[] args) {
        // Create DataProcessingSystem with 3 validation tasks
        DataProcessingSystem system = new DataProcessingSystem(3);
        // Start data validation
        system.startValidation();
    }
}

