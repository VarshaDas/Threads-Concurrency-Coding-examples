package Threads;
public class CheckThenActRaceCondition {
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            if (!flag) {
                flag = true;
                System.out.println("Thread 1 set the flag.");
            }
        });

        Thread thread2 = new Thread(() -> {
            if (!flag) {
                flag = true;
                System.out.println("Thread 2 set the flag.");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
