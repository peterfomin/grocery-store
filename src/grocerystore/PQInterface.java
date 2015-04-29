package grocerystore;

/**
 * 
 * @author Peter Fomin and Zach Gartner
 * 
 * Inspired By Dovolis Car Wash Simulation
 * 
 * **Interface Re-Used from Example**
 *
 */

public interface PQInterface {

    // Priority Queue Interface

    public void add(Event o, double time);

    /* Places an event object into priority queue with priority given
       by time */

    public Event remove();

    /* Removes and returns the highest priority event in a priority
       queue; returns null if the priority queue is empty */

    public boolean isEmpty();

    /* Returns true is the priority queue is empty, false otherwise */

    public double getCurrentTime();

    /* Returns the priority associated with the top priority event in the
       priority queue */
}
