package Threads;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashmapDemo {
    private static final int NUM_THREADS = 5;
    private static final int NUM_INSERTIONS = 100;

    private static ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.execute(insertRecord());
        }

        executorService.shutdown();

        if (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }

        System.out.println("Size of the hashmap = " + hashMap.size());
    }

    private static Runnable insertRecord() {
        return () -> {
            for (int i = 0; i < NUM_INSERTIONS; i++) {
                hashMap.put(i + Thread.currentThread().getName(), i);
            }
        };
    }
}
