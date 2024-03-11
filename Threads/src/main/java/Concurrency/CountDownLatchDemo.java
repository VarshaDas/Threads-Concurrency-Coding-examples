package Concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CountDownLatchDemo {

        public static void main(String[] args) {
            int numberOfParticipants = 5;
            Semaphore semaphore = new Semaphore(2);
            CountDownLatch startSignal = new CountDownLatch(5); // Initialize with a count of 1.

            // Create and start participant threads.
            for (int i = 0; i < numberOfParticipants; i++) {
                new Participant(startSignal, "Participant " + (i + 1)).start();
            }

            // Now, the main thread waits for all participants to be ready.
            System.out.println("Waiting for all participants to be ready...");
            try {
                Thread.sleep(7000); // Simulating some preparations.
                System.out.println("count after wake up = "+startSignal.getCount());


                System.out.println("count now= "+startSignal.getCount());

                startSignal.await();
                System.out.println("count = "+startSignal.getCount());
                System.out.println("Race has started!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Participant extends Thread {
        private final CountDownLatch startSignal;

        public Participant(CountDownLatch startSignal, String name) {
            super(name);
            this.startSignal = startSignal;
        }

        @Override
        public void run() {
            System.out.println(getName() + " is getting ready...");
            try {
                System.out.println("printing count for partipant " + getName()+ " before ready :" +startSignal.getCount());
                Thread.sleep(1000); // Simulating preparation time.
                System.out.println(getName() + " is ready!");
                startSignal.countDown(); // Signal that this participant is ready.
                System.out.println("printing count for "+ getName() + " " +startSignal.getCount());

                // Perform some action when the race starts.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


}

