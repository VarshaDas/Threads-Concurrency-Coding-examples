package Threads;

public class TicketBookingSystem {
    private int availableTickets = 5;

    public void bookTickets(int numTickets) {
        if (numTickets <= availableTickets) { // Check if enough tickets are available
            // Simulate a delay in processing the booking
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            availableTickets -= numTickets; // Update the available tickets
            System.out.println(Thread.currentThread().getName() + " booked " + numTickets + " tickets.");
        } else {
            System.out.println(Thread.currentThread().getName() + " couldn't book tickets. Not enough available.");
        }
    }

    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        Runnable bookingTask = () -> {
            for (int i = 0; i < 3; i++) { // Each customer attempts to book 2 tickets
                bookingSystem.bookTickets(2);
            }
        };

        Thread thread1 = new Thread(bookingTask, "Customer 1");
        Thread thread2 = new Thread(bookingTask, "Customer 2");

        thread1.start();
        thread2.start();
    }
}
