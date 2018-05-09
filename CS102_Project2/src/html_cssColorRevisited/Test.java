package html_cssColorRevisited;

public class Test {

	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		
		Color red = new Color("#9932CC");
		Color c2 = new Color("#92A456");
		Color c3 = new Color("#92f456");
		Color c4 = null;
		
		ColorList test = new ColorList();
		test.add(red);
		test.add(c2);
		test.add(c3);
		
		
		ColorList test2 = new ColorList();
		test2.add(red);
		test2.add(c2);
		test2.add(c3);
		
		ColorList test3 = (ColorList) test2.clone();
		
	
		String testInput = "Black,#FFFFFF";
		String[] split = testInput.split(",");

		
		System.out.print(c3.equals(c4));
	

		OrderedLinkedList<String> stringList = new OrderedLinkedList<String>();
		String s1 = "apple";
		stringList.add(s1);
		

		System.out.println(stringList);
		System.out.println(stringList.remove("apple"));
		System.out.println(stringList);

		
	}

}
