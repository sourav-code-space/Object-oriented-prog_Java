/*
 *
 * TollRoadDatabase.java
 *
 * Version : 1.0		Date :- 03/02/2020
 *
 * Revision : $Log$
 *
 */
package HighwayToll;

import static HighwayToll.FileHandler.open;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class keeps the track of TollRecords.It generates a summary of Complete
 * trip as string.On road report as string.billing report as a string.And it
 * also generates speeder report for vehicle that crossed speed limit
 * 
 * 
 * @author Sourav Khan(sk9675@rit.edu)
 *
 */
public class TollRoadDatabase {

	/*
	 * Tree map Data structure is used for the operation license tag of the vehicle
	 * is form the keys of the map. Each key in the map is associated with an
	 * array-list of TollRecord objects
	 * 
	 */
	private Map<String, List<TollRecord>> map = new TreeMap<String, List<TollRecord>>();
	private List<TollRecord> myList;

	/*
	 * Tree map is used to keep track of uncompleted trips
	 */
	private Map<String, TollRecord> map2 = new TreeMap<String, TollRecord>();

	// variable to keep track of total completed trip

	private static int totalCount;

	/**
	 * Default constructor
	 */
	public TollRoadDatabase() {

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param fileName
	 */
	public TollRoadDatabase(String fileName) {
		super();
		for (String line : open(fileName)) {
			String data[] = line.trim().split(",");
			int time = Integer.parseInt(data[0]);
			String tag = data[1];
			int exit = Integer.parseInt(data[2]);
			if (!map.containsKey(tag)) {
				myList = new ArrayList<>();
				map.put(tag, myList);
			}
			myList = map.get(tag);
			TollRecord myTollRecordObject = new TollRecord(time, tag, exit);
			// Populate map and avoid duplicate entries
			boolean found = false;
			if (myList.size() > 0) {
				for (int i = 0; i < myList.size(); i++) {
					if (myTollRecordObject.equals(myList.get(i))) {
						found = true;
						break;
					}
				}
			}
			if (found == true) {
				continue;
			}
			myList.add(myTollRecordObject);

		}

	}

	/**
	 * This method sorts value in tree map based on time.
	 */
	public void SortValueInMap() {
		for(Map.Entry<String, List<TollRecord>> entry: map.entrySet()) {
			Collections.sort(entry.getValue());
		}

	}
	
	/**
	 * This method analyze whole data present in map and return the total number of
	 * completed trips
	 * 
	 * @return String
	 */
	public String getCompleteTripCount() {

		for (Map.Entry<String, List<TollRecord>> entry : map.entrySet()) {
			int count = entry.getValue().size();
			if (count % 2 != 0) {
				map2.put(entry.getKey(), entry.getValue().get(count - 1));
				count = count - 1;

			}
			count = count / 2;
			totalCount += count;

		}
		return totalCount + " completed trips";
	}

	/**
	 * This method analyze our map2 data and generates onRoad report. That is it
	 * provides us the information regarding uncompleted/on-going trips.
	 * 
	 * @return String
	 */
	public String callOnRoadReport() {
		String toReturn = "";
		for (Map.Entry<String, TollRecord> entry : map2.entrySet()) {
			TollRecord obj = entry.getValue();

			toReturn += String.format(TollsRUs.INCOMPLE_TOLL_RECORD_FORMAT, obj.getTag(), obj.getExit(), obj.getTime())
					+ TollsRUs.NL;

		}
		return toReturn;
	}

	/**
	 * This method analyzes our map data and generates speeder report for completed
	 * trips
	 * 
	 * @return String
	 */
	public String generateSpeederReport() {
		String toReturn = "";
		for (Map.Entry<String, List<TollRecord>> entry : map.entrySet()) {
			for (int i = 0; i < entry.getValue().size() - 1; i = i + 2) {
				TollRecord obj1 = entry.getValue().get(i);
				TollRecord obj2 = entry.getValue().get(i + 1);
				double distanceInMiles = Math
						.abs(ExitInfo.getMileMarker(obj1.getExit()) - ExitInfo.getMileMarker(obj2.getExit()));
				double timeInHours = (double) (obj2.getTime() - obj1.getTime()) / TollsRUs.MINUTES_PER_HOUR;
				double speed = distanceInMiles / timeInHours;

				if (speed > TollsRUs.SPEED_LIMIT) {
					toReturn += "Vehicle " + obj1.getTag() + ", starting at time " + obj1.getTime() + "\n\tfrom "
							+ ExitInfo.getName(obj1.getExit()) + "\n\tto " + ExitInfo.getName(obj2.getExit()) + "\n\t"
							+ String.format(TollsRUs.SPEED_FORMAT, speed) + TollsRUs.NL;
				}

			}
		}
		return toReturn;

	}

	/**
	 * This method generates billing information for all license tags
	 * 
	 * @return String
	 */
	public String generateBillingInformation() {
		String toReturn = "";
		double total = 0;
		for (Map.Entry<String, List<TollRecord>> entry : map.entrySet()) {
			int size = entry.getValue().size();
			if (size % 2 != 0) {
				size -= 1;
			}
			for (int i = 0; i < size; i += 2) {
				TollRecord obj1 = entry.getValue().get(i);
				TollRecord obj2 = entry.getValue().get(i + 1);
				toReturn += String.format(TollsRUs.COMPLETE_TOLL_RECORD_FORMAT, obj1.getTag(), obj1.getExit(),
						obj1.getTime(), obj2.getExit(), obj2.getTime()) + ": "
						+ String.format(TollsRUs.DOLLAR_FORMAT, ExitInfo.getToll(obj1.getExit(), obj2.getExit()))
						+ TollsRUs.NL;
				total += ExitInfo.getToll(obj1.getExit(), obj2.getExit());
			}
		}
		toReturn += "Total: " + String.format(TollsRUs.DOLLAR_FORMAT, total);
		return toReturn;
	}

	/**
	 * This method generates billing information for a particular license tag
	 * 
	 * @param tag
	 * @return String
	 */
	public String generateBillingInformation(String tag) {
		String toReturn = "";
		double total = 0;
		List<TollRecord> myObj = map.get(tag);
		if (myObj != null) {
			int size = myObj.size();
			if (size % 2 != 0) {
				size -= 1;
			}

			for (int i = 0; i < size; i += 2) {
				TollRecord obj1 = myObj.get(i);
				TollRecord obj2 = myObj.get(i + 1);

				toReturn += String.format(TollsRUs.COMPLETE_TOLL_RECORD_FORMAT, obj1.getTag(), obj1.getExit(),
						obj1.getTime(), obj2.getExit(), obj2.getTime()) + ": "
						+ String.format(TollsRUs.DOLLAR_FORMAT, ExitInfo.getToll(obj1.getExit(), obj2.getExit()))
						+ TollsRUs.NL;
				total += ExitInfo.getToll(obj1.getExit(), obj2.getExit());
			}

		}
		toReturn += "\nVehicle total due: " + String.format(TollsRUs.DOLLAR_FORMAT, total);

		return toReturn;
	}

	/**
	 * This method generates exit report for particular exit on highway
	 * 
	 * @param exit
	 * @return String
	 */
	public String generateExitReport(int exit) {
		String toReturn = "";
		System.out.println("\nEXIT " + exit + " REPORT");
		System.out.println("==============");
		for (Map.Entry<String, List<TollRecord>> entry : map.entrySet()) {
			int size = entry.getValue().size();
			if (size % 2 != 0) {
				size -= 1;
			}
			for (int i = 0; i < size; i += 2) {
				TollRecord obj1 = entry.getValue().get(i);
				TollRecord obj2 = entry.getValue().get(i + 1);
				if (obj1.getExit() == exit || obj2.getExit() == exit) {

					toReturn += String.format(TollsRUs.COMPLETE_TOLL_RECORD_FORMAT, obj1.getTag(), obj1.getExit(),
							obj1.getTime(), obj2.getExit(), obj2.getTime()) + TollsRUs.NL;
				}
			}
		}
		for (Map.Entry<String, TollRecord> entry : map2.entrySet()) {
			TollRecord obj = entry.getValue();
			if (obj.getExit() == exit) {

				toReturn += String.format(TollsRUs.INCOMPLE_TOLL_RECORD_FORMAT, obj.getTag(), obj.getExit(),
						obj.getTime()) + TollsRUs.NL;
			}
		}
		return toReturn;
	}

}
