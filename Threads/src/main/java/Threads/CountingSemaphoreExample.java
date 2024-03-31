package Threads;
import java.util.concurrent.Semaphore;

public class CountingSemaphoreExample {
    public static void main(String[] args) {
        Semaphore countingSemaphore = new Semaphore(3); // Creating a counting semaphore with three permits

        // Acquiring semaphore
        try {
            countingSemaphore.acquire();
            System.out.println("Thread 1 has acquired the semaphore.");
            System.out.println("Available Permits: " + countingSemaphore.availablePermits()); // Should print 2
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Releasing semaphore
            countingSemaphore.release();
            System.out.println("Thread 1 has released the semaphore.");
            System.out.println("Available Permits: " + countingSemaphore.availablePermits()); // Should print 3
        }

        // Acquiring semaphore again
        try {
            countingSemaphore.acquire();
            System.out.println("Thread 2 has acquired the semaphore.");
            System.out.println("Available Permits: " + countingSemaphore.availablePermits()); // Should print 2
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Releasing semaphore again
            countingSemaphore.release();
            System.out.println("Thread 2 has released the semaphore.");
            System.out.println("Available Permits: " + countingSemaphore.availablePermits()); // Should print 3
        }
    }
}