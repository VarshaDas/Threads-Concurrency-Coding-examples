public class InterThreadCommunication {

    public static void main(String[] args) {
        final SharedCounter sharedCounter = new SharedCounter();

        //Create first thread: for incrementing
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedCounter.increment();
            }
        });

        //Create second thread: for decrementing
        Thread decrementThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedCounter.decrement();
            }
        });

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + sharedCounter.getCounter());
    }
}

class SharedCounter {
    private int counter = 0;

    public synchronized void increment() {
        // While the counter is at the maximum value (5), wait for a decrement to make room.
        while (counter >= 5) {
            try {
                System.out.println("Increment thread waiting.");
                wait(); // Wait for a decrement to make room
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        counter++;
        System.out.println("Incremented Counter: " + counter);

        if (counter == 1) {
            // Use notify() to wake up a single waiting thread (decrementThread) when the counter changes.
            notify();
        } else {
            // Use notifyAll() to wake up all waiting threads when the counter changes.
            notifyAll();
        }
    }

    public synchronized void decrement() {
        // While the counter is at the minimum value (0), wait for an increment to have something to decrement.
        while (counter <= 0) {
            try {
                System.out.println("Decrement thread waiting.");
                wait(); // Wait for an increment to have something to decrement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        counter--;
        System.out.println("Decremented Counter: " + counter);

        if (counter == 4) {
            // Use notify() to wake up a single waiting thread (incrementThread) when the counter changes.
            notify();
        } else {
            // Use notifyAll() to wake up all waiting threads when the counter changes.
            notifyAll();
        }
    }

    public int getCounter() {
        return counter;
    }
}
