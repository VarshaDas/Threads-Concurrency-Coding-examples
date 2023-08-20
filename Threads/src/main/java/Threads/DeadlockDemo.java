package Threads;

public class DeadlockDemo {

    public static void main(String[] args) {
        Object varsha_key = new Object();
        Object harsha_key = new Object();

        Thread varsha = new Thread(() -> {
            synchronized (harsha_key){
                System.out.println("Varsha has acquired harsha's key");
                try {
                    System.out.println("Varsha sleeping fr 3 secs");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("varsha woke up");
                synchronized (varsha_key){
                    System.out.println("varsha has got her key");
                }
            }
        });


        Thread harsha = new Thread(() -> {
            synchronized (harsha_key){
                System.out.println("Harsha has acquired varsha's key");
                try {
                    System.out.println("Harsha sleeping fr 3 secs");

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("harsha woke up");

                synchronized (varsha_key){
                    System.out.println("harsha has got her key");
                }
            }
        });

        varsha.start();
        harsha.start();
    }
}


