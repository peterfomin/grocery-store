package grocerystore;

public class CheckerEvent implements Event {
	private Checker checker;

	public CheckerEvent(int ID) {
		checker = StoreSim.checkers[ID];
	}

	public void run() {
		if (checker.hasNext()) {
			checker.checkout();
		} else {
			checker.busy = false;
		}
	}

	@Override
	public String toString() {
		return String.format(super.toString() + "[checker=%s]", checker.ID);
	}

}
