package grocerystore;

/**
 * 
 * @author Peter Fomin 4603194 and Zach Gartner 4643160
 * 
 *         Inspired By Dovolis Car Wash Simulation
 * 
 *         Class Analog: Washer "Event"
 *
 */
// Event representing time of completion of the checkout of a shopper
public class CheckerEvent implements Event {
	private Checker checker;

	public CheckerEvent(int ID) {
		checker = StoreSim.checkers[ID];
	}

	// When we get here, the current shopper serviced by this checker has
	// finished checkout
	public void run() {
		// checker "caught" in busy state processing shoppers, update stat
		if (checker.isBusy()) {
			Statistics.updateBusyTimeStats(StoreSim.agenda.getCurrentTime(),
					checker.ID);
		}
		// if waitline has a next shopper, process them
		if (checker.hasNext()) {
			checker.checkout();
		} else {
			checker.busy = false;
		}
	}

	// event logging
	@Override
	public String toString() {
		return String.format(super.toString() + "[checker=%s]", checker.ID);
	}

}
