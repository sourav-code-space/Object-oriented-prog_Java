/*
 *
 * Passenger.java
 *
 * Version : 1.0		Date :- 02/11/2020
 *
 * Revision : $Log$
 *
 */

package PassengerBoarding;
/**
 * This class represents an actual passenger.
 * A Passenger has three attributes name,boarding group and sequence.
 * 
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */

public class Passenger {
	private String name;
	private String group;
	private int sequence;

	/**
	 * Default constructor
	 */
	public Passenger() {

	}

	/**
	 * Parameterized Constructor
	 * @param name
	 * @param group
	 * @param sequence
	 */
	public Passenger(String name, String group, int sequence) {
		this.name = name;
		this.group = group;
		this.sequence = sequence;
	}
	
	/**
	 * This method returns passenger name upon invoking on a particular 
	 * passenger object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method return passenger boarding group upon invoking on a particular 
	 * passenger object. 
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * This method return passenger sequence number upon invoking on a particular
	 * passenger object
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * This method defines Natural ordering for the Passenger object.
	 */
	public int compareTo(Passenger item) {
		// TODO Auto-generated method stub
		//Priority determination
		
		//if Both passenger are in same group, 
		//then sort them according to their sequence number
		if(this.getGroup().compareTo(item.getGroup()) == 0) {
			if(this.getSequence() < item.getSequence()) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else if(this.getGroup().compareTo(item.getGroup()) == 1){
			return -1;
		}
		else
			return 1;
			
		
	}

	

}
