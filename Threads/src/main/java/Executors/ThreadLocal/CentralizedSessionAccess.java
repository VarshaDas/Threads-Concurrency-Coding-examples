package Executors.ThreadLocal;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

class SessionManager {
    private static final SessionData sessionData = new SessionData("default");

    public static synchronized SessionData getSessionData() {
        return sessionData;
    }
}

public class CentralizedSessionAccess {

    private static final SessionData sessionData = new SessionData("10");  //global session data
    public static void main(String[] args) {

        // Approach 1: Modifying shared session data for global variable
        //Disadvantage: Race Condition
        System.out.println("Global Access");
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                // Access shared session data and print session ID
                System.out.println("Thread " + Thread.currentThread().getId() + " - Session ID: " + sessionData.getSessionId());

                // Simulate some processing time
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        // Approach 2: Create object for each thread(for synchronization)
        //Disadvantage: Performance issues
        System.out.println("Separate Object Access");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) { // Increased number of threads for heavier contention
            new Thread(() -> {
                // Access session data from the centralized location
                SessionData sessionData = SessionManager.getSessionData();
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

