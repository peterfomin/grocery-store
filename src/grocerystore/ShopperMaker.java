package grocerystore;

import java.util.Random;

public class ShopperMaker implements Event {

	public double interval;

	public ShopperMaker(double intval) {
		this.interval = intval;
	}

	// change interval
	private double RandomArrival() {
		Random random = new Random();
		int selection = random.nextInt(100);
		if ((selection >= 0) && (selection < 10)) {
			return interval + (0.75 * interval);
		} else if ((selection >= 10) && (selection < 25)) {
			return interval + (0.50 * interval);
		} else if ((selection >= 25) && (selection < 45)) {
			return interval + (0.20 * interval);
		} else if ((selection >= 45) && (selection < 55)) {
			return interval;
		} else if ((selection >= 55) && (selection < 75)) {
			return interval - (0.20 * interval);
		} else if ((selection >= 75) && (selection < 90)) {
			return interval - (0.50 * interval);
		} else if ((selection >= 90) && (selection < 100)) {
			return interval - (0.75 * interval);
		} else {
			return interval;
		}
	}

	private int RandomItem() {
		int[] itemrange = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 10, 20,
				30, 40, 50, 60, 70, 80, 10, 20, 30, 40, 50, 60, 70, 20, 30, 40,
				50, 30 };
		int items = 10;
		boolean reset = true;
		while (reset == true) {
			try {
				Random random = new Random();
				int randint1 = random.nextInt(30);
				int randint2 = random.nextInt(10);
				int selection = itemrange[randint1];
				items = selection - randint2;
				reset = false;
			} catch (Exception e) {
				reset = true;
			}
		}

		return items;
	}

	public void run() {
		StoreSim.agenda.add(new ShopperMaker(interval), RandomArrival());
		Shopper newShopper = new Shopper(StoreSim.agenda.getCurrentTime(),
				RandomItem());
		// add to available checker's queue
		StoreSim.addToChecker(newShopper);
	}

}
