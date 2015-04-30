package grocerystore;

/**
 * 
 * @author Peter Fomin 4603194 and Zach Gartner 4643160
 * 
 * Inspired By Dovolis Car Wash Simulation
 * 
 * **Class Re-Used from Example**
 *
 */

public class Segment {
    private double time;
    private Q1 q;
    private Segment next;

    // constructor

    public Segment(double t) {
        time = t;
        q = new Q1();
        next = null;
    }

    // methods

    public double getTime() {
        return time;
    }

    public Q1 getEvents() {
        return q;
    }

    public Segment getNext() {
        return next;
    }

    public void setNext(Segment nextSegment) {
        next = nextSegment;
    }

	@Override
	public String toString() {
		return String.format("Segment [time=%s, q=\n%s]", time, q);
	}
    
}
