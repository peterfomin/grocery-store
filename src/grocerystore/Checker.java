package grocerystore;

public class Checker {
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

	public double getShopperCheckTime(int items) {
		return items * 9;
	}

	public double getEmployeeCheckTime(int items) {
		return items * 5;
	}
	
	public boolean isBusy(){
		return busy;
	}

	public void checkout() {
		this.busy = true;
		Shopper shopper = (Shopper) waitline.remove();
		if (shopper != null) {
			int items = shopper.getItems();
			double time = 0;
			if(employeeBagging){
				time = getEmployeeCheckTime(items);
			}else{
				time = getShopperCheckTime(items);
			}
			Statistics.updateWaitTimeStats(StoreSim.agenda.getCurrentTime(), shopper.getArrivalTime());
			StoreSim.agenda.add(new CheckerEvent(ID), time);
		}
	}

}
