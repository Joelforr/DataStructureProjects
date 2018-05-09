package project1;

public class Color extends ColorConverter implements Comparable<Color>{

	private String hexValue;
	private String name;
	private int red;
	private int green;
	private int blue;
	
	private final int MIN_RGB = 0;
	private final int MAX_RGB = 255;
	
	public Color(String colorHexValue) {
		
		if(colorHexValue.length() != 7 || colorHexValue.charAt(0) != '#') {
				throw new IllegalArgumentException("Invalid Argument: 'colorHexValue' must match format #XXXXXX");
		}else {
		
			for(int i = 1; i < colorHexValue.length(); i++) {
			
				if(Character.digit(colorHexValue.charAt(i), 16) == -1) {
					throw new IllegalArgumentException("Invalid Argument: Invalid hexCode value.");
				}else {
					hexValue = colorHexValue;
					red = ConvertToInt(colorHexValue.charAt(1),colorHexValue.charAt(2));
					green = ConvertToInt(colorHexValue.charAt(3),colorHexValue.charAt(4));
					blue = ConvertToInt(colorHexValue.charAt(5),colorHexValue.charAt(6));

				}
			}
		}
	}
	
	public Color(String colorHexValue, String colorName) {
		
		if(colorHexValue.length() != 7 || colorHexValue.charAt(0) != '#') {
			throw new IllegalArgumentException("Invalid Argument; 'colorHexValue' must match format #XXXXXX");
		}else {
			
			for(int i = 1; i < colorHexValue.length(); i++) {
				
				if(Character.digit(colorHexValue.charAt(i), 16) == -1) {
					throw new IllegalArgumentException("Invalid Argument; Invalid hexCode value.");
				}else {
					hexValue = colorHexValue;
					red = ConvertToInt(colorHexValue.charAt(1),colorHexValue.charAt(2));
					green = ConvertToInt(colorHexValue.charAt(3),colorHexValue.charAt(4));
					blue = ConvertToInt(colorHexValue.charAt(5),colorHexValue.charAt(6));
					name = colorName;
				}
			}
		}
		
		
	}
	
	public Color(int r, int g, int b){
		if(!ValidRange(r, g, b, MIN_RGB, MAX_RGB)) {
			throw new IllegalArgumentException("Invalid Argument; Values must be between the range 0 to 255");
		}else {
		red = r;
		green = g;
		blue = b;
		
		hexValue = ConvertToHex(red,green,blue);
		}
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHexValue() {
		return hexValue.toUpperCase();
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public boolean equals(Color other) {
		return this.hexValue.equalsIgnoreCase(other.hexValue);
		
	}
	

	@Override
	public int compareTo(Color other) {
		
		return this.getHexValue().compareToIgnoreCase(other.getHexValue());
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getHexValue());
		sb.append(", (");
		sb.append(this.getRed());
		sb.append(", ");
		sb.append(this.getGreen());
		sb.append(", ");
		sb.append(this.getBlue());
	
		if(this.getName() != null) {
			sb.append("), ");
			sb.append(this.getName());
		}else {
			sb.append(")");
		}
		return sb.toString();
	}
	
	private int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}

	private String ConvertToHex(int r, int g, int b) {
		StringBuilder sb = new StringBuilder("#");
		if(Integer.toHexString(r).length() == 1) {
			sb.append("0");
		}
		sb.append(Integer.toHexString(r));
		if(Integer.toHexString(g).length() == 1) {
			sb.append("0");
		}
		sb.append(Integer.toHexString(g));
		if(Integer.toHexString(g).length() == 1) {
			sb.append("0");
		}
		sb.append(Integer.toHexString(b));
		return sb.toString();
	}
	
	private int ConvertToInt(char a, char b) {
		StringBuilder sb = new StringBuilder();
		sb.append(a);
		sb.append(b);
		String hex = sb.toString();
		return Integer.parseInt(hex, 16);
		
	}
	
	private boolean ValidRange(int r, int g, int b, int min, int max) {
		if(r < min || r > max || g < min || g > max || b < min || b > max) {
			return false;
		}else {
			return true;
		}
	}

}


