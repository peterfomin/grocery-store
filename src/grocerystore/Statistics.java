package grocerystore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Statistics {
	private static double averagearrivaltime;
	private static int arrivals;
	private static double[] busyTime = new double[StoreSim.checkers.length];
	private static double[] lastUpdateTime = new double[StoreSim.checkers.length];
	private static double[] idleTime = new double[StoreSim.checkers.length];
	private static double averageIdleTime;
	private static double averageBusyTime;

	// private int

	public static void updateBusyTimeStats(double time, int ID) {

		busyTime[ID] += time - lastUpdateTime[ID];
		lastUpdateTime[ID] = time;
	}

	public static void updateIdleTimeStats(double time, int ID) {

		idleTime[ID] += time - lastUpdateTime[ID];
		lastUpdateTime[ID] = time;
	}

	public static void print() {
		System.out.println("--------------------------------------");
		for (int i = 0; i < StoreSim.checkers.length; i++) {
			System.out.println("Checker: " + i);
			System.out.println("IdleTime: " + idleTime[i]);
			System.out.println("BusyTime: " + busyTime[i]);

		}

	}

	public static double getArrayAverage(double[] data) {
		double sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}
		return (double) sum / data.length;
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
