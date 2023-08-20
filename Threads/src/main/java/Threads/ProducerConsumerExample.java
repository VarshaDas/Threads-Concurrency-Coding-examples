package Threads;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {

    public static void main(String[] args) {
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 2;
        Thread producer = new Thread(new Producer(buffer, maxSize), "Producer");
        Thread consumer = new Thread(new Consumer(buffer, maxSize), "Consumer");
        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {

    private final Queue<Integer> buffer;
    private final int maxSize;


    public Producer(Queue<Integer> buffer, int maxSize) {
        this.buffer = buffer;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int count = 0;
        for(int i = 0; i<10;i++){

            synchronized (buffer) {
                while (buffer.size() == maxSize) {
                    try {
                        System.out.println("Queue is full, waiting");
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = (int) (Math.random() * 100);
                System.out.println("Produced: " + i);
                buffer.add(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                buffer.notifyAll();
            }

        }
    }
}

class Consumer implements Runnable {

    private final Queue<Integer> buffer;
    private final int maxSize;
    int iterations = 10;

    public Consumer(Queue<Integer> buffer, int maxSize) {
        this.buffer = buffer;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int count = 0;

        for(int i = 0; i<10;i++){
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        System.out.println("Queue is empty, waiting");
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Consumed: " + buffer.remove());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                buffer.notifyAll();
            }
        }
    }
}

