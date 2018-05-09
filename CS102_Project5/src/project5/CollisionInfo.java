package project5;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class CollisionInfo {

	public static void main(String[] args) throws FileNotFoundException {
		File csvFile = null;
		Scanner inCSV = null;
		String line = "";
		ArrayList<String> lineData = null;
		
		try {
			csvFile = new File(args[0]);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			inCSV = new Scanner(csvFile);
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Declare and initialize CollisionsData object
		CollisionsData collisionsData = new CollisionsData();
		Collision collision = null;
		
		//Ignore the header line of the CSV file
		line = inCSV.nextLine();
			
		while(inCSV.hasNextLine()) {
			line = inCSV.nextLine();
			lineData = splitCSVLine(line);
			collision = new Collision(lineData);
			if(collision.isValid())
				collisionsData.add(collision);

		}
		inCSV.close();
		
		String userString = "";
		String dateStringA = "";
		String dateStringB = "";
		Scanner userInput = new Scanner(System.in);
		while(userString.compareToIgnoreCase("quit") != 0) {
			System.out.println("Enter a zip code (’quit’ to exit): ");
			userString = userInput.next();
			
			if(userString.matches("[0-9]+") && userString.length() == 5) {
				
				System.out.println("Enter start date (MM/DD/YYYY): ");
				dateStringA = userInput.next();
				try{
					Date test = new Date(dateStringA);
				}catch(Exception e) {
					dateStringA = "";
					System.err.println("Invalid date format. Try again.");
				}
				
				if(!dateStringA.isEmpty()) {
					System.out.println("Enter end date (MM/DD/YYYY): ");
					dateStringB = userInput.next();
					try{
						Date test = new Date(dateStringB);
						if(test.compareTo(new Date(dateStringA)) < 0) {
							System.err.println("Invalid date format. Try again.");
						}else {
							System.out.println(collisionsData.getReport(userString, new Date(dateStringA), test));
						}
					}catch(Exception e) {
						dateStringB = "";
						System.err.println("Invalid date format. Try again.");
					}
					
				}
			}else {
				System.err.println("Invalid zip code. Try again.");
			}
		}
		
		userInput.close();
	}
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
	public static ArrayList<String> splitCSVLine(String textLine){
		
		ArrayList<String> entries = new ArrayList<String>(); 
		int lineLength = textLine.length(); 
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar; 
		boolean insideQuotes = false; 
		boolean insideEntry= false;
		
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
					
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;
				}else {
					insideQuotes = true; 
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
				// add it to the current entry 
					nextWord.append( nextChar );
				}else { // skip all spaces between entries
					continue; 
				}
			} else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar); 
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			} 
			
		}
		// add the last word ( assuming not empty ) 
		// trim the white space before adding to the list 
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	

}
