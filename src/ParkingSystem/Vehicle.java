/*
 *
 * Vehicle.java
 *
 *
 * Version : 1.0		Date :- 02/04/2020
 *
 *
 * Revision : $Log$
 *
 */

/**
 * This class holds the information about the vehicle.Each vehicle is
 * identified by their platenumber.And we represent vehicle object using
 * platenumber and ticket received.
 *
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 *
 */
package ParkingSystem;

import java.util.List;

public class Vehicle {
    private int plateNumber;
    public Vehicle(int plateNumber){
        this.plateNumber=plateNumber;
    }
    public int getPlate(){
        return this.plateNumber;
    }
    public List<Ticket> getTickets(){
        return PATS.getTickets(this);
    }
    public void clearTickets(){
        PATS.clearTickets(this);
    }

    @Override
    public String toString() {
        return "Vehicle[" +
                "plate=" + plateNumber +", "+
                "tickets="+getTickets()+
                ']';
    }
}
