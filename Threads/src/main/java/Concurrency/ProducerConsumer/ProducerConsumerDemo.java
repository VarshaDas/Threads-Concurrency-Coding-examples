package Concurrency.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 5;

        Thread producerThread = new Thread(() -> {
            for(int i = 0; i< 10; i++){
                synchronized (buffer){
                    //overflow check
                    while(buffer.size() == maxSize){
                        try {
                            System.out.println("buffer is full, so waiting");
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    buffer.add(i);
                    System.out.println("Produced "+i);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    buffer.notifyAll();
                }
            }
        }, "Producer");

        Thread consumerThread = new Thread(() -> {
            for(int i = 0; i< 10; i++){
                synchronized (buffer){
                    //underflow check
                    while(buffer.isEmpty()){
                        try {
                            System.out.println("buffer is empty, so waiting");
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int val = buffer.remove();
                    System.out.println("Consumed "+val);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    buffer.notifyAll();
                }
            }
        }, "Consumer");

        producerThread.start();
        consumerThread.start();



    }
}
