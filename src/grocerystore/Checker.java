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

	public boolean isBusy() {
		if (waitline.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public double getShopperCheckTime(int items) {
		return items * 9;
	}

	public double getEmployeeCheckTime(int items) {
		return items * 5;
	}

	public void checkout() {
		Shopper shopper = (Shopper) waitline.remove();
		if (shopper != null) {
			int items = shopper.getItems();
			double time = 0;
			switch (this.type) {
			case 0:
				time = getShopperCheckTime(items);
				break;
			case 1:
				time = getEmployeeCheckTime(items);
				break;
			case 2:
				time = getShopperCheckTime(items);
				break;
			case 3:
				time = getEmployeeCheckTime(items);
				break;
			default:
				break;
			}
			
			StoreSim.agenda.add(new CheckerEvent(ID), time);
		}
	}

}
