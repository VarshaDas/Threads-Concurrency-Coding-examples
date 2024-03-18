package Executors.CompletableFuture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ConcurrentFileReaderWithCompletableFuture {

    public static void main(String[] args) throws InterruptedException {
        List<String> fileNames = List.of("Threads/src/main/resources/file1.txt", "Threads/src/main/resources/file2.txt", "Threads/src/main/resources/file3.txt");

        List<CompletableFuture<String>> futures = fileNames.stream()
                .map(fileName -> CompletableFuture.supplyAsync(() -> readFile(fileName)))
                .collect(Collectors.toList());


        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));


        allFutures.thenAccept(res -> {
            for (CompletableFuture<String> future : futures) {
                try {
                    String content = future.get(); // This will not block because the futures are already completed
                    System.out.println("File content: " + content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            System.out.println("Main thread executing task " + i);
            Thread.sleep(1000);
        }


    }

    private static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Thread.sleep(1000);
                content.append(line).append("\n");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
