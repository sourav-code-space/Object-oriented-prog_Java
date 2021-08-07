/*
 *
 * TollReport.java
 *
 * Version : 1.0		Date :- 03/02/2020
 *
 * Revision : $Log$
 *
 */
package HighwayToll;

import java.util.Scanner;

/**
 * This is the main executable class.It accepts single command-line argument
 * specifying the name of toll event data file. It creates toll record database
 * and generate full report and also provides user a command line interface
 * 
 * 
 * @author Sourav Khan(sk9675@rit.edu)
 *
 */
public class TollReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		if(args.length<1){
			System.out.println("Usage: java TollReport <FileName>");
			System.exit(0);
		}
		String fileName = args[0];
		TollRoadDatabase myTollRoadDatabase = new TollRoadDatabase(fileName);
		myTollRoadDatabase.SortValueInMap();
		System.out.println(myTollRoadDatabase.getCompleteTripCount());
		System.out.println("\nOn-Road Report");
		System.out.println("==============");
		System.out.println(myTollRoadDatabase.callOnRoadReport());
		System.out.println("\nSPEEDER REPORT");
		System.out.println("==============");
		System.out.println(myTollRoadDatabase.generateSpeederReport());
		System.out.println("BILLING INFORMATION");
		System.out.println("===================");
		System.out.println(myTollRoadDatabase.generateBillingInformation());

		do {

			System.out.println("\n\'b <string>\' to see bill for license tag");
			System.out.println("\'e <number>\' to see activity at exit");
			System.out.println("\'q\' to quit");
			System.out.print("> ");
			String str = sc.nextLine();
			String strArray[] = str.split(" ");
			if (strArray.length > 2 || (strArray[0].length() > 1 && (strArray[0].charAt(0) != 'b'
					|| strArray[0].charAt(0) != 'e' || strArray[0].charAt(0) != 'q'))) {
				System.out.println("Illegal command. Try again");
				continue;
			} else {

				char c = strArray[0].charAt(0);
				switch (c) {
				case 'b':
					String tag = strArray[1];

					System.out.println(myTollRoadDatabase.generateBillingInformation(tag));
					break;
				case 'e':
					int exit = Integer.parseInt(strArray[1]);
					if (!ExitInfo.isValid(exit)) {
						System.out.println("Illegal command. Try again");
						continue;
					}
					System.out.println(myTollRoadDatabase.generateExitReport(exit));
					break;
				case 'q':
					sc.close();
					System.exit(0);
				}
			}
			
		} while (true);
		
	}

}
