/*
 *
 * Ticket.java
 *
 *
 * Version : 1.0		Date :- 02/04/2020
 *
 *
 * Revision : $Log$
 *
 */

/**
 * This class includes an enum to define our ticket type issued by the patrolling officer.
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */
package ParkingSystem;

public class Ticket {
    private int ticketNumber;
    private static int ticketCount=0;

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public int getDay() {
        return day;
    }

    public TicketType getType() {
        return type;
    }

    private int fineAmount;
    private int plateNumber;
    int day;
    private TicketType type;
    public Ticket(int plateNumber, TicketType type, int day){
        this.ticketNumber=ticketCount++;
        this.plateNumber=plateNumber;
        this.day=day;
        this.type=type;
        if(type.equals(TicketType.NO_PERMIT)){
            this.fineAmount=100;
        }
        else{
            this.fineAmount=50;
        }
    }
    public int getPlateNumber(){
        return this.plateNumber;
    }
    @Override
    public String toString() {
        return "Ticket[" +
                "type="+ this.type+", "+
                "day="+ this.day+", "+
                "plate=" + this.plateNumber+", "+
                "fine=" + this.fineAmount+
                "]";
    }

}
