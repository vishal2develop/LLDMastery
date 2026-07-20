import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        SeatAllocationStrategy strategy = new LowerBirthFirst();
        BookingSystem bookingSystem = BookingSystem.getInstance(strategy);

        // Create train with 2 sleeper coaches, 1 AC
        Train rajdhani = new Train("12301", "Rajdhani Express", "New Delhi", "Mumbai");
        rajdhani.addCoach(new Coach("S1", CoachType.SLEEPER, 72));
        rajdhani.addCoach(new Coach("S2", CoachType.SLEEPER, 72));
        rajdhani.addCoach(new Coach("B1", CoachType.AC, 48));
        rajdhani.addCoach(new Coach("D1", CoachType.GENERAL, 90));
        bookingSystem.addTrain(rajdhani);

        // print the train details
        System.out.println(rajdhani);



        // Concurrent booking simulation
        // Create a fixed thread pool with 10 threads
        ExecutorService pool = Executors.newFixedThreadPool(10);
        // futures to store the results of the bookings
        List<Future<Ticket>> futures = new ArrayList<>();
        // rotate across coach types so fares/berths vary instead of draining one queue
        CoachType[] coachTypes = {CoachType.SLEEPER, CoachType.AC, CoachType.GENERAL};
        int totalBookings = 40;
        // latch to wait for all bookings to complete
        CountDownLatch latch = new CountDownLatch(totalBookings);
        // book tickets concurrently, cycling through coach types
        for (int i = 0; i < totalBookings; i++) {
            final int id = i;
            final CoachType coachType = coachTypes[i % coachTypes.length];
            futures.add(pool.submit(() -> {
                Passenger p = new Passenger("P" + id, "Passenger " + id, 25 + id, "p" + id + "@email.com");
                try {
                    Ticket t = bookingSystem.bookTicket(p, "12301", coachType);
                    System.out.printf("[%s] %s -> %s Coach: %s Seat: %s Fare: %d%n", Thread.currentThread().getName(), t.getPnr(), t.getStatus(), coachType, t.getSeat() != null ? t.getSeat().getCoach().getCoachId() + "-" + t.getSeat().getSeatNumber() : "WAITLISTED", t.getFare());
                    return t;
                } finally {
                    // decrement the latch when a booking is complete
                    latch.countDown();
                }
            }));
        }
        // wait for all bookings to complete
        latch.await();
        System.out.println("\n=== All bookings complete ===");

        // Cancel first confirmed ticket
        Ticket firstTicket = futures.get(0).get();
        if (firstTicket.getStatus() == BookingStatus.CONFIRMED) {
            CancellationReceipt receipt = bookingSystem.cancelTicket(firstTicket);
            System.out.println("Cancelled: " + receipt);
        }
        // Shutdown the thread pool
        pool.shutdown();
    }
}
