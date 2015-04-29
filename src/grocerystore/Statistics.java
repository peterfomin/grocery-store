package grocerystore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Peter Fomin and Zach Gartner
 * 
 *         Inspired By Dovolis Car Wash Simulation
 * 
 *         Class Analog: Stat
 *
 */

// All statistics tracking is done here
public class Statistics {
	// class variables
	private static int arrivals;
	private static double[] busyTime = new double[StoreSim.checkers.length];
	private static double[] lastUpdateTime = new double[StoreSim.checkers.length];
	private static double[] idleTime = new double[StoreSim.checkers.length];
	private static double[] maxWaitlineLength = new double[StoreSim.checkers.length];
	private static double[] averageWaitlineLength = new double[StoreSim.checkers.length];
	private static double totalSystemTime;
	private static double[] oldWaitlineLength = new double[StoreSim.checkers.length];
	private static double[] lastWaitlineUpdateTime = new double[StoreSim.checkers.length];
	private static double averageCheckoutTime;
	private static double maxCheckoutTime;

	// All statistics are calculated per checker and then calculated for global
	// trial results on simulation completion. Methods use logic adapted from
	// CarWash
	// statistics calculations.

	public static void calculateAverageWaitlineLength() {
		for (int i = 0; i < averageWaitlineLength.length; i++) {
			averageWaitlineLength[i] = averageWaitlineLength[i]
					/ totalSystemTime;
		}
	}

	public static void updateCheckoutTime(double time, double enterTime) {
		double wait = time - enterTime;
		if (wait > maxCheckoutTime) {
			maxCheckoutTime = wait;
		}
		averageCheckoutTime += wait;
		arrivals++;
	}

	public static void updateQueueStats(double time, int waitlinelength, int ID) {

		if (waitlinelength > maxWaitlineLength[ID]) {
			maxWaitlineLength[ID] = waitlinelength;
		}
		averageWaitlineLength[ID] += oldWaitlineLength[ID]
				* (time - lastWaitlineUpdateTime[ID]);
		if (time > totalSystemTime) {
			totalSystemTime = time;
		}
		lastWaitlineUpdateTime[ID] = time;
		oldWaitlineLength[ID] = waitlinelength;
	}

	public static void updateBusyTimeStats(double time, int ID) {

		busyTime[ID] += time - lastUpdateTime[ID];
		lastUpdateTime[ID] = time;
	}

	public static void updateIdleTimeStats(double time, int ID) {

		idleTime[ID] += time - lastUpdateTime[ID];
		lastUpdateTime[ID] = time;
	}

	// prints some global and individual checker and shopper stats
	public static void print() {
		System.out.println("Total System Time: " + totalSystemTime);
		System.out.println("Average Waiting Time: " + averageCheckoutTime
				/ arrivals);
		System.out.println("Average Idle Time: " + getArrayAverage(idleTime));
		System.out.println("Average Waitline Length: "
				+ getArrayAverage(averageWaitlineLength));
		System.out.println("Maximum Waitline Length: "
				+ getArrayMaximum(maxWaitlineLength));

		System.out.println("--------------------------------------");
		for (int i = 0; i < StoreSim.checkers.length; i++) {
			System.out.println("Checker: " + i);
			System.out.println("IdleTime: " + idleTime[i]);
			System.out.println("BusyTime: " + busyTime[i]);
		}
		System.out.println("--------------------------------------");
		for (int i = 0; i < StoreSim.checkers.length; i++) {
			System.out.println("Checker: " + i);
			System.out.println("Max Waitline Length: " + maxWaitlineLength[i]);
			System.out.println("Average Waitline Length: "
					+ (averageWaitlineLength[i]));
		}
	}

	// array maximum function
	public static double getArrayMaximum(double[] array) {
		double max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	// Array average function
	public static double getArrayAverage(double[] array) {
		double total = 0;
		for (int i = 0; i < array.length; i++) {
			total = total + array[i];
		}
		return total / array.length;
	}

	// Assigns Stats File
	private static final String STATS_FILE = "data/statistic.csv";

	// Writes to Stats file
	public static void saveStats() throws IOException {

		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(
					STATS_FILE, true)));
			fileWriter.println(String.format("%.2f, %.2f, %.2f, %.2f",
					averageCheckoutTime / arrivals, getArrayAverage(idleTime),
					getArrayAverage(averageWaitlineLength),
					getArrayMaximum(maxWaitlineLength)));
		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}

	}
}
