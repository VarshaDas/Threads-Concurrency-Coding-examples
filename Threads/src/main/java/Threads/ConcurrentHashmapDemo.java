package Threads;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashmapDemo {
    private static final int NUM_THREADS = 5;
    private static final int NUM_INSERTIONS = 1000;
    private static ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        for(int i = 0; i<NUM_THREADS ;i++) {
            executorService.execute(insertIntoMap());
        }

        executorService.shutdown();

        if(!executorService.isTerminated()){
            Thread.sleep(3000);
        }

        System.out.println("Size of the HashMap: " + hashMap.size());
    }

    private static Runnable insertIntoMap() {
        return () -> {
            for(int i = 0; i<NUM_INSERTIONS;i++){
                hashMap.put(Thread.currentThread().getName() + i, i);
            }
        };
    }
}


