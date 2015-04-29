package grocerystore;

/**
 * 
 * @author Peter Fomin and Zach Gartner
 * 
 * Inspired By Dovolis Car Wash Simulation
 * 
 * **Class Re-Used from Example**
 *
 */

public class Q1 implements Q {
	// constructor

	public Q1() {
	}

	// selectors

	public void add(Object o) {

		if (size == 0) {
			front = new Node(o, null);
			rear = front;
		} else {
			rear.setNext(new Node(o, null));
			rear = rear.getNext();
		}
		size++;
	}

	public Object remove() {

		Object answer;

		if (size == 0)
			return null;

		answer = front.getData();
		front = front.getNext();
		size--;
		if (size == 0)
			rear = null;
		return answer;
	}

	public int length() {
		return size;
	}

	@Override
	public String toString() {

		int size = 0;
		StringBuilder builder = new StringBuilder();

		Node node = front;
		while (node != null) {
			size++;
			builder.append("\n\t").append(node);
			node = node.getNext();
		}

		return "\tQ1: size=" + size + " nodes:" + builder.toString();
	}

	public void print() {
		boolean foundnull = false;
		Node current = front;
		while (foundnull == false) {
			if (current.getNext() == null) {
				foundnull = true;
			}
			System.out.println(current.getData().toString());
		}
	}

	private int size;
	private Node front;
	private Node rear;

}
