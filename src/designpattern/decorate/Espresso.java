package designpattern.decorate;

public class Espresso extends Coffee {

	public double cost(){  
        double price = super.cost();  
        price += 2.5;  
        return price;  
    } 
}
