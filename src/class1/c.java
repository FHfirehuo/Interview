package class1;

public class c {

	public static void main(String[] args) {
		c c =new c();
		System.out.println(c.add(9, 34));

	}
	
	public int add(int a, int b){
		try {
			return a +b;
		} catch(Exception e){
			System.out.println("catch");
		} finally {
			System.out.println("finally");
		}
		
		return 0;
	}

}
