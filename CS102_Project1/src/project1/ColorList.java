package project1;

import java.util.ArrayList;

public class ColorList extends ArrayList<Color> {

	//protected static ColorList instance;
	
	public ColorList() {
		
	}
	
	public Color getColorByName(String colorName) {
	
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getName() != null && this.get(i).getName().equalsIgnoreCase(colorName)) {
					//System.out.print("hit");
			return this.get(i);
			}
			
		}
		
		 return null ;
	}
	
	public Color getColorByHexValue(String colorHexValue) {
		for(Color i : this) {
			if(i.getHexValue().equalsIgnoreCase(colorHexValue)) {
				return i;
			}
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
