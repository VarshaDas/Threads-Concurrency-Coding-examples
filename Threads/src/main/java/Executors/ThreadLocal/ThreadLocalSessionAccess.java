package Executors.ThreadLocal;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ThreadLocalSessionAccess {
    private static final ThreadLocal<SessionData> threadLocalSessionData = ThreadLocal.withInitial(() -> new SessionData(UUID.randomUUID().toString()));

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Simulate multiple threads accessing session data
        for (int i = 0; i < 100; i++) { // Increased number of threads for heavier contention
            new Thread(() -> {
                // Access session data using ThreadLocal
                SessionData sessionData = threadLocalSessionData.get();
                System.out.println("Thread " + Thread.currentThread().getId() + " - Session ID: " + sessionData.getSessionId());

                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " milliseconds");
    }
}

