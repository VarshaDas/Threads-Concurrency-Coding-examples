package Executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Executors;

public class ProducerConsumerMultiple {
        public static void main(String[] args) {
            BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

            ExecutorService executorService = Executors.newFixedThreadPool(5);

            for (int i = 1; i <= 2; i++) {
                executorService.submit(new Producer(queue, i));
            }

            for (int i = 1; i <= 2; i++) {
                executorService.submit(new Consumer(queue, i));
            }

            // Shutdown the executor service after some time
            executorService.shutdown();
        }
    }

class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private int producerId;

    public Producer(BlockingQueue<Integer> queue, int producerId) {
        this.queue = queue;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                int value = produce(producerId) + i;
                queue.put(value);
                System.out.println("Producer " + producerId + " produced: " + value);
                Thread.sleep(1000); // Simulate some work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int produce(int i) {
        return (int) ( i * 100);
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    private int consumerId;

    public Consumer(BlockingQueue<Integer> queue, int consumerId) {
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        try {
            while (!queue.isEmpty()) {
                int value = queue.take();
                System.out.println("Consumer " + consumerId + " consumed: " + value);
                Thread.sleep(2000); // Simulate some work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


