package project5;

import java.util.ArrayList;

public class Collision implements Comparable<Collision> {

	//All variables contained in the data sheet
	private Date date;
	private String time;
	private String borough;
	private String zipcode;
	private double lattitude;
	private double longitude;
	private String onStreetName;
	private String crossStreetName;
	private int personsInjured;
	private int personsKilled;
	private int pedestriansInjured;
	private int pedestriansKilled;
	private int cyclistsInjured;
	private int cyclistsKilled;
	private int motoristsInjured;
	private int motoristsKilled;
	private String contributingFactorVehicle1;
	private String contributingFactorVehicle2;
	private String contributingFactorVehicle3;
	private String contributingFactorVehicle4;
	private String key;
	private String vehicleTypeCode1;
	private String vehicleTypeCode2;
	private String vehicleTypeCode3;
	private String vehicleTypeCode4;
	private String vehicleTypeCode5;
	
	//Check for checking valid Dataset entry
	private boolean isValid;
	
	public Collision(ArrayList<String> entries) throws IllegalArgumentException{
		
		// 1. HANDLE ALL REQUIRED VALUES FIRST
		
		// Try and set the date using date entry from CSV. If this fails set validity to false and print err msg
		try {
			date = new Date(entries.get(0));
		}catch(Exception e){
			isValid = false;
			//e.printStackTrace();
			return;
		}
		
		//If the check zipCode entry for CSV if print error and set validity to false.
		if(entries.get(3).matches("[0-9]+") && entries.get(3).length() == 5) {
			zipcode = entries.get(3);
		}else {
			isValid = false;
			//throw new IllegalArgumentException("Invalid ZIP code:\n 	Must be five character string of digits ranging form [0-9]");
			//System.err.println("Invalid ZIP code:\n 	Must be five character string of digits ranging form [0-9]");
			return;
		}
		
		//Check all the CSV entries for injured/killed. If any are negative set validity to false.
		for(int i = 10; i < 17; i++) {
			if(Integer.parseInt(entries.get(i)) < 0) {
				isValid = false;
				//System.err.println("Invalid Value:\n 	Can not be less than 0");
				return;
			}
		}
		
		// Check the CSV entries for keys. If no key is found set validity to false.
		if(entries.get(23).isEmpty()) {
			isValid = false;
			//System.err.println("Invalid Key:\n 	No Key Found");
			return;
		}else {
			key = entries.get(23);
		}
		
		//If all the above checks passed this is a valid entry
		isValid = true;
	
		//2. Add rest of values from data set
		time = entries.get(1);
		borough = entries.get(2);
		crossStreetName = entries.get(8);
		personsInjured = Integer.parseInt(entries.get(10));
		personsKilled = Integer.parseInt(entries.get(11));
		pedestriansInjured = Integer.parseInt(entries.get(12));
		pedestriansKilled = Integer.parseInt(entries.get(13));
		cyclistsInjured = Integer.parseInt(entries.get(14));
		cyclistsKilled = Integer.parseInt(entries.get(15));
		motoristsInjured = Integer.parseInt(entries.get(16));
		motoristsKilled = Integer.parseInt(entries.get(17));
		contributingFactorVehicle1 = entries.get(18);
		contributingFactorVehicle2 = entries.get(19);
		contributingFactorVehicle3 = entries.get(20);
		contributingFactorVehicle4 = entries.get(21);
		key = entries.get(23);
		onStreetName = entries.get(7);
		vehicleTypeCode1 = entries.get(24);
		vehicleTypeCode2 = entries.get(25);
		vehicleTypeCode3 = entries.get(26);
		vehicleTypeCode4 = entries.get(27);
		if(entries.size() == 29) {
			vehicleTypeCode5 = entries.get(28);
		}
		
		if(!entries.get(4).isEmpty()) {
			lattitude = Double.parseDouble(entries.get(4));
		}
		
		if(!entries.get(5).isEmpty()) {
			longitude = Double.parseDouble(entries.get(5));
		}
		
	}
	
	/*
	 * Returns the zip code of this collision
	 */
	public String getZip() {
		return zipcode;
	}
	
	/*
	 * Returns the data the collision occurred
	 */
	public Date getDate() {
		return date;
	}
	
	/*
	 * Returns unique key associated with collision
	 */
	public String getKey() {
		return key;
	}
	
	/*
	 * Return the total amount of persons injured by this collision
	 */
	public int getPersonsInjured() {
		return personsInjured;
	}
	
	/*
	 * Returns the amount of pedestrians injured by this collision
	 */
	public int getPedestriansInjured() {
		return pedestriansInjured;
	}
	
	/*
	 * Returns the amount of cyclists injured by this collision
	 */
	public int getCyclistsInjured() {
		return cyclistsInjured;
	}
	
	/*
	 * Returns the amount of motorists injured by this collision
	 */
	public int getMotoristsInjured() {
		return motoristsInjured;
	}
	
	/*
	 * Returns the total amount of persons killed by this collision
	 */
	public int getPersonsKilled() {
		return personsKilled;
	}
	
	/*
	 * Returns the amount of pedestrians killed by this collision
	 */
	public int getPedestriansKilled() {
		return pedestriansKilled;
	}
	
	/*
	 * Returns the amount of cyclists killed by this collision
	 */
	public int getCyclistsKilled() {
		return cyclistsKilled;
	}
	
	/*
	 * Returns the amount of motorists killed by this collision
	 */
	public int getMotoristsKilled() {
		return motoristsKilled;
	}
	
	/*
	 * Returns whether are not this collision is an valid entry to a CollisionData dataset
	 */
	public boolean isValid() {
		return isValid;
	}

	@Override
	public int compareTo(Collision obj) {
		if(!zipcode.equals(obj.zipcode))
			return zipcode.compareTo(obj.zipcode);
		
		if(!date.equals(obj.date))
			return date.compareTo(obj.date);
		
		if(!key.equals(obj.key))
			return key.compareTo(obj.key);
		
		return 0;
	}
	

	public boolean equals(Collision obj) {
		if(zipcode.equals(obj.zipcode) && date.equals(obj.date) && key.equals(obj.key))
			return true;
		else
			return false;
	}
	
}
