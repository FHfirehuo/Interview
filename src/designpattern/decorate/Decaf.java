package designpattern.decorate;

public class Decaf extends Coffee {

	public double cost() {
		double price = super.cost();
		price += 2.0;
		return price;
	}
}
