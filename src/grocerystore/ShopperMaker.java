package grocerystore;

public class ShopperMaker implements Event {
	
	public int interval;
	public int groceries;
	
	public ShopperMaker(int intval, int g){
		this.interval = intval;
		this.groceries = g;
	}
	//change interval
	private int RandomArrival(int low, int high){
		return (int) Math.floor((high - low) * Math.random() + low + 0.5);
	}
	
	private int RandomItem(){
		return 9;
	}
	
	public void run(){
		StoreSim.agenda.add(new ShopperMaker(interval, groceries), RandomArrival(0, 2 * interval));
		Shopper newShopper = new Shopper(StoreSim.agenda.getCurrentTime(), RandomItem());
	}
}
