package Threads;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int data = 0;

    public void write(int newData) {
        lock.writeLock().lock();
        try {
            data = newData;
            System.out.println(Thread.currentThread().getName() + " wrote data: " + newData);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read data: " + data);
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo sharedResource = new ReadWriteLockDemo();

        // Reader threads
        for (int i = 1; i <= 3; i++) {
            Thread readerThread = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    sharedResource.read();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Reader-" + i);
            readerThread.start();
        }

        // Writer thread
        Thread writerThread = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                sharedResource.write(i);
                try {
                    Thread.sleep(3000); // Simulate infrequent writes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Writer");
        writerThread.start();
    }
}
