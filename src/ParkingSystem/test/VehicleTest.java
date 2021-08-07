package ParkingSystem.test;

import ParkingSystem.PATS;
import ParkingSystem.Ticket;
import ParkingSystem.TicketType;
import ParkingSystem.Vehicle;

import java.util.List;

import static ParkingSystem.test.Tests.assertEquals;
import static ParkingSystem.test.Tests.runAllTests;

public class VehicleTest {
    public static void testConstructor() {
        int plateNumber=100000012;
        Vehicle vehicle = new Vehicle(plateNumber);
        assertEquals(plateNumber, vehicle.getPlate());
    }
    public static void testGetTickets(){
        int plate=1000000099;
        TicketType type=TicketType.RESERVED;
        int day =1;
        PATS.createTicket(plate, type, day);
        List<Ticket> tickets=PATS.getTickets(new Vehicle(plate));
        String expected=
                "[Ticket[type="+type+", day="+day+", plate="+plate+", " +
                        "fine=50]]";
        assertEquals(expected, tickets.toString());
    }

    public static void main(String[] args) {
        runAllTests(VehicleTest.class);
    }

}
