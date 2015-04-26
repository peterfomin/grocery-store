package grocerystore;

public class Node {
	 // instance variables
    private Object data;
    private Node next;

    // constructors
    public Node() {

    }

    public Node(Object o, Node link) {
         data = o;
         next = link;
    }

    // selectors
    public Object getData() {
         return data;
    }

    public void setData(Object o) {
         data = o;
    }

    public Node getNext() {
         return next;
    }

    public void setNext(Node link) {
         next = link;
    }

	@Override
	public String toString() {
		return String.format("Node [data=%s]", data);
	}
    
}
