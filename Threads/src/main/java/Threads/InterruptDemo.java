package Threads;

public class InterruptDemo {
    public static void main(String[] args) {
        Thread workerThread = new Thread(() -> {
            System.out.println("Worker thread started.");
            try {
                // Simulating a time-consuming task
                for (int i = 0; i < 10; i++) {
                    System.out.println("Working... (" + (i + 1) + "/10)");
                    Thread.sleep(1000); // Sleep for 1 second
                    // Check if the thread has been interrupted
                    if (Thread.interrupted()) {
                        System.out.println("Thread interrupted. Cleaning up...");
                        // Clean up resources, if any
                        break;
                    }
                }
                System.out.println("Task completed.");
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted while sleeping. Cleaning up...");
                // Clean up resources, if any
            }
            System.out.println("Worker thread finished.");
        });

        // Start the worker thread
        workerThread.start();

        // Main thread sleeps for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupting the worker thread
        System.out.println("Interrupting worker thread...");
        workerThread.interrupt();
    }
}
