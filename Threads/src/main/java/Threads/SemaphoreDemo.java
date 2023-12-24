package Threads;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

        private static final int NUM_THREADS = 5;
        private static final Semaphore semaphore = new Semaphore(3); // Binary semaphore with 2 permits

        public static void main(String[] args) {
            for (int i = 0; i < NUM_THREADS; i++) {
                new Thread(() -> {
                    try {
                        semaphore.acquire();

                        Thread.sleep(3000);

                        // Critical section
                        System.out.println("Thread " + Thread.currentThread().getId() + " is in the critical section with "+ semaphore.availablePermits());

                        Thread.sleep(8000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }).start();
            }
        }
    }
