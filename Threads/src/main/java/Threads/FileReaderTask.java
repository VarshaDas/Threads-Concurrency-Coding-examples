package Threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;


/*
What is the purpose of the Future interface in Java?
How does the Callable interface differ from the Runnable interface in Java?
What is the role of the submit() method in ExecutorService, and how does it relate to Callable?
How does the isDone() method in the Future interface help in handling asynchronous computations?
What does the get() method of the Future interface do, and how does it differ from isDone()?
Explain how you can handle exceptions thrown by a task executed through ExecutorService and Callable.
In what scenarios would you use the Future interface over the Runnable interface for handling asynchronous tasks?
How can you create a thread pool and use it to execute Callable tasks using ExecutorService?
How would you ensure that a task submitted to ExecutorService completes successfully before moving on to the next task?
Can you demonstrate the use of Future and Callable to concurrently read and process data from multiple files in Java?
 */
public class FileReaderTask implements Callable {

    String filePath;

    public FileReaderTask(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Object call() throws Exception {

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getName() + ": reads line  " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }


}
