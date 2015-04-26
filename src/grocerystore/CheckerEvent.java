package grocerystore;

public class CheckerEvent implements Event {
	private Checker checker;

	public CheckerEvent(int ID) {
		checker = StoreSim.checkers[ID];
	}

	public void run() {
		if (checker.isBusy()) {
			checker.checkout();
		}
	}
}
