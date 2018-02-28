package class1;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/** 
 * @author nspace 
 *         代码中存在一个小问题，就是accessCount的释放后，存在负数的情况，也就是说高并发的情况下每秒会存在>MAX_QPS次的并发访问次数 
 *         ，还不能做到非常精确控制。期待大家更加简单和轻量的方式 
 */ 
public class FlowConcurrentController {

	// 每秒并发访问控制数量  
    final static int MAX_QPS = 10;  
    // 并发控制信号量  
    final static Semaphore semaphore = new Semaphore(MAX_QPS);  
    // 监控每秒并发访问次数（理论上accessCount.get() <= 10）  
    final static AtomicInteger accessCount = new AtomicInteger(0);  
  
    // 模拟远程访问  
    private static void remoteCall(int i, int j) {  
        System.out.println(String.format("%s - %s: %d %d", new Date(), Thread.currentThread(), i, j));  
    }  
  
    private static void releaseWork() { // 每秒release一次  
        // release semaphore thread  
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {  
            @Override  
            public void run() {  
                semaphore.release(accessCount.get());  
                accessCount.set(0);  
                System.out.println("clean it!!!!!!!!!!!!!!!!");  
            }  
        }, 1000, 1000, TimeUnit.MILLISECONDS);  
    }  
  
    // 模拟并发访问控制  
    private static void simulateAccess(final int m, final int n) throws Exception {           
        // m :线程数；      // n : 调用数  
        ExecutorService pool = Executors.newFixedThreadPool(100);  
        for (int i = m; i > 0; i--) {  
            final int x = i;  
            pool.submit(new Runnable() {  
                @Override  
                public void run() {  
                    for (int j = n; j > 0; j--) {  
                        try {  
                            Thread.sleep(10000);  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                        }  
                        semaphore.acquireUninterruptibly(1);  
                        accessCount.incrementAndGet();  
                        remoteCall(x, j);  
                    }  
                }  
            });  
        }  
        pool.shutdown();  
        pool.awaitTermination(1, TimeUnit.HOURS);  
    }  
  
    public static void main(String[] args) throws Exception {  
        // 开启releaseWork  
        releaseWork();  
        // 开始模拟lots of concurrent calls: 100 * 1000  
        simulateAccess(100, 1);  
    }  
}
