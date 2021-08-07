/*
 *
 * ParkingSpot.java
 *
 *
 * Version : 1.0		Date :- 02/04/2020
 *
 *
 * Revision : $Log$
 *
 */

/**
 * This class does the job of parking the vehicle in the spot and vacating the spot.
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */

package ParkingSystem;

public class ParkingSpot {
    private int id;
    private Permit permit;
    public boolean occupied=false;
    private Vehicle vehicle;
    public ParkingSpot(int id, Permit permit){
        this.id=id;
        this.permit=permit;
    }
    public void park(Vehicle vehicle){
        this.vehicle=vehicle;
        this.occupied=true;
    }
    public void vacate(){
        this.vehicle=null;
        this.occupied=false;
    }
    public Permit getPermit(){
        return this.permit;
    }
    public boolean getOccupancy(){return  this.occupied;}
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public String toString(){
        return "[number="+id+", "+"permit="+permit+", "+"occupied" +
                "="+occupied+"]";
    }
}
