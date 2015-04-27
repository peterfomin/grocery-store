package grocerystore;

public class Statistics {
	private static double averagearrivaltime;
	private static int arrivals;
	private static double[] busyTime = new double[StoreSim.checkers.length];
	private static double[] lastUpdateTime = new double[StoreSim.checkers.length];
	private static double[] idleTime = new double[StoreSim.checkers.length];

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

}
