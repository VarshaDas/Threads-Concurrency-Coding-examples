package Concurrency;

import java.util.concurrent.CyclicBarrier;

class HikingTrip {
    private static final int NUM_HIKERS = 4;
    private static final CyclicBarrier checkpoint = new CyclicBarrier(NUM_HIKERS);

    public static void main(String[] args) {
        for (int i = 1; i <= NUM_HIKERS; i++) {
            Hiker hiker = new Hiker("Hiker " + i);
            hiker.start();
        }
    }

    static class Hiker extends Thread {
        public Hiker(String name) {
            super(name);
        }

        public void run() {
            System.out.println(getName() + " started hiking.");
            try {
                // Simulate hiking to a checkpoint
                Thread.sleep((long) (Math.random() * 5000));

                System.out.println(getName() + " reached a checkpoint.");
                // Wait for other hikers to reach the checkpoint
                checkpoint.await();

                // All hikers have reached the checkpoint, continue hiking
                System.out.println(getName() + " continues hiking.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
