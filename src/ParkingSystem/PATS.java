/*
 *
 * PATS.java
 *
 *
 * Version : 1.0		Date :- 02/04/2020
 *
 *
 * Revision : $Log$
 *
 */

/**
 * This class perform a part of simulation where it register a vehicle.
 * Maintains a record of tickets. and prints the report.
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */

package ParkingSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PATS {
    private static HashMap<Integer, Permit> registration = new HashMap<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static List<Ticket> ticketHistory = new ArrayList<>();

    /**
     * This method based on its input parameter tries to register a vehicle
     * by maintaining data in hashmap.
     *
     * @param plateNumber     int      vehicle platenumber
     *
     * @param permit          Permit     the permit vehicle holds.
     */
    public static void register(int plateNumber, Permit permit){
        registration.put(plateNumber,permit);
    }
    public static Permit getVehiclePermit(int plateNumber){
        if(isRegistered(plateNumber))
            return registration.get(plateNumber);
        else
            return null;
    }
    private static boolean isRegistered(int plateNumber){
        if(registration.containsKey(plateNumber))
            return true;
        else
            return false;
    }
    public static void createTicket(int plateNumber, TicketType type, int day){
        tickets.add(new Ticket(plateNumber,type,day));
    }
    public static List<Ticket> getTickets(Vehicle vehicle){
        List<Ticket> temp = new ArrayList<>();
        for(Ticket ticket: tickets){
            if(ticket.getPlateNumber()==vehicle.getPlate())
                temp.add(ticket);
        }
        return temp;
    }

    public static void clearTickets(Vehicle vehicle){
        ticketHistory.addAll(getTickets(vehicle));
        tickets.removeAll(getTickets(vehicle));
    }
    public static void printReport(Vehicle[] vehicleList ){

        for(Vehicle vehicle:vehicleList){
            boolean ticketExist=false;
            System.out.print("\n"+vehicle.getPlate()+":");
            //System.out.println("****************");
            for(Ticket ticket:ticketHistory){
                if(ticket.getPlateNumber()==vehicle.getPlate()) {
                    System.out.print(ticket);
                    ticketExist=true;
                }
            }
            if(!ticketExist)
                System.out.print("(no ticket)");
        }
    }
}
