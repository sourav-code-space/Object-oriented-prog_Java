/*
 *
 * ParklingLot.java
 *
 *
 * Version : 1.0		Date :- 02/04/2020
 *
 *
 * Revision : $Log$
 *
 */

/**
 * This class tries to create parking spot with appropriate spot permit.
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 * @author Kundan Patil (kp4677@rit.edu)
 *
 */

package ParkingSystem;

public class ParkingLot {
    private String name;
    private int reserved;
    private int general;
    private ParkingSpot[] spots;
    public ParkingLot(char name, int reserved, int general){
        this.name=new String(name+"Lot");
        this.reserved=reserved;
        this.general=general;
        this.spots=new ParkingSpot[reserved+general];
        this.createParkingSpots(this.reserved, this.general);
    }
    /**
     * This method takes two integer parameter and using those create parking spots.
     *
     * @param reserved no of reserved spot
     * @param general no of general spot
     * */
    public void createParkingSpots(int reserved, int general){
        for(int i=0;i<reserved;i++){
            spots[i]=new ParkingSpot(i, Permit.RESERVED);
        }
        for(int i=reserved;i<reserved+general;i++){
            spots[i]=new ParkingSpot(i, Permit.GENERAL);
        }
    }
    public void park(Vehicle vehicle){
        for(ParkingSpot spot:spots){
            if(spot.occupied==false){
                spot.park(vehicle);
                break;
            }
        }
    }
    public String getName(){
        return this.name;
    }
    public void vacate(){
        for(ParkingSpot spot: spots){
            spot.vacate();
        }
    }
    public ParkingSpot[] getParkingSpots(){
        return this.spots;
    }
    public String toString(){
        return name+" Lot";
    }
}
