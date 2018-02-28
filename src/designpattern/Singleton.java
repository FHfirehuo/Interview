package designpattern;

/**
 * 
 * 
 * @author fire
 *
 */
public class Singleton {

	public static void main(String[] args) {
		Singleton1 s1 = Singleton1.getSingleton();
		s1.doSomething();
		Singleton2 s2 = Singleton2.getSingleton();
		s2.doSomething();
	}

}

/**
 * 线程安全的
 * @author fire
 *
 */
class Singleton1 {

	private static final Singleton1 singleton = new Singleton1();

	private Singleton1() {

	}

	public static Singleton1 getSingleton() {
		return singleton;
	}

	public void doSomething() {
		System.err.println("Singleton1");

	}

}

/**
 * 线程不安全的
 * @author fire
 *
 */
class Singleton2 {
	private static Singleton2 singleton = null;

	private Singleton2() {

	}

	public static Singleton2 getSingleton() {
		if (singleton == null) {
			singleton = new Singleton2();
		}
		return singleton;
	}

	public void doSomething() {
		System.err.println("Singleton2");

	}

}

/**
 * 线程安全的
 * @author fire
 *
 */
class Singleton3 {
	private static Singleton3 singleton = null;

	private Singleton3() {

	}

	public static synchronized Singleton3 getSingleton() {
		if (singleton == null) {
			singleton = new Singleton3();
		}
		return singleton;
	}

	public void doSomething() {
		System.err.println("Singleton2");

	}

}
