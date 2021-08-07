package ParkingSystem.test;

import ParkingSystem.*;

import static ParkingSystem.test.Tests.assertEquals;
import static ParkingSystem.test.Tests.runAllTests;

public class PatsTest {
    static int plate=10000023;
    static Permit permit=Permit.RESERVED;
    public static void testRegister(){
        PATS.register(plate,permit);
        assertEquals(permit, PATS.getVehiclePermit(plate));
    }

    public static void testClearTickets(){
        int plate=1000000099;
        Vehicle v= new Vehicle(plate);
        TicketType type=TicketType.RESERVED;
        int day =1;
        PATS.createTicket(plate, type, day);
        PATS.clearTickets(v);
        assertEquals("[]",PATS.getTickets(v).toString());
    }
    public static void main(String[] args) {
//        testRegister();
//        testClearTickets();
        runAllTests(PatsTest.class);
    }
}
