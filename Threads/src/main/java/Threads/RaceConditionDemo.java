package Threads;

public class RaceConditionDemo {
    private static int sharedValue = 0;

    // Read-Modify-Write Race Condition
    public static void readModifyWriteRace() {
        for (int i = 0; i < 100000; i++) {
            // Read sharedValue
            int temp = sharedValue;
            // Simulate some computation
            temp++;
            // Write back the updated value to sharedValue
            sharedValue = temp;
        }
    }

    // Write-Write Race Condition
    public static void writeWriteRace() {
        sharedValue = 1; // Initial value
        // Simulate some computation
        sharedValue = 2; // New value
    }

    public static void main(String[] args) throws InterruptedException {
        // Create and start threads
        Thread thread1 = new Thread(() -> readModifyWriteRace());
        Thread thread2 = new Thread(() -> writeWriteRace());

        thread1.start();
        thread2.start();

        // Wait for threads to finish
        thread1.join();
        thread2.join();

        // Print the final value of sharedValue
        System.out.println("Final value of sharedValue: " + sharedValue);
    }
}

