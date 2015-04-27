package grocerystore;



import java.util.Random;

public class ShopperMaker implements Event {

	public double interval;
	public int ID;

	public ShopperMaker(double intval) {
		this.interval = intval;
	}

	// change interval for next arrival
	private double randomArrival() {
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

	private int randomItem() {
		int[] itemrange = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 10, 20,
				30, 40, 50, 60, 70, 80, 10, 20, 30, 40, 50, 60, 70, 20, 30, 40,
				50, 30 };
		int items = 10;
		Random random = new Random();
		int randint1 = random.nextInt(itemrange.length);
		int randint2 = random.nextInt(10);
		int selection = itemrange[randint1];
		items = selection - randint2;

		return items;
	}

	public void addToChecker(Shopper shopper) {
		int minsize = StoreSim.checkers[0].waitline.length();
		Checker current = StoreSim.checkers[0];
		boolean expressaccess = false;
		if (shopper.getItems() <= 10) {
			expressaccess = true;
		}
		// finds lowest queue checker, searches express checkers if applicable
		for (int i = 0; i < StoreSim.checkers.length; i++) {
			if (StoreSim.checkers[i].waitline.length() <= minsize) {
				if ((!StoreSim.checkers[i].express)
						|| ((StoreSim.checkers[i].express) && (expressaccess))) {
					current = StoreSim.checkers[i];
					minsize = current.waitline.length();
					this.ID = current.ID;
				}
			}
		}

		// add the shopper to the proper checker queue
		current.addToWaitline(shopper);
		// if the checker added to was idle, process next shopper
		if (!current.isBusy()) {
			for(int i = 0; i < StoreSim.checkers.length; i++){
				if(!StoreSim.checkers[i].isBusy()){
					Statistics.updateIdleTimeStats(StoreSim.agenda.getCurrentTime(), StoreSim.checkers[i].ID);
				}
			}
			current.checkout();
		}

	}

	public void run() {
		// reschedule new entry of shopper into system using random interval
		StoreSim.agenda.add(new ShopperMaker(interval), randomArrival());
		Shopper newShopper = new Shopper(StoreSim.agenda.getCurrentTime(),
				randomItem());
		// add to available checker's queue
		addToChecker(newShopper);
		System.out.println("Added to Checker: " + this.ID);
	}

	@Override
	public String toString() {
		return String.format(super.toString() + "[interval=%s, ID=%s]",
				interval, ID);
	}
}
