package project3;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *JUnit Test for PossibleLocationsQueue class
 * 
 * @author Joel Forrester
 *
 */
public class PossibleLocationsQueueTest {

	@Test
	public void testPossibleLocationsQueue() {
		try {
			PossibleLocationsQueue plqTest = new PossibleLocationsQueue();
		}catch(Exception e) {
			fail("Error with PossibleLocationsQueue() \n" + e.getMessage());
		}
	}

	@Test
	public void testPossibleLocationsQueueInt() {
		try {
			PossibleLocationsQueue plqTest = new PossibleLocationsQueue(5);
		}catch(Exception e) {
			fail("Error with PossibleLocationsQueue(int capacity) \n" + e.getMessage());
		}
	}

	@Test
	public void testAdd() {
		PossibleLocationsQueue plqTest = new PossibleLocationsQueue(2);
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		Location l3 = new Location(0,2);
		Location l4 = new Location(0,3);
		
		plqTest.add(l1);
		plqTest.add(l2);
		
		assertTrue("The location removed should be equal to l1", plqTest.remove() == l1);
		
		try {
			plqTest.add(l3);
			plqTest.add(l4);
		}catch(Exception e) {
			fail("Error with add method\n" + e.getMessage());
		}
		
		
		assertTrue("The location removed should be equal to l2", plqTest.remove() == l2);
		assertTrue("The location removed should be equal to l3", plqTest.remove() == l3);
		assertTrue("The location removed should be equal to l4", plqTest.remove() == l4);
		
	}

	@Test
	public void testIsEmpty() {
		PossibleLocationsQueue plqTest = new PossibleLocationsQueue(2);
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		
		//Test isEmpty, should return true because we did not add any Locations 
		assertTrue("isEmpty method incorrect, the value at the top should be true", plqTest.isEmpty() == true);
		
		plqTest.add(l1);
		plqTest.add(l2);

		//Test isEmpty, after adding Locations should return false
		assertTrue("isEmpty method incorrect, the value at the top should be false", plqTest.isEmpty() == false);
		
		 plqTest.remove();
		 plqTest.remove();
		 plqTest.remove(); 
		//Test isEmpty, should return true because we removed all Locations 
		assertTrue("isEmpty method incorrect, the value at the top should be true", plqTest.isEmpty() == true);
	}

	@Test
	public void testRemove() {
		PossibleLocationsQueue plqTest = new PossibleLocationsQueue(2);
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);

		
		plqTest.add(l1);
		plqTest.add(l2);
		
		assertTrue("The location removed should be equal to l1", plqTest.remove() == l1);
		assertTrue("The location removed should be equal to l2", plqTest.remove() == l2);
		assertTrue("The location removed should be equal to l3", plqTest.remove() == null);

	}

	@Test
	public void testSize() {
		PossibleLocationsQueue plqTest = new PossibleLocationsQueue(2);
		Location l1 = new Location(0,0);
		Location l2 = new Location(0,1);
		Location l3 = new Location(0,2);
		
		//Test size, since we have not added any locations the value of size should be 0
		assertTrue("size method incorrect, the value returned should be 0", plqTest.size() == 0);
				
		plqTest.add(l1);
		plqTest.add(l2);
		
		//Test size, since we have added 2 locations the value of size should be 2
		assertTrue("size method incorrect, the value returned should be 2", plqTest.size() == 2);
		
		plqTest.remove();
		
		//Test size, since we have removed 1 locations the value of size should be 1
		assertTrue("size method incorrect, the value returned should be 1", plqTest.size() == 1);
		
		plqTest.remove();
		plqTest.remove();
		
		//Test size, since we  removed all locations the value of size should be 0
		assertTrue("size method incorrect, the value returned should be 1", plqTest.size() == 0);
	}

}
