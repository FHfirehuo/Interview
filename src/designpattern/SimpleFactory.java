package designpattern;

public class SimpleFactory {

	public static void main(String[] args) {
		BMW320 bmw320 = new BMW320();
		BMW523 bmw523 = new BMW523();
		
		
		Factory factory = new Factory();  
        BMW bmw3201 = factory.createBMW(320);  
        BMW bmw5231 = factory.createBMW(523);
	}
}

class BMW320 extends BMW {
	public BMW320() {
		System.out.println("制造-->BMW320");
	}
}

class BMW523 extends BMW {
	public BMW523() {
		System.out.println("制造-->BMW523");
	}
}

abstract class BMW {  
    public BMW(){  
          
    }  
}

class Factory {  
    public BMW createBMW(int type) {  
        switch (type) {  
          
        case 320:  
            return new BMW320();  
  
        case 523:  
            return new BMW523();  
  
        default:  
            break;  
        }  
        return null;  
    }  
} 