package Threads;

public class PrintNumbersAndAlphabetsBruteForce {

    // Print odd and even numbers alternately using 2 threads
    private static boolean printLetter = true;

    private static final Object lock = new Object();
    public static void main(String[] args) {
        Runnable letterTask = new Runnable() {
            @Override
            public void run() {
                for(char ch = 'A'; ch <= 'Z'; ch++) {
                    synchronized (lock) {
                        if(!printLetter){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        System.out.print(" "+ch);
                        printLetter = false;
                        lock.notify();
                    }
                }
            }
        };


        Runnable numberTask = new Runnable() {
            @Override
            public void run() {
                for (int num = 1; num <= 26; num++) {
                    synchronized (lock) {
                        if (printLetter) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        System.out.print(" " + num);
                        printLetter = true;
                        lock.notify();
                    }
                }
            }
        };

        Thread letterThread = new Thread(letterTask);
        Thread numberThread = new Thread(numberTask);


        letterThread.start();
        numberThread.start();


        try {
            letterThread.join();
            numberThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
