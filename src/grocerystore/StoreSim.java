package grocerystore;

import java.io.IOException;

/**
 * 
 * @author Peter Fomin and Zach Gartner
 * 
 *         Inspired By Dovolis Car Wash Simulation
 * 
 *         Class Analog: CarSim
 *
 */

// Main driver for program
public class StoreSim {
	static PQ agenda = new PQ();
	static ShopperMaker shoppermaker;
	static Checker[] checkers;

	// OPTIONAL: Can input run config (Uncomment)

	// arg 0 : rate of shopper (int),
	// arg 1 : number of regular checkout lanes (int)
	// arg 2: number of express lanes (int)
	// arg 2 : employee bagging (boolean)

	public static void main(String args[]) throws IOException {

		// setup system config (Choose as run config or main driver method)

		// if (args.length < 4) {
		// throw new IllegalArgumentException("Need all arguments!");
		// }
		// int rate = Integer.parseInt(args[0]);
		// int regular = Integer.parseInt(args[1]);
		// int express = Integer.parseInt(args[2]);
		// boolean employee = Boolean.parseBoolean(args[3]);

		int rate = 40;
		int regular = 9;
		int express = 1;
		boolean employeeBagging = false;

		// create the configured checkers
		createCheckers(regular, express, employeeBagging);

		// start shopper arrival mechanism
		agenda.add(new ShopperMaker(rate), 10);

		// Simulation is run for given number of seconds
		while (agenda.getCurrentTime() <= 15000) {
			Event event = agenda.remove();
			System.out.println(agenda.getCurrentTime());
			System.out.println(event.toString());
			event.run();
		}

		// Account time lost for events uncomplete by simulation hard stop
		for (int i = 0; i < checkers.length; i++) {
			Statistics.updateIdleTimeStats(StoreSim.agenda.getCurrentTime(), i);
		}

		// Statistics calculations
		Statistics.calculateAverageWaitlineLength();
		Statistics.print();
		Statistics.saveStats();
	}

	// Creates each desired checker stores in array
	static void createCheckers(int regular, int express, boolean employeeBagging) {
		checkers = new Checker[regular + express];

		for (int i = 0; i < express; i++) {
			checkers[i] = new Checker(i, true, employeeBagging);
		}

		for (int i = express; i < regular + express; i++) {
			checkers[i] = new Checker(i, false, employeeBagging);
		}
	}

}
