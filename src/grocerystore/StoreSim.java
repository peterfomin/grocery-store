package grocerystore;

public class StoreSim {
	static PQ agenda = new PQ();
	static ShopperMaker shoppermaker;
	static Checker checker1;
	static Checker[] checkers;

	public static void main(String args[]) {
		checkers = new Checker[4];

	}

	public static void addToChecker(Shopper shopper) {
		int minsize = checkers[0].waitline.length();
		Checker current = checkers[0];
		for (int i = 0; i < checkers.length; i++) {
			if (checkers[i].waitline.length() < minsize) {
				current = checkers[i];
			}
		}

		current.addToWaitline(shopper);
	}
}
