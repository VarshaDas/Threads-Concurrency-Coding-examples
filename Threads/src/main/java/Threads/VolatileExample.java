package Threads;

public class VolatileExample {
    private static volatile int sharedCounter = 0;
    public static void main(String[] args) {
        // Creating multiple threads to increment the shared counter
        Runnable volatileTask = () -> {
            for (int i = 0; i < 10000; i++) {
                sharedCounter++; // Incrementing a volatile variable
            }
        };
        Thread thread1 = new Thread(volatileTask);
        Thread thread2 = new Thread(volatileTask);

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
            System.out.println("Volatile Counter: " + sharedCounter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
