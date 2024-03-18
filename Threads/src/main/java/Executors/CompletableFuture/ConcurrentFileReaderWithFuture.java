package Executors.CompletableFuture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentFileReaderWithFuture {

    /**
     * Reads multiple files concurrently using Future interface.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();

        List<String> fileNames = List.of("Threads/src/main/resources/file1.txt", "Threads/src/main/resources/file2.txt", "Threads/src/main/resources/file3.txt");

        for (String fileName : fileNames) {
            Future<String> future = executor.submit(() -> readFile(fileName));
            System.out.println(future);
            futures.add(future);
        }

        for (Future<String> future : futures) {
            try {
                while(!future.isDone()){
                    Thread.sleep(5000);
                    System.out.println("future is not done");
                }
                String content = future.get();   // Blocking get() call
                System.out.println("File content: " + content);
            } catch (Exception e) {

                System.out.println(e.getCause());
            }
        }



        executor.shutdown();
    }

    /**
     * Reads the content of a file.
     *
     * @param fileName The name of the file to read.
     * @return The content of the file.
     * @throws IOException If an I/O error occurs.
     */
    private static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Thread.sleep(5000);
                content.append(line).append("\n");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
