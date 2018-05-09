package project3;
/**
 * Custom class, representing a stack that holds <tt>Locations</tt>, 
 * 2D points(row,columns). Built using the "Reference Structure Method"
 * 
 * Implements PossibleLocations interface
 * 
 * @author Joel Forrester
 *
 */
public class PossibleLocationsStack implements PossibleLocations {
	/**
	 * Used to hold Location data and link to other Locations.
	 */
	private static class Node<Location>{
		private Location element;
		private Node next;
		
		public Node(Location e) {
			element = e;
			next = null;
		}
		
		public project3.Location getElement() {
			return (project3.Location) this.element;
		}
		
		public Node<Location> getNext() {
			return this.next;
		}
		
		public void setNext(Node<Location> n) {
			this.next = n;
		}
	}
	
	//instance variables
	private Node top = null;
	private int size = 0;
	
	//methods
	@Override
	public void add(Location s) {
		//If add is called with a null value return
		if(s == null) {
			return;
		}
		
		Node newest = new Node(s);
		
		if(top == null) {
			top = newest;
		}else {
			newest.setNext(top);;
			top = newest;
		}
		
		size++;
	}

	@Override
	public boolean isEmpty() {
		return (top == null);
	}
	
	@Override
	public Location remove() {
		//Return if top is null
		if(top == null) {
			return null;
		}
		
		Location toPop = top.getElement();
		top = top.getNext();
		size--;
		return toPop;
	}
	
	/**
	 * Returns the number of Locations in the stack
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns the Location at the top of the stack
	 */
	public Location top() {
		return top.getElement();
	}
}
