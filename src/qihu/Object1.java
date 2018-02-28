package qihu;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Object 有哪些方法
 * 
 * @author fire
 *
 */
public class Object1 {

	public static void main(String[] args) {
		
		Object o =new Object();
		o.getClass();
		/**
		 * equals 和  hashCode 作用
		 */
		o.equals("");
		o.hashCode();
		//o.notify();
		//o.notifyAll();
		//o.toString();
		/*try {
			o.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		 Map<String, String>  map = new  ConcurrentHashMap<String, String>();
		map.put("a", "a");
		map.size();
		map.get("a");
		map.putAll(map);
		
		Map<String, String>  map1 = new HashMap<>();
		String a = map1.put("a", "a");
		System.out.println(a);
	}
}
