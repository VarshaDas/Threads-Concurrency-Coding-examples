package Threads;

public class OddEvenPrinter {
    static Object lock = new Object();
    static boolean flag = false;
    static int n = 50;
    static int sharedcount = 0;

    public static void main(String[] args) {

        Runnable evenTask = new Thread(new Runnable() {
            @Override
            public void run() {
                while (sharedcount <= 50) {
//                    synchronized (lock) {

                        if (sharedcount % 2 != 0) {
                            try {
//                                lock.wait();
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + " : " + sharedcount);
                        sharedcount++;
//                        lock.notifyAll();
                    }
                }
//            }
        });

        Runnable oddTask = new Thread(new Runnable() {
            public void run() {
                while (sharedcount <= 50) {
//                    synchronized (lock) {

                        if (sharedcount % 2 != 1) {
                            try {
                                Thread.sleep(2000);
//                                lock.wait();
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + " : " + sharedcount);
                        sharedcount++;
//                        lock.notifyAll();
                    }
                }
//            }

        }, "oddrunner");

       Thread even = new Thread(evenTask, "even");

        Thread odd = new Thread(oddTask, "odd");

        even.start();
        odd.start();

        try {
            even.join();
            odd.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
