/*
 *
 * Flight.java
 *
 * Version : 1.0		Date :- 02/11/2020
 *
 * Revision : $Log$
 *
 */
package PassengerBoarding;

import java.util.Scanner;

/**
 * This class simulates the Airline Passenger Boarding System.
 * 
 * 
 * @author Sourav Khan	(sk9675@rit.edu)
 *
 */

public class Flight {
	//static PriorityQueue reference = new HeapQueue();
	static PriorityQueue reference = new LinkedQueue();

	/*
	 * The Default constructor for the flight class
	 */
	public Flight() {
		
	}
	
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter an option");
		System.out.println("1 to Add a passenger to the queue");
		System.out.println("2 to Remove and Print the first passenger");
		System.out.println("3 to quit");
		
		//validate user input choice could be 1 , 2 or 3 only.
		do {
			System.out.print("Your choice:");
			int choice = sc.nextInt();
			while(choice != 1 && choice != 2 && choice != 3) {
				System.out.println("Invalid Input. Excepted Integer in range 1 to 3");
				System.out.print("Your choice:");
				choice = sc.nextInt();
			}
			
			
			
			switch (choice) {
			case 1:
				// call method to add passenger to queue
				System.out.print("Passenger name: ");
				String name = sc.next();
				System.out.print("Boarding Group: ");
				String group = sc.next();
				//validate user input boarding group can be A ,B or C.
				while(!group.equals("A") && !group.equals("B") && !group.equals("C")) {
					System.out.println("Invalid Input.Only A,B,C accepted.");
					System.out.print("Boarding Group: ");
					group = sc.next();
				}
				System.out.print("Sequence: ");
				int sequence = sc.nextInt();
				//validate user input sequence should be in between 1 to 60.
				while(sequence < 1 || sequence > 60) {
					System.out.println("Invalid Input. Required Integer in range 1 to 60");
					System.out.print("Sequence: ");
					sequence = sc.nextInt();
				}
			
				

				Passenger passenger = new Passenger(name, group, sequence);
				//add passenger to the queue
				reference.enqueue(passenger);
				break;
			
			case 2:
				// Remove and Print the first passenger
				//perform dequeue -> we get passenger object
				Passenger returnedPassenger = reference.dequeue();
				if(returnedPassenger != null) {
					System.out.println("Removing : "+ returnedPassenger.getName()+" in seat "+returnedPassenger.getGroup()+""+returnedPassenger.getSequence());
				}
				else {
					System.out.println("Queue is empty");
				}
				break;
			
			case 3:
				// quit the simulation
				System.exit(0);

			}
			
		}
		while(true);
		
	}
}

