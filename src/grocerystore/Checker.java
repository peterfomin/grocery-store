package grocerystore;

public class Checker {
	boolean busy = false;
	boolean express;
	
	public Checker(boolean e){
		express = e;
	}
	
	public void changebusy(){
		busy = !busy;
	}
	
}
