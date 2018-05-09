package html_cssColor;

public class Test {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Color red = new Color("#9932CC");
		Color c2 = new Color("#92A456");
		Color c3 = new Color("#92f456");
		
		
		ColorList test = new ColorList();
		test.add(red);
		test.add(c2);
		test.add(c3);
	
		String testInput = "Black,#FFFFFF";
		String[] split = testInput.split(",");
		
//		for(int i = 1; i < split[1].length(); i++) {
//			System.out.println(Character.digit(split[1].charAt(i), 16));
//		}
		try {
		System.out.print(test.getColorByName("orange"));
		}finally {
				
		}
		System.out.println(red.toString());
		
		//System.out.println(split[0] + " " + split[1]);
		
		System.out.println(Integer.toHexString(01));
		System.out.println(Integer.parseInt("1", 16));
		System.out.println(Integer.parseInt("923456", 16));
		System.out.println(Character.digit('7',16));
		//System.out.println(Integer.toBinaryString(red.getRed()));
		//System.out.println(Integer.toHexString(red.getRed()));
		
	}

}
