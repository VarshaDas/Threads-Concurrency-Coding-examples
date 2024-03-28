package Threads;

public class SynchronizedBlockDemo {
    private static int counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Runnable incrementTask = () -> {
            synchronized (lock) {
                for (int i = 0; i < 10000; i++) {
                    counter++;
                }
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);
        Thread thread3 = new Thread(incrementTask);
        Thread thread4 = new Thread(incrementTask);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter (with synchronized block): " + counter);
    }
}

