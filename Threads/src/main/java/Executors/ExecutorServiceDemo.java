package Executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*

Implement a Java program to show how to use the Executor framework and ThreadPoolExecutor
 to execute multiple tasks concurrently.

 Write a program to create a fixed-size thread pool and submit multiple tasks to it.
 Ensure that all tasks are executed concurrently.


In this example, we create a ThreadPoolExecutor using the Executors.newFixedThreadPool() method,
which creates a thread pool with a fixed number of threads (in this case, 3).
We then submit 5 tasks to the executor using the submit() method. Each task is represented by a
lambda expression that prints a start message, simulates some work by sleeping for 2 seconds,
and then prints a completion message.

After submitting all tasks, we call shutdown() on the executor to gracefully shut it down once all
tasks are completed. This ensures that the program does not terminate before all tasks have finished executing.

When you run this program, you will see that the tasks are executed concurrently by the thread pool,
with up to three tasks running simultaneously. The output may vary, but you should observe the tasks
 starting and completing in a non-sequential order.

The use of the Executor framework and ThreadPoolExecutor in this program allows for efficient and
concurrent execution of tasks, leveraging the thread pool to manage the thread resources and handle
the scheduling and execution of the submitted tasks.
 */
public class ExecutorServiceDemo {

        public static void main(String[] args) {
//             Create a ThreadPoolExecutor with a fixed number of threads
            ExecutorService executor = Executors.newFixedThreadPool(3);

            for (int i = 1; i <= 5; i++) {
                final int taskNumber = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskNumber + " started");
                    // Perform some task
                    try {
                        Thread.sleep(4000); // Simulate some work
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task " + taskNumber + " completed");
                });
            }

            // Shutdown the executor once all tasks are submitted
            executor.shutdown();

//            final int n = 10;
//            Callable<Integer> sumTask = () -> {
//                int sum = 0;
//                for (int i = 1; i <= n; i++)
//                    sum += i;
//                return sum;
//            };
//            System.out.println(sumTask);
        }
    }


