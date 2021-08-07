package ParkingSystem.test;

import ParkingSystem.Ticket;
import ParkingSystem.TicketType;

import static ParkingSystem.test.Tests.assertEquals;
import static ParkingSystem.test.Tests.runAllTests;

public class TicketTest {
    public static void testConstructor() {
        int plateNumber=10000023;
        int day=2;
        TicketType type=TicketType.RESERVED;
        int fine=50;
        Ticket ticket = new Ticket(plateNumber,type,day);
        assertEquals(plateNumber, ticket.getPlateNumber());
        assertEquals(day, ticket.getDay());
        assertEquals(type, ticket.getType());
        assertEquals(fine, ticket.getFineAmount());
    }


    public static void main(String[] args) {
        runAllTests(TicketTest.class);
    }
}
