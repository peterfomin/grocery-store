package grocerystore;

//new comment peter local

/**
 * 
 * @author Peter Fomin 4603194 and Zach Gartner 4643160
 * 
 *         Inspired By Dovolis Car Wash Simulation
 * 
 *         Class Analog: Car
 *
 */

// Data utility for representing individual shoppers in the system
public class Shopper {
	// Data values
	private double arrivaltime = 2;
	private int items = 0;

	public Shopper(double time, int items) {
		this.arrivaltime = time;
		this.items = items;

	}

	public void setArrivalTime(double arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public double getArrivalTime() {
		return this.arrivaltime;
	}

	public int getItems() {
		return this.items;
	}
}
