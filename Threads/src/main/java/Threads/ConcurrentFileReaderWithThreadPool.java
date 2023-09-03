package Threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentFileReaderWithThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        String[] filePaths = {
                "/Users/vd056735/samplelogs1.txt",
                "/Users/vd056735/samplelogs2.txt",
                "/Users/vd056735/samplelogs3.txt"
        };

        for(String filePath : filePaths){
            executorService.execute(() -> readFile(filePath));
        }

        executorService.shutdown();

    }

    private static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Thread.sleep(4000);
                System.out.println("file path =" + filePath + " " + Thread.currentThread().getName() + ": reads line " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


