package PassengerBoarding.test;
import PassengerBoarding.LinkedQueue;
import PassengerBoarding.Passenger;

import static PassengerBoarding.test.Tests.assertEquals;
public class LinkedQueueTest {
    static String name = "Harry Potter";
    static String boardingGroup= "A";
    static int sequenceNumber=10;
    static Passenger p = new Passenger(name,boardingGroup,sequenceNumber);
    static LinkedQueue lq=new LinkedQueue();
    public static void testEnqueue() {
        lq.enqueue(p);
        assertEquals(false,lq.isEmpty());
    }

    public static void testDequeue() {
        lq.dequeue();
        assertEquals(true,lq.isEmpty());
    }

    public static void testIsEmpty() {
        assertEquals(true,lq.isEmpty());
    }
}
