package Executors.CompletableFuture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ConcurrentFileReaderWithCompletableFuture {

    public static void main(String[] args) {
        List<String> fileNames = List.of("Threads/src/main/resources/file1.txt", "Threads/src/main/resources/file2.txt", "Threads/src/main/resources/file3.txt");

        List<CompletableFuture<String>> futures = fileNames.stream()
                .map(fileName -> CompletableFuture.supplyAsync(() -> readFile(fileName)))   //non-blocking
                .collect(Collectors.toList());


        //Combine all CompletableFutures into a single CompletableFuture that completes when all are done
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        //Perform action when all CompletableFutures are completed
        allFutures.thenRun(() -> {
            for (CompletableFuture<String> future : futures) {
                try {  //Handle exceptions that may occur during file reading
                    String content = future.get();
                    System.out.println("File content: " + content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).join();
    }

    private static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}

    

