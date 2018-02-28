package designpattern.decorate;

public class Test {

	public static void main(String[] args) {
		Coffee coffee = new Decaf();
		coffee.addMocha();
		coffee.addWhip();
		// 2.6
		System.out.println(coffee.cost());

	}

}
