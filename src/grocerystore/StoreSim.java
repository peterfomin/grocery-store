package grocerystore;

import java.io.IOException;


public class StoreSim {
	static PQ agenda = new PQ();
	static ShopperMaker shoppermaker;
	static Checker[] checkers;

	// arg 0 : rate of shopper (int),
	// arg 1 : number of regular checkout lanes (int)
	// arg 2: number of express lanes (int)
	// arg 2 : employee bagging (boolean)

	public static void main(String args[]) throws IOException {

		// setup system config
//		if (args.length < 4) {
//			throw new IllegalArgumentException("Need all arguments!");
//		}
//		int rate = Integer.parseInt(args[0]);
//		int regular = Integer.parseInt(args[1]);
//		int express = Integer.parseInt(args[2]);
//		boolean employee = Boolean.parseBoolean(args[3]);
		
		int rate = 30;
		int regular = 6;
		int express = 2;
		boolean employeeBagging = true;

		
		
		// create my checkers
		createCheckers(regular, express, employeeBagging);
		
		
		// start arrival mechanism

		agenda.add(new ShopperMaker(rate), 10);

		while (agenda.getCurrentTime() <= 15000) {
			Event event = agenda.remove();
			System.out.println(agenda.getCurrentTime());
			System.out.println(event.toString());
			event.run();
		}
		
		for(int i = 0; i < checkers.length; i++){
			Statistics.updateIdleTimeStats(StoreSim.agenda.getCurrentTime(), i);
		}
		
		Statistics.calculateAverageWaitlineLength();
		Statistics.print();
		Statistics.saveStats();
	}

	static void createCheckers(int regular, int express,
			boolean employeeBagging) {
		checkers = new Checker[regular + express];

		for (int i = 0; i < express; i++) {
			checkers[i] = new Checker(i, true, employeeBagging);
		}

		for (int i = express; i < regular + express; i++) {
			checkers[i] = new Checker(i, false, employeeBagging);
		}
	}

}
