package ParkingSystem.test;

import ParkingSystem.ParkingSpot;
import ParkingSystem.Permit;
import ParkingSystem.Vehicle;

import static ParkingSystem.test.Tests.assertEquals;
import static ParkingSystem.test.Tests.runAllTests;

public class ParkingSpotTest {
    static int plate=10000023;
    static ParkingSpot spot= new ParkingSpot(100, Permit.RESERVED);
    static Vehicle v= new Vehicle(plate);
    public static void testPark(){
        spot.park(v);
        assertEquals(v, spot.getVehicle());
        assertEquals(true, spot.getOccupancy());
    }

    public static void testVacate(){
        spot.park(v);
        spot.vacate();
        assertEquals(null, spot.getVehicle());
        assertEquals(false, spot.getOccupancy());
    }

    public static void main(String[] args) {
        runAllTests(ParkingSpotTest.class);
    }
}
