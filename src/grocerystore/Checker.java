package grocerystore;

public class Checker {
	boolean busy = false;
	boolean express;
	Q1 waitline = new Q1();

	public Checker(boolean e) {
		express = e;
	}

	public void addToWaitline(Shopper shopper) {

		waitline.add(shopper);
	}

	public void changebusy() {
		busy = !busy;
	}

}
