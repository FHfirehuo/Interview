package daojia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class text {

	public static void main(String[] args) {
		
		// 怎么获取他的size
		HashMap<String, String> a = new HashMap<String, String>();
		a.size();
		
		/**
		 * 1、 ConcurrentHashMap的实现
		 *  2、 怎么确保她是线程安全的
		 *  3、 get 和 put 那个是安全的
		 */
		ConcurrentHashMap<String, String> b = new ConcurrentHashMap<String, String>();
		b.get("a");
		b.put("a", "v");
		
		// 有序的map
		LinkedHashMap<String, String> c = new LinkedHashMap<String, String>();
		
		List<String> d = new ArrayList<>();
		
		c.get(c.size()-2);
		
		ConcurrentLinkedQueue<String> f;
		
		ExecutorService es = Executors.newFixedThreadPool(1);
		
		
	}
}


class test1 implements Callable<String> {

	@Override
	public String call() throws Exception {
		return null;
	}
	
}