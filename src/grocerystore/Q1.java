package grocerystore;

public class Q1 implements Q {
	  // constructor

    public Q1() {}

    // selectors

    public void add(Object o) {

        if (size == 0) {
          front = new Node(o, null);
          rear = front;
        }
        else {
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

    private int size;
    private Node front;
    private Node rear;

}
