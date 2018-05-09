package project3;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *JUnit Test for PossibleLocationsStack class
 * 
 * @author Joel Forrester
 *
 */
public class PossibleLocationsStackTest {

	@Test
	public void testAdd() {
		PossibleLocationsStack plsTest = new PossibleLocationsStack();
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		
		plsTest.add(l1);
		plsTest.add(l2);
		
		//Test add, since we added it last the values of l2 should be at the top of the Possible Locations Stack
		assertTrue("add method incorrect, the value at the top should be 0,1", plsTest.top().getRow() == 0 && plsTest.top().getColumn() == 1);
	}

	@Test
	public void testIsEmpty() {
		PossibleLocationsStack plsTest = new PossibleLocationsStack();
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		
		//Test isEmpty, should return true because we did not add any Locations 
		assertTrue("isEmpty method incorrect, the value at the top should be true", plsTest.isEmpty() == true);
		
		plsTest.add(l1);
		plsTest.add(l2);

		//Test isEmpty, after adding Locations should return false
		assertTrue("isEmpty method incorrect, the value at the top should be false", plsTest.isEmpty() == false);
	}

	@Test
	public void testRemove() {
		PossibleLocationsStack plsTest = new PossibleLocationsStack();
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		Location l3 = new Location(0,2);
		
				
		plsTest.add(l1);
		plsTest.add(l2);
		
		Location removed = plsTest.remove();
		
		//Test remove, the value of removed should be equal to l2 because it was at the top of the stack
		assertTrue("remove method incorrect, the value returned should be 0,1", removed.getRow() == 0 && removed.getColumn() == 1);
		
		plsTest.remove();
		removed = plsTest.remove();
		
		//Test remove, the value of removed should be equal to null because we removed all elements from the stack prior to setting removed
		assertTrue("remove method incorrect, the value returned should be null", removed == null);
	}

	@Test
	public void testSize() {
		PossibleLocationsStack plsTest = new PossibleLocationsStack();
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		Location l3 = new Location(0,2);
		
		//Test size, since we have not added any locations the value of size should be 0
		assertTrue("size method incorrect, the value returned should be 0", plsTest.size() == 0);
				
		plsTest.add(l1);
		plsTest.add(l2);
		
		//Test size, since we have added 2 locations the value of size should be 2
		assertTrue("size method incorrect, the value returned should be 2", plsTest.size() == 2);
		
		plsTest.remove();
		
		//Test size, since we have removed 1 locations the value of size should be 1
		assertTrue("size method incorrect, the value returned should be 1", plsTest.size() == 1);
		
		plsTest.remove();
		plsTest.remove();
		
		//Test size, since we  removed all locations the value of size should be 0
		assertTrue("size method incorrect, the value returned should be 1", plsTest.size() == 0);
	}

	@Test
	public void testTop() {
		PossibleLocationsStack plsTest = new PossibleLocationsStack();
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		Location l3 = new Location(0,2);
		
		plsTest.add(l1);
		plsTest.add(l2);
		
		//Test add, since we added it last the values of l2 should be at the top of the Possible Locations Stack
		assertTrue("add or top method incorrect, the value at the top should be 0,1", plsTest.top().getRow() == 0 && plsTest.top().getColumn() == 1);
		
		plsTest.add(l3);
		
		//Test add, since we added it last the values of l2 should be at the top of the Possible Locations Stack
		assertTrue("add or top method incorrect, the value at the top should be 0,2", plsTest.top().getRow() == 0 && plsTest.top().getColumn() == 2);
	}

}
