package project2;

import java.util.ArrayList;

/**
 * A sorted collection.  The element in this list are stored in order determined
 * by the natural ordering of those elements (i.e., the {@code compareTo()} method
 * defined in the elements' class).  
 *
 * @author  Joel Forrester
 */

public class ColorList extends OrderedLinkedList<Color> {

	public ColorList() {
		
	}
	
	/**
     * Finds color by searching for color name. Returns Color
     * @param colorName the name of the color
     */
	public Color getColorByName(String colorName) {
	
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getName() != null && this.get(i).getName().equalsIgnoreCase(colorName)) {
			return this.get(i);
			}
			
		}
		
		 return null ;
	}
	
	/**
     * Finds color by searching the list for the hexValue. Returns Color
     * @param colorHexValue the hex value of the color
     */
	public Color getColorByHexValue(String colorHexValue) {
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getHexValue().equalsIgnoreCase(colorHexValue))
			return this.get(i);
		}
		
		 return null;
	}
	
	private int IterateList(int i) {
		int num = i;
		
		if(this.get(num).getName() == null) {
			num++;
			if(num < this.size()) {
				IterateList(num);
			}
		}
		
		return num;
	}
	

}
