package html_cssColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ColorConverter {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.err.printf("ERROR: incorrect usage.\n\n");
			System.err.printf("Usage: java color_list inputFile outputFile\n\n" );
			
		}

		File fileColors = new File(args[0]);
		
		if(!fileColors.exists() || !fileColors.canRead()) {
			System.err.printf("ERROR: cannot read file %s.\n\n", args[0]);
			System.exit(1);
		}
		
		Scanner inColors = new Scanner (fileColors);
		
		ColorList colorList = new ColorList();
		
		while(inColors.hasNext()) {
			String line = inColors.nextLine();
			
			String[] colorData = line.split(",");
			if(colorData.length != 2) continue;
			
			colorData[0] = colorData[0].trim();
			colorData[1] = colorData[1].trim();
			
			colorList.add(new Color(colorData[1], colorData[0]));
		}
		
		inColors.close();
	
		String hexInput = "";
		
		Scanner userInput = new Scanner(System.in);
		
		while(hexInput.compareTo("quit") != 0) {

			System.out.println("Enter the color in HEX format (#RRGGBB) or 'quit' to stop: ");
			hexInput = userInput.next();
			
			try {
			Color c = new Color(hexInput.trim());
			if(c.getName() == null) {
				for(Color i : colorList) {
					if(c.getHexValue().equalsIgnoreCase(i.getHexValue())) {
						c.setName(i.getName());
					}
				}
			}
			
			System.out.println("Color information:");
			System.out.println(c.toString());
			
			}catch(IllegalArgumentException e) {
				if(!hexInput.equalsIgnoreCase("quit")) {
					
					System.err.println(e.getMessage());
				}
			}

		}
		
		userInput.close();
		
	}

}
