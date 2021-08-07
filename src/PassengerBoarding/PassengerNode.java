/*
 *
 * PassengerNode.java
 *
 * Version : 1.0		Date :- 02/11/2020
 *
 * Revision : $Log$
 *
 */

package PassengerBoarding;

/**
 * This class is used to describe the passenger node.A passenger node holds 
 * passenger object and its prev and next pointer to the passenger object 
 * if present.  
 *
 * @author Sourav Khan	(sk9675@rit.edu)
 * 
		
 *
 */


public class PassengerNode<Passenger> {
    Passenger item;
    PassengerNode<Passenger> next;
    PassengerNode<Passenger> prev;

    /**
     * Default Constructor
     */
    PassengerNode(){
    	
    }
    
    /**
     * Parameterized Constructor for PassengerNode
     * @param prev
     * @param element
     * @param next
     */
    PassengerNode(PassengerNode<Passenger> prev, Passenger element, PassengerNode<Passenger> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
