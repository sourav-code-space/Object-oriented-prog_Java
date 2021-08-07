/*
 *
 * ParkingOfficer.java
 *
 *
 * Version : 1.0		Date :- 02/04/2020
 *
 *
 * Revision : $Log$
 *
 */

/**
 * This class depicts the behaviour of patrolling officer.Patrolling officer checks whether
 * the spot is occupied or not.If occupied then does the vehicle hold appropriate permit.If not
 * then issues a ticket.
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */
package ParkingSystem;

public class ParkingOfficer {

    void patrol(ParkingLot lot,int day){
        System.out.println("Officer begins patrolling "+lot.getName());
        ParkingSpot[] spots = lot.getParkingSpots();
        for(ParkingSpot spot:spots){
            System.out.println("Officer visits Parking Spot"+spot);
            if(spot.occupied){
                int plateNumber = spot.getVehicle().getPlate();
                System.out.println("\ta vehicle is in the spot: "+ spot.getVehicle());
                if(PATS.getVehiclePermit(plateNumber)==null){
                    PATS.createTicket(plateNumber, TicketType.NO_PERMIT, day);
                    System.out.println("\tthe vehicle is unregistered.");
                    System.out.println("\ta ticket is issued: Ticket[type="+TicketType.NO_PERMIT+", day="+day+", plate="+plateNumber+", fine=100.0]");
                }
                else if(spot.getPermit()!=PATS.getVehiclePermit(plateNumber)){
                    if((spot.getPermit()==Permit.RESERVED) &&(PATS.getVehiclePermit(plateNumber)==Permit.GENERAL)){
                        PATS.createTicket(plateNumber,TicketType.RESERVED, day);
                        System.out.println("\ta ticket is issued: " +
                                "Ticket[type="+TicketType.RESERVED+", day="+day+", " +
                                "plate="+plateNumber+", fine=50.0]");
                    }
                    else
                        System.out.println("\tthe vehicle is legally parked.");
                        continue;
                }
                else
                    System.out.println("\tthe vehicle is legally parked.");
                    continue;
            }
            else
                continue;
        }
    }
}
