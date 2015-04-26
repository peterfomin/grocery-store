package grocerystore;

public class StoreSim {
	static PQ agenda = new PQ();
	static ShopperMaker shoppermaker;
	static Checker[] checkers;

	//arg 0 : rate of shopper (int), 
	//arg 1 : number of regular checkout lanes (int)
	//arg 2: number of express lanes (int) 
	//arg 2 : employee bagging (boolean)
	
	public static void main(String args[]) {
		
		//setup system config
		if(args.length < 4){
			throw new IllegalArgumentException("Need all arguments!");
		}
		int rate = Integer.parseInt(args[0]);
		int regular = Integer.parseInt(args[1]);
		int express = Integer.parseInt(args[2]);
		boolean employee = Boolean.parseBoolean(args[3]);
		
		
	
		//create my checkers
		checkers = new Checker[regular + express];
		
		for(int e = 0; e < express; e++){
			if(employee){
				checkers[e] = new Checker(e,0);
			}else{
				checkers[e] = new Checker(e,2);
			}
		}
		
		for(int r = 0; r < regular; r++){
			if(employee){
				checkers[r] = new Checker(r,1);
			}else{
				checkers[r] = new Checker(r,3);
			}
		}
		
		//start arrival mechanism
		

	}

}
