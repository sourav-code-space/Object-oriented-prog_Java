package ParkingSystem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Runs a simulation of Parking Enforcement at RIT.
 */
public class RITParking {
    /**
     * A pseudo-random number generator configured with a specific seed so
     * that the pseudo-random numbers generated are consistent from one run to
     * the next.
     */
    private static final Random RNG = new Random(1);

    /**
     * Runs a simulation for a single parking lot with the specified number of
     * spots over the specified number of days.
     *
     * @param numberOfSpots The number of spots in the simulated parking lot.
     * @param days          The number of days over which the simulation should be
     *                      executed.
     */
    public static void simulate(int numberOfSpots, int days) {
        // 30% of the spots are reserved, and 70% are general
        int reserved = (int) (0.3 * numberOfSpots);
        int general = numberOfSpots - reserved;
        // there are some vehicles that are not registered
        int noPermit = (int) (0.2 * numberOfSpots);
        // the total number of vehicles
        int total = reserved + general + noPermit;

        // create the new lot, "J Lot"
        ParkingLot lot = new ParkingLot('J', reserved, general);

        // initialize the fleet of vehicles
        Vehicle[] vehicles = new Vehicle[total];
        // used to assign a 7-digit plate number to each vehicle
        int lastPlate = 1000000;
        for (int v = 0; v < vehicles.length; v++) {
            Vehicle vehicle = new Vehicle(lastPlate++);

            if (v < reserved) {
                PATS.register(vehicle.getPlate(), Permit.RESERVED);
            } else if (v < (reserved + general)) {
                PATS.register(vehicle.getPlate(), Permit.GENERAL);
            }
            vehicles[v] = vehicle;
        }

        // create a parking officer
        ParkingOfficer officer = new ParkingOfficer();

        // used to determine the pseudo-random order in which the vehicles
        // will park in the lot
        ArrayList<Integer> vehicleNumbers = new ArrayList<>();
        for (int spot = 0; spot < total; spot++) {
            vehicleNumbers.add(spot);
        }

        // run the simulation for the correct number of days
        for (int day = 1; day <= days; day++) {
            System.out.println("\n**DAY " + day + "**");
            // predictably "shuffles" the order in which vehicles arrive
            Collections.shuffle(vehicleNumbers, RNG);
            // park vehicles in "random" order
            System.out.println("As the day begins, vehicles park in the lot...");

            // park all of the vehicles
            for (int vehicleNumber : vehicleNumbers) {
                // the lot WILL fill up before all of the vehicles have parked
                // this method should do nothing if the lot is already full
                lot.park(vehicles[vehicleNumber]);
            }

            // patrol the lot
            officer.patrol(lot, day);

            // vacate all of the spots in the lot
            System.out.println("As the day ends, vehicles vacate the lot...");
            lot.vacate();

            for (Vehicle vehicle : vehicles) {
                List<Ticket> tickets = vehicle.getTickets();
                if (tickets.size() > 0) {
                    System.out.println(vehicle.getPlate() + " was ticketed: "+
                            tickets);
                    vehicle.clearTickets();

                }
            }
        }

        // print final report
        System.out.println("\n\n********FINAL REPORT********");
        PATS.printReport(vehicles);
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.err.println("usage: java hw3.RITParking numberOfSpots days");
            System.exit(1); // error code
        }

        int numberOfSpots = Integer.parseInt(args[0]);
        int days = Integer.parseInt(args[1]);

        simulate(numberOfSpots, days);

    }
}
