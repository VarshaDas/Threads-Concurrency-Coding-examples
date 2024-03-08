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
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = new ArrayList<>();

        List<String> fileNames = List.of("file1.txt", "file2.txt", "file3.txt");

        // Issue: Blocking Call with get()
        // - The get() calls inside the loop block the main thread until each future completes its task.
        for (String fileName : fileNames) {
            Future<String> future = executor.submit(() -> readFile(fileName));
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

        // Exception Handling
        // Issue: Limited Exception Handling
        // - The get() method throws checked exceptions, leading to cumbersome exception handling,
        // especially when dealing with multiple futures.
        /*
        Future<String> future1 = executor.submit(() -> {
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
        }
        */

        // Multiple futures cannot be combined/chained together
        // Issue: Inability to Combine Futures
        // - Future doesn't provide a straightforward way to combine or chain multiple futures together,
        // making it difficult to handle dependencies between tasks.
        /*
        Future<String> future1 = executor.submit(() -> readFile("file1.txt"));
        Future<String> future2 = executor.submit(() -> readFile("file2.txt"));

        future1.future2.get();
        */

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
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
