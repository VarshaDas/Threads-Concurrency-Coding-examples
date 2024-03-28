package Threads;

public class SynchronizedExample {
    private int count = 0;
    private final Object lock = new Object(); //Object for synchronized block

    // Synchronized method
    public synchronized void synchronizedMethod() {
        System.out.println(Thread.currentThread().getName() + " enters synchronized method.");
        count++;
        System.out.println(Thread.currentThread().getName() + " increments count to: " + count);
    }

    public void synchronizedBlock() {
        System.out.println(Thread.currentThread().getName() + " enters synchronized block.");
        synchronized (lock) {   // Synchronized block
            count += 5;
            System.out.println(Thread.currentThread().getName() + " increments count to: " + count);
        }
    }

    public static void main(String[] args) {
        final SynchronizedExample example = new SynchronizedExample();

        // Creating and starting threads
        Thread thread1 = new Thread(() -> example.synchronizedMethod());
        Thread thread2 = new Thread(() -> example.synchronizedBlock());

        thread1.start();
        thread2.start();
    }
}
