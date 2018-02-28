package designpattern.adapter;

public class Clienter {

	public static void main(String[] args) {
		Ps2 p = new Adapter();
		p.isPs2();

		Ps2 p2 = new Adapter2(new Usber());
		p2.isPs2();
		
		A a = new Ashili();
        a.a();
        a.d();
	}
}
