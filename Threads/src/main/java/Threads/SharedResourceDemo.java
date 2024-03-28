package Threads;

class SharedResource {
    private int count = 0;

    // Method to increment the count by 1
    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + " increments count to: " + count);
    }

    // Method to decrement the count by 1
    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + " decrements count to: " + count);
    }
}

public class SharedResourceDemo {
    public static void main(String[] args) {
        // Creating a shared resource
        SharedResource sharedResource = new SharedResource();

        // Creating and starting multiple threads
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                // Accessing the shared resource
                sharedResource.increment();
                sharedResource.decrement();
            }).start();
        }
    }
}
