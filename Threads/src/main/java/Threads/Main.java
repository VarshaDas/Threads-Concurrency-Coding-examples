package Threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        String file1Path = "/Users/vd056735/samplelogs1.txt";
//        String file2Path = "/Users/vd056735/samplelogs2.txt";
//
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        Future<Object> future1 = executorService.submit(new FileReaderTask(file1Path));
//        Future<Object> future2 = executorService.submit(new FileReaderTask(file2Path));
//
//        System.out.println("Future 1 "+future1.get());
//        System.out.println("Future 2 " +future2.get());
//
//
//        executorService.shutdown();
//
//        while (!future1.isDone() && !future2.isDone()){
//            Thread.sleep(3000);
//            System.out.println("not done");
//        }


        String strObj = new String("Hello!");
        String str = "Hello!";
        String str1 = "Hello!";
// The two string references point two strings that are equal
        if (str1.equals(strObj)) {
            System.out.println("The strings are equal");
        }
// The two string references do not point to the same object
//        if (strObj != str) {
//            System.out.println("The strings are not the same object");
//        }




    }
}
