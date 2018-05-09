package project2;

/**
 * A sorted collection.  The element in this list are stored in order determined
 * by the natural ordering of those elements (i.e., the {@code compareTo()} method
 * defined in the elements' class).  
 * The user can access elements by their integer index (position in
 * the list), and search for elements in the list.<p>
 *
 *
 * @param <E> the type of elements in this list
 *
 * @author  Joel Forrester
 */
public class OrderedLinkedList<E extends Comparable<E>> implements OrderedList<E>{

	/**
     * Node object that will make up the list;
     */
	private static class Node<E>{
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
			
		public Node(E e) {
			element = e;
			next = null;
		}
		
		/**
	     * Returns the element of type <tt>E</tt> stored in the node
	     */
		public E getElement() {
			return element;
		}
		
		/**
	     * Returns the following Node
	     */
		public Node<E> getNext(){
			return next;
		}
		
		/**
	     * Sets the following Node
	     */
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	//instance variables
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	public OrderedLinkedList() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean add(E e) throws ClassCastException, NullPointerException{
		if(e == null) {
			throw new NullPointerException("Can not add null to list");
		}
		
		Node newest = new Node(e);
		
		if(this.isEmpty()) {
			head = newest;
			tail = newest;
			size = size + 1;
			return true;
		}
		
		Node walk = head;
		Node previous = walk;
		while(walk != null) {
			if(e.compareTo( (E)walk.getElement()) < 0) {
				if(walk == head) {
					newest.next = head;
					head = newest;
				}else {
					newest.next = walk;
					previous.next = newest;		
				}
				
				size = size + 1;
				return true;
			}
			
			if(e.compareTo( (E)walk.getElement()) == 0) {
				if(walk == head) {
					newest.next = head;
					head = newest;
				}else {
					newest.next = walk;
					previous.next = newest;		
				}
				
				size = size + 1;
				return true;
			}
			
			if(e.compareTo( (E)walk.getElement()) > 0) {
				previous = walk;
				walk = walk.getNext();
			}
			
		}
		
		tail.next = newest;
		newest.next = null;
		tail = newest;
		size = size + 1;
		return true;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
		
	}


	public Object clone() throws CloneNotSupportedException {
		OrderedLinkedList<E> other = (OrderedLinkedList<E>)super.clone();
		if(size > 0) {
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();
			Node<E> otherTail = other.head;
			
			while(walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest);
				otherTail = newest;
				walk =  walk.getNext();
			}
		}
		
		return other;
	} 
	
	@Override
	public boolean contains(Object o) {
		if(o == null) {
		throw new NullPointerException();	
		}
		
		Node current = head;
		
		while(current != null) {
			if(current.element == o) return true;
			current = current.next;
		}
		
		return false;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		
		OrderedLinkedList other = (OrderedLinkedList)o;
		if(size != other.size) return false;
		
		Node walkA = head;
		Node walkB = other.head;
		while(walkA != null) {
			if(!walkA.getElement().equals(walkB.getElement())) return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}
	
	/**
     * Returns the first element <tt>E</tt> of this list
     */
	public E first() {
		if(isEmpty()) return null;
		return head.getElement();
	}
	
	@Override
	public E get(int index) {
		if(head == null) {
			throw new NullPointerException();
		}
	
		Node current = head;
		int counter = 0;
		
		while(current != null && counter != index) {
			current = current.next;
			counter++;
		}
		if(counter == index) {
			return (E) current.getElement();
		}
		return null;
	}

	@Override
	public int indexOf(Object o) {
		if(head == null) {
			throw new NullPointerException("Invalid Command: List is empty");
		}
		
		Node current = head;
		int index = 0;
		
		while(current != null) {
			if(current.getElement() == o) {
				return index;
			}
			current = current.getNext();
			index++;
		}
		return -1;
	}
	
	/**
     * Returns <tt>true</tt> if this list is empty
     */
	public boolean isEmpty() {
		return head == null || size == 0;
	}
	
	 /**
     * Returns the last element <tt>E</tt> of this list
     */
	public E last() {
		if(isEmpty()) return null;
		return tail.getElement();
	}
	
	@Override
	public E remove(int index) {

		if(index < 0 || index > size-1) {
			throw new IndexOutOfBoundsException();
		}
		
		if(head == null) {
			throw new NullPointerException();
		}
		
		
		Node target = head;
		Node previous = target;
		int counter = 0;
		
		if(index == 0 ) {
			head = head.next;
			size = size - 1;
			return (E) target.getElement();
		}
		
		while(target != null && counter != index) {
			previous = target;
			target = target.next;
			counter++;
		}
		
	
		if(target == tail) {
			tail = previous;
			tail.next = null;
			size = size - 1;
			return (E) target.getElement();
		}
		
		if(counter == index) {
			previous.next = target.next;
			size = size - 1;
			return (E) target.getElement();
		}
	
		
		return null;
	}

	@Override
	public boolean remove(Object o) {
		
		if(o == null) {
			throw new NullPointerException();
		}
		
		if(head == null) {
			throw new NullPointerException();
		}
		
		Node target = head;
		Node previous = target;
		
		if(o == head.getElement()) {
			head = head.next;
			size = size - 1;
			return true;
		}
		
		while(target != null && target.getElement() != o) {
			previous = target;
			target = target.next;
		}
		
		if(o == tail.getElement()) {
			tail = previous;
			tail.next = null;
			size = size - 1;
			return true;
		}
		
		if(target.getElement() == o) {
			previous.next = target.next;
			size = size - 1;
			return true;
		}
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		if(head == null) return "[]";
		
		Node<E> current = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while(current != null) {
			sb.append(current.getElement().toString());
			if(current.next != null) {
				sb.append(", ");
			}
			current = current.next;
		}
		sb.append("]");
		
		return sb.toString();
	}
}
