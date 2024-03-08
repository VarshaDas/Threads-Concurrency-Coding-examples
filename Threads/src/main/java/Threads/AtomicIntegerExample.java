package Threads;

import java.util.concurrent.atomic.AtomicInteger;
public class AtomicIntegerExample {
    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    public static void main(String[] args) {
        // Creating multiple threads to increment the atomic counter
        Runnable atomicTask = () -> {
            for (int i = 0; i < 10000; i++) {
                atomicCounter.incrementAndGet(); // Atomic increment operation
            }
        };
        Thread thread1 = new Thread(atomicTask);
        Thread thread2 = new Thread(atomicTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Atomic Counter: " + atomicCounter.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

