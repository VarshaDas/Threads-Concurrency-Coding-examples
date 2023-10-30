package Threads;

public class InterThreadComm {
        public static void main(String[] args) {
            final SharedCounter sharedCounter = new SharedCounter();

            Thread incrementThread = new Thread(new IncrementTask(sharedCounter));
            Thread decrementThread = new Thread(new DecrementTask(sharedCounter));

            incrementThread.start();
            decrementThread.start();
        }
    }

    class SharedCounter {
        private int count = 0;

        public synchronized void increment() {
            while (count >= 5) {
                try {
                    wait(); // Wait if the counter is 5 or more
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            count++;
            System.out.println("Incremented to: " + count);
            notifyAll();
        }

        public synchronized void decrement() {
            while (count <= 0) {
                try {
                    wait(); // Wait if the counter is 0 or less
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            count--;
            System.out.println("Decremented to: " + count);
            notifyAll();
        }
    }

    class IncrementTask implements Runnable {
        private SharedCounter sharedCounter;

        public IncrementTask(SharedCounter sharedCounter) {
            this.sharedCounter = sharedCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                sharedCounter.increment();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class DecrementTask implements Runnable {
        private SharedCounter sharedCounter;

        public DecrementTask(SharedCounter sharedCounter) {
            this.sharedCounter = sharedCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                sharedCounter.decrement();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


