/*
 *
 * TollRecord.java
 *
 * Version : 1.0		Date :- 03/02/2020
 *
 * Revision : $Log$
 *
 */
package HighwayToll;

/**
 * This class represent a single vehicle trip on the highway.A toll record is
 * created when a vehicle arrives via an exit and includes the arrival/exit
 * number license tag and arrival time.
 * 
 * @author Sourav Khan(sk9675@rit.edu)
 *
 */
public class TollRecord implements Comparable<TollRecord>{
	// Vehicle arrival time
	private int time;
	// Vehicle license tag
	private String tag;
	// Vehicle arrival or departure lane
	private int exit;

	/**
	 * Default constructor
	 */
	public TollRecord() {

	}

	/**
	 * Parameterized Constructor for this call
	 * 
	 * @param time
	 * @param tag
	 * @param exit
	 */
	public TollRecord(int time, String tag, int exit) {
		super();
		this.time = time;
		this.tag = tag;
		this.exit = exit;

	}

	/**
	 * Upon invoking this method will return arrival/departure time of required
	 * TollRecord Object.
	 * 
	 * @return time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Upon invoking this method will return license tag of required TollRecord
	 * Object.
	 * 
	 * @return tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Upon invoking this method will return arrival/departure lane of required
	 * TollRecord Object.
	 * 
	 * @return exit
	 */
	public int getExit() {
		return exit;
	}

	/**
	 * This method is used to generate hash-code for a particular TollRecord. It
	 * will take into account all the fields associated with TollRecord.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exit;
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + time;
		return result;
	}

	/**
	 * This method checks for the content of the two TollRecord Objects. If two
	 * objects have same content entirely then only we return true else return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TollRecord other = (TollRecord) obj;
		if (exit != other.exit)
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (time != other.time)
			return false;
		return true;
	}

	/**
	 * This method is used to print TollRecord Object
	 */
	public String toString() {
		String toReturn = "";
		toReturn = "[" + getTag() + "] on #" + getExit() + ", time\t" + getTime();
		return toReturn;
	}
	
	/**
	 * This method is used to order TollRecord Object in increasing order
	 */
	@Override
	public int compareTo(TollRecord o) {
		// TODO Auto-generated method stub
		if(this.getTime() > o.getTime()) {
			return 1;
		}
		else if(this.getTime() < o.getTime()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
