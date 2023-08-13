package Threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
You are given the task of developing a generic concurrent file reader program that can read data from multiple text files concurrently using multiple threads.
The program should be able to handle any type of data stored in the files and process it accordingly.

Your goal is to design and implement a solution that allows seamless concurrent reading of data from two different files, and
 simultaneously process and print each data entry alongside the thread's name.
 */
public class ConcurrentFileReaderExample {


    public static void main(String[] args) {
        // File paths for two files to read
        String file1Path = "/Users/vd056735/samplelogs1.txt";
        String file2Path = "/Users/vd056735/samplelogs2.txt";

        // Shared StringBuilder to store the output from both files
//        StringBuilder result = new StringBuilder();

        // Create two threads, one for each file
        Thread file1Thread = new Thread(() -> readFile(file1Path));
        Thread file2Thread = new Thread(() -> readFile(file2Path));

        // Start both threads
        file1Thread.start();
        file2Thread.start();

        // Wait for both threads to complete
        try {
            file1Thread.join();
            file2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the combined output
//        System.out.println(result.toString());
    }

    private static void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
//                content.append(line).append("\n");
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + ": reads line  "+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
