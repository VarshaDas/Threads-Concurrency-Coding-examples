package Threads;

public class NoDeadlockExample {
    private static final Object varsha_key = new Object();
    private static final Object harsha_key = new Object();

    public static void main(String[] args) {
        Thread varsha = new Thread(() -> {
            synchronized (varsha_key) {
                System.out.println("Varsha has acquired her key");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Varsha is waiting for Harsha's key");
                synchronized (harsha_key) {
                    System.out.println("Varsha has acquired Harsha's key");
                }
            }
        });

        Thread harsha = new Thread(() -> {
            synchronized (varsha_key) { // Acquiring in the same order to prevent deadlock
                System.out.println("Harsha has acquired her key");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Harsha is waiting for Varsha's key");
                synchronized (harsha_key) {
                    System.out.println("Harsha has acquired Varsha's key");
                }
            }
        });

        varsha.start();
        harsha.start();
    }
}
