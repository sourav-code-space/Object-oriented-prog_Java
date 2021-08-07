/*
 *
 * PriorityQueue.java
 *
 * Version : 1.0		Date :- 02/11/2020
 *
 * Revision : $Log$
 *
 */


package PassengerBoarding;
/**
 * This class represents an interface.Other Classes which will use this interface 
 * would be forced to implement the methods below described by this interface.
 * Interface PriorityQueue Defines 3 three methods essential for the application.
 * namely enqueue,dequeue,isEmpty.
 * 
 * 
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */

public interface PriorityQueue {
	 public void enqueue(Passenger toInsert);
	 public Passenger dequeue();
	 public boolean isEmpty();
}
