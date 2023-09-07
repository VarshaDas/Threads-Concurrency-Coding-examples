package Threads;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapDemo {
    private static final int NUM_THREADS = 5;
    private static final int NUM_INSERTIONS = 100;
    private static HashMap<String, Integer> hashMap = new HashMap<>();
    public static void main(String[] args) {
        Runnable insertTask = () -> {
            for (int i = 0; i < NUM_INSERTIONS; i++) {
                int key = i;
                int value = i;

                // Insert the key-value pair into the HashMap without synchronization
                hashMap.put(key + Thread.currentThread().getName(), value);
            }
        };

        // Create and start multiple threads to insert data concurrently
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(insertTask);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the size of the HashMap after concurrent insertions
        System.out.println("Size of the HashMap: " + hashMap.size());
    }
}


