package Executors.CompletableFuture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ConcurrentFileReaderWithFuture {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = new ArrayList<>();

        List<String> fileNames = List.of("E:\\file1.txt", "E:\\file2.txt", "E:\\file3.txt");

        for (String fileName : fileNames) {
            Future<String> future = executor.submit(() -> 
                readFile(fileName));
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                String content = future.get();   // Blocking get() call
                System.out.println("File content: " + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Exception Handling 
        /*Future<String> future1 = executor.submit(() -> {
            try {
                return readFile("nonexistent.txt"); // Simulating non-existent file
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        });
        try {
            String content = future1.get();   // Blocking get() call
            System.out.println("File content: " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        //Multiple futures cannot be combined/chained together
        /*Future<String> future1 = executor.submit(() -> readFile("file1.txt"));
        Future<String> future2 = executor.submit(() -> readFile("file2.txt"));

        future1.future2.get();*/ 

        executor.shutdown();
    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            //TimeUnit.MINUTES.sleep(1);
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return content.toString();
    }
}

