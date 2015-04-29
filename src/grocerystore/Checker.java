package grocerystore;

/**
 * 
 * @author Peter Fomin and Zach Gartner
 * 
 *         Inspired By Dovolis Car Wash Simulation
 * 
 *         Class Analog: Washer
 *
 */
// Instantiable for each checker in the system, can be of different types.
// Processes shoppers in its
// waitline. Calls CheckerEvent for each shopper processing.
public class Checker {
	// type designations
	boolean busy;
	boolean express;
	boolean employeeBagging;

	int ID;
	Q1 waitline = new Q1();

	public Checker(int ID, boolean express, boolean employeeBagging) {
		this.ID = ID;
		this.express = express;
		this.employeeBagging = employeeBagging;
	}

	public void addToWaitline(Shopper shopper) {

		waitline.add(shopper);
	}

	public void changebusy() {
		busy = !busy;
	}

	public boolean hasNext() {
		if (waitline.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	// calculations for checkout time/bagging
	public double getShopperCheckTime(int items) {
		return items * 9;
	}

	public double getEmployeeCheckTime(int items) {
		return items * 5;
	}

	public boolean isBusy() {
		return busy;
	}

	public void checkout() {
		this.busy = true;
		Shopper shopper = (Shopper) waitline.remove();
		// shopper removed from queue stat update. Updates checkout time and
		// waitline length.
		Statistics.updateCheckoutTime(StoreSim.agenda.getCurrentTime(),
				shopper.getArrivalTime());
		Statistics.updateQueueStats(StoreSim.agenda.getCurrentTime(),
				this.waitline.length(), this.ID);

		if (shopper != null) {
			int items = shopper.getItems();
			double time = 0;
			if (employeeBagging) {
				time = getEmployeeCheckTime(items);
			} else {
				time = getShopperCheckTime(items);
			}
			// adds new checker event based on shopper service time
			StoreSim.agenda.add(new CheckerEvent(ID), time);
		}
	}

}
