package project5;

import project5.BST_Recursive.Node;


/**
 * The class provides an implementation of AVL tree. With the addition of several method to search within a range as specified in project goals.
 * Extends BST_Recursive with specified parameter of <tt>Collision
 * 
 * @author Joel Forrester
 *
 
 */
public class CollisionsData extends BST_Recursive<Collision>{

	private boolean found;
	private int matches, fatalities, pedestriansF, cyclistsF, motoristsF, 
	injuries, pedestriansI, cyclistsI, motoristsI;
	
	/*
	 * Default Constructor for CollisionsData class. Starts with null or 0 values;
	 */
	public CollisionsData() {
		this.root = null;
		numOfElements = 0;
	}

	@Override
	public void add(Collision item) {
		if (item == null)
			return;
		root = add (root, item);
	}
	
	public String getReport(String zip, Date dateBegin, Date dateEnd) {
		matches = 0;
		fatalities = 0; 
		pedestriansF = 0; 
		cyclistsF = 0;
		motoristsF = 0;
		injuries = 0;
		pedestriansI = 0; 
		cyclistsI = 0;
		motoristsI = 0;
		
		StringBuilder sb = new StringBuilder();
		
		recRangeCheck(root, zip, dateBegin, dateEnd);
		sb.append("Motor Vehicle Collisions for zipcode " + zip + " " + "(" + dateBegin.toString() + " - " + dateEnd.toString() + ")");
		sb.append("\n====================================================================");
		sb.append("\nTotal number of collisions: " + matches);
		sb.append("\nNumber of fatalities: " + fatalities);
		sb.append("\npedestrians: " + pedestriansF);
		sb.append("\ncyclists: " + cyclistsF);
		sb.append("\nmotorists: " + motoristsF);
		sb.append("\nNumber of injuries: " + injuries);
		sb.append("\npedestrians: " + pedestriansI);
		sb.append("\ncyclists: " + cyclistsI);
		sb.append("\nmotorists: " + motoristsI);
		return sb.toString();
	}
	
	@Override
	public boolean remove(Collision target) {
		root = recRemove(target, root);
		return found;
	}
	
	/**
	 * Returns a string representation of this tree using an inorder traversal . 
	 * @see java.lang.Object#toString()
	 * @return string representation of this tree 
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		inOrderPrint(root, s);
		return s.toString();
	}

	/**
	 * Produces tree like string representation of this BST.
	 * @return string containing tree-like representation of this BST.
	 */
	@Override
	public String toStringTreeFormat() {

		StringBuilder s = new StringBuilder();

		preOrderPrint(root, root.height, s);
		return s.toString();
	}

	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * Utility functions 
	 * 
	 * References:
	 * .Code provided
	 * .http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
	 * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * */
	private void recRangeCheck(Node<Collision> node, String zip, Date dateBegin, Date dateEnd) {
		
		if(node == null) {
			return;
		}
		
		
		if(node.data.getDate().compareTo(dateBegin) < 0) {
			recRangeCheck(node.right, zip, dateBegin, dateEnd);
		}
		
		if(node.data.getDate().compareTo(dateBegin) >= 0 && node.data.getDate().compareTo(dateEnd) <= 0) {
			if(node.data.getZip().equals(zip)) {
				System.out.println(node.data.getKey());
				matches++;
				fatalities += node.data.getPersonsInjured(); 
				pedestriansF += node.data.getPedestriansKilled(); 
				cyclistsF += node.data.getCyclistsKilled();
				motoristsF += node.data.getMotoristsKilled();
				injuries += node.data.getPersonsInjured();
				pedestriansI += node.data.getPedestriansInjured(); 
				cyclistsI += node.data.getCyclistsInjured();
				motoristsI += node.data.getMotoristsInjured();
			}
			recRangeCheck(node.left, zip, dateBegin, dateEnd);
			recRangeCheck(node.right, zip, dateBegin, dateEnd);
		}
		
		if(node.data.getDate().compareTo(dateEnd) > 0) {
			recRangeCheck(node.left, zip, dateBegin, dateEnd);
		}
		
	}
	

	
	/**
	 * Calculate the height of a node.
	 * @param n Target node
	 * @return Returns the height of a target node.
	 */
	int height(Node n) {
		if(n == null)
			return 0;
		
		return n.height;
	}
	
	/**
	 * Does a right rotation around a a given node.
	 * @param pivot The node and which you are rotating the other nodes around
	 * @return
	 */
	Node<Collision> rotateRight(Node<Collision> pivot) {
		Node x = pivot.left;
		Node T2 = x.right;
		
		//perform rotation
		x.right = pivot;
		pivot.left = T2;
		
		//update heights
		pivot.height = Math.max(height(pivot.left),height(pivot.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		
		//calculate number of children 
		//which changed due to rotation
		int val = (T2 != null) ? T2.desc : 1;
		pivot.desc = pivot.desc - (x.desc + 1) + (val + 1);
		x.desc = x.desc - (val + 1) + (pivot.desc + 1);
		
		//return new root
		return x;
	}
	
	/**
	 * Does a left rotation around a a given node.
	 * @param pivot The node and which you are rotating the other nodes around
	 * @return
	 */
	Node<Collision> rotateLeft(Node<Collision> pivot) {
		Node y = pivot.right;
		Node T2 = y.left;
		
		//perform rotation
		y.left = pivot;
		pivot.right = T2;
		
		//update heights
		pivot.height = Math.max(height(pivot.left),height(pivot.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;
	
		//calculate number of children 
		//which changed due to rotation
		int val = (T2 != null) ? T2.desc : -1;
		pivot.desc = pivot.desc - (y.desc + 1) + (val + 1);
		y.desc = y.desc - (val + 1) + (pivot.desc + 1);
		
		//return new root
		return y;
	}
	
	/**
	 * Checks the height values of a nodes left and right children. If 0 the node is balanced. 
	 * @param n
	 * @return returns an integer.
	 */
	int getBalance(Node<Collision> n) {
		if(n == null)
			return 0;
		
		return height(n.left) - height(n.right);
	}
		
	/*
	 * Actual recursive implementation of add.  
	 * 
	 * @param item the new element to be added to the tree
	 */
	private Node<Collision> add(Node<Collision> node, Collision item) {
		
		// 1. Perform normal BST insertion
		if (node == null) { 
			numOfElements++;
			return new Node<Collision>(item);
		}
		
		if (node.data.compareTo(item) > 0) {
			node.left = add(node.left, item);
			node.desc++;
		}else if (node.data.compareTo(item) < 0) {
			node.right = add(node.right, item);
			node.desc++;
		}else {
			node.right = add(node.right, item);
			node.desc++;
		}
		
		// 2. Update height
		node.height = 1 + Math.max(height(node.left), height(node.right));
		
		// 3. Get the balance factor to check whether it has become unbalanced
		int balance = getBalance(node);
		
		// 4. If unbalanced there are only 4 possible cases
		if( balance > 1 && node.left.data.compareTo(item) > 0)
			return rotateRight(node);
		
		if(balance < -1 && node.right.data.compareTo(item) < 0)
			return rotateLeft(node);
		
		if(balance > 1 && node.left.data.compareTo(item) < 0) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		
		if(balance < -1 && node.right.data.compareTo(item) > 0) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node; 
	}
	
	/*
	 * Actual recursive implementation of remove method: find the node to remove.  
	 * 
	 * @param target the item to be removed from this tree 
	 */
	private Node<Collision> recRemove(Collision target, Node<Collision> node)
	{
		if (node == null)
			found = false;
		else if (target.compareTo(node.data) < 0) {
			node.left = recRemove(target, node.left);
			node.desc = node.desc - 1;
		}else if (target.compareTo(node.data) > 0) {
			node.right = recRemove(target, node.right );
			node.desc = node.desc -1;
		}else {
			node = removeNode(node);
			found = true;
		}
		
		//If the tree only had one node
		if(node == null)
			return node;
		
		// 2. Update height of the current node
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		
		// 3. Get the balance factor of current node
		int balance = getBalance(node);
		
		// 4. If unbalanced then there are 4 cases
		if(balance > 1 && getBalance(node.left) >= 0)
			return rotateRight(root);
		
		if(balance > 1 && getBalance(node.left) < 0) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		
		if(balance < -1 && getBalance(node.right) <= 0)
			return rotateLeft(node);
		
		if(balance <-1 && getBalance(node.right) > 0) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}
	
	/*
	 * Actual recursive implementation of remove method: perform the removal.  
	 * 
	 * @param target the item to be removed from this tree 
	 * @return a reference to the node itself, or to the modified subtree 
	 */
	private Node<Collision> removeNode(Node<Collision> node)
	{
		Collision data;
		if (node.left == null)
			return node.right ;
		else if (node.right  == null)
			return node.left;
		else {
			data = getPredecessor(node.left);
			node.data = data;
			node.left = recRemove(data, node.left);
			node.desc = node.desc -1;
			return node;
		}
	}
	
	/*
	 * Returns the information held in the rightmost node of subtree  
	 * 
	 * @param subtree root of the subtree within which to search for the rightmost node 
	 * @return returns data stored in the rightmost node of subtree  
	 */
	private Collision getPredecessor(Node<Collision> subtree)
	{
		if (subtree==null) throw new NullPointerException("getPredecessor called with an empty subtree");
		Node<Collision> temp = subtree;
		while (temp.right  != null)
			temp = temp.right ;
		return temp.data;
	}

	/*
	 * Actual recursive implementation of inorder traversal to produce string
	 * representation of this tree.
	 * 
	 * @param tree the root of the current subtree
	 * @param s the string that accumulated the string representation of this BST
	 */
	private void inOrderPrint(Node<Collision> tree, StringBuilder s) {
		if (tree != null) {
			inOrderPrint(tree.left, s);
			s.append(tree.data.toString() + "  ");
			inOrderPrint(tree.right , s);
		}
	}

	/*
	 * Actual recursive implementation of preorder traversal to produce tree-like string
	 * representation of this tree.
	 * 
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree to
	 *   determine the indentation of each item
	 * @param output the string that accumulated the string representation of this
	 *   BST
	 */
	private void preOrderPrint(Node<Collision> tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right , level + 1, output);
		}
		// uncomment the part below to show "null children" in the output
		/*else {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}*/
	}
}
