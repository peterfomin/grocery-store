package grocerystore;

public class Checker {
	boolean busy = false;
	int type;
	int ID;
	Q1 waitline = new Q1();

	public Checker(int ID, int type) {
		this.ID = ID;
		this.type = type;
	}

	public void addToWaitline(Shopper shopper) {

		waitline.add(shopper);
	}

	public void changebusy() {
		busy = !busy;
	}

}
