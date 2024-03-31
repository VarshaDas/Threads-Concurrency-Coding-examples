package Threads;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();  // counter=1
        reentrantLock.lock();  // counter=2

        System.out.println(reentrantLock.isLocked());
        System.out.println(reentrantLock.isHeldByCurrentThread());

        reentrantLock.unlock();  // counter=1
        System.out.println(reentrantLock.isLocked());
        System.out.println(reentrantLock.getHoldCount());

        reentrantLock.lock();  // counter=2

        reentrantLock.unlock();  // counter=1
        System.out.println(reentrantLock.isLocked());
        System.out.println(reentrantLock.getHoldCount());

        reentrantLock.unlock();  // counter=0
        System.out.println(reentrantLock.isLocked());
        System.out.println(reentrantLock.getHoldCount());  // 0
    }
}
