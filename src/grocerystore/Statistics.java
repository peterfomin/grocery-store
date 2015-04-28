package grocerystore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Statistics {
	//class variables
	private static double averagearrivaltime;
	private static int arrivals;
	private static double[] busyTime = new double[StoreSim.checkers.length];
	private static double[] lastUpdateTime = new double[StoreSim.checkers.length];
	private static double[] idleTime = new double[StoreSim.checkers.length];
	private static double averageIdleTime;
	private static double averageBusyTime;
	private static double[] maxWaitlineLength = new double[StoreSim.checkers.length];
	private static double[] averageWaitlineLength = new double[StoreSim.checkers.length];
	private static double totalSystemTime;
	private static double[] oldWaitlineLength = new double[StoreSim.checkers.length];
	private static double[] lastWaitlineUpdateTime = new double[StoreSim.checkers.length];
	private static double averageCheckoutTime;
	private static double maxCheckoutTime;

	public static void updateArrivalStats(double time) {

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
		// totalSystemTime += time - lastWaitlineUpdateTime[ID];
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

	public static void print() {
		System.out.println("Total System Time: " + totalSystemTime);
		System.out.println("Average Waiting Time: "
				+ (averageCheckoutTime));
		System.out.println("Average Busy Time: " + averageBusyTime);
		System.out.println("Average Idle: " + averageIdleTime);
		System.out.println("Average Waitline Length: ");
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
					+ (averageWaitlineLength[i] / totalSystemTime));
		}
	}

	public static double getArrayMaximum(double[] array) {
		double max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return (double) max;
	}

	public static double getArrayAverage(double[] array) {
		double total = 0;
		for (int i = 0; i < array.length; i++) {
			total = total + array[i];
		}
		return (double) total / array.length;
	}

	private static final String STATS_FILE = "data/statistic.csv";

	public static void saveStats() throws IOException {

		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(STATS_FILE,
					true)));
			out.println(String.format("%.2f, %.2f", getArrayAverage(busyTime),
					getArrayAverage(idleTime)));
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
