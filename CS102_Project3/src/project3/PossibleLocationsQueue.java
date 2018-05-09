package project3;

/**
 * Custom class, representing a queue that holds <tt>Locations</tt>, 
 * 2D points(row,columns). Built using the "Array Structure Method"
 * 
 * Implements PossibleLocations interface
 * 
 * @author Joel Forrester
 *
 */
public class PossibleLocationsQueue implements PossibleLocations {

	//instance variables
	private Location[] data;
	private int front = 0;
	private int size = 0;
	
	//constructors
	/**
	 * Constructs a queue object of default size that holds <tt>Locations<tt>.
	 */
	public PossibleLocationsQueue() {
		data = new Location[30];
	}
	
	/**
	 * Constructs a queue object of a given size that holds <tt>Locations<tt>.
	 * @throws IllegalArgumentException when the parameter does not represent a valid <tt>int<tt>
	 */
	public PossibleLocationsQueue(int capacity) { //constructs queue with given capacity
		if(capacity <= 0) {
			throw new IllegalArgumentException("capacity must be greater than 0");
		}
		data = new Location[capacity];
	}
	
	//methods
	@Override
	public void add(Location s){
		if(data[data.length - 1] != null) {
			Location[] newData = new Location[data.length * 2];
			for(int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			data = newData;
		}
		int available = (front+size)%data.length;
		data[available] = s;
		size++;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	
	@Override
	public Location remove() {
		if(isEmpty()) {
			return null;
		}
		
		Location toDequeue = data[front];
		data[front] = null;
		front = (front + 1)%data.length;
		size--;
		return toDequeue;
	}	
	
	/**
	 * Returns the number of Locations in the queue
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns the Location at the index  of the queue
	 * @param index
	 * 	- the index in the queue to travel to
	 */
	public Location getIndexLocation(int index) {
		return data[index];
	}
}
