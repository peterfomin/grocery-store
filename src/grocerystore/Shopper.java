package grocerystore;

//new comment peter local

public class Shopper {
	//random comment
	private double arrivaltime = 2;
	private int items = 0;

	public Shopper(double time, int items) {
		this.arrivaltime = time;
		this.items = items;

		
	}
	public void setArrivalTime(double arrivaltime){
		this.arrivaltime = arrivaltime;
	}
	public void setItems(int items){
		this.items = items;
	}
	public double getArrivalTime(){
		return this.arrivaltime;
	}
	public int getItems(){
		return this.items;
	}
}
