package Threads;

public class WaitNotifyDemo {
    private static final Object lock = new Object();
    private static boolean helloPrinted = false;

    public static void main(String[] args) {
        Thread helloThread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Hello");
                helloPrinted = true;
                lock.notify(); // Notify the other thread that "Hello" is printed
            }
        });

        Thread worldThread = new Thread(() -> {
            synchronized (lock) {
                while (!helloPrinted) {
                    try {
                        lock.wait(); // Wait for "Hello" to be printed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("World");
            }
        });

        helloThread.start();
        worldThread.start();
    }
}

