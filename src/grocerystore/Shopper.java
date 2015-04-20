package grocerystore;

//new shopper class

public class Shopper {
	private double arrivaltime = 0;
	private int items = 0;

	public Shopper(double time, int items) {
		this.arrivaltime = time;
		this.items = items;

		// add to available checker's queue
		StoreSim.addToChecker(this);
	}
}
