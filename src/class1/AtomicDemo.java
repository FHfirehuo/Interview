package class1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicDemo {

	private static volatile AtomicBoolean canExecutingFlag = new AtomicBoolean(true);

	/**
	 * 
	 * 业务逻辑处理:
	 * <ol>
	 * <li>Step 1</li>
	 * <li>Step 2</li>
	 * </ol>
	 */
	public void executeBusiLogic() {
		if (canExecutingFlag.compareAndSet(true, false)) {
			try {
				System.out.println(LocalDate.now() + " " + LocalTime.now() + "--" + Thread.currentThread().getName()
						+ "--处理业务逻辑开始...");
				Thread.sleep(5000);
				System.out.println(LocalDate.now() + " " + LocalTime.now() + "--" + Thread.currentThread().getName()
						+ "--处理业务逻辑完毕.");
			} catch (Exception e) {
				System.out.println(LocalDate.now() + " " + LocalTime.now() + "--" + Thread.currentThread().getName()
						+ "--处理业务逻辑失败!!!");
			} finally {
				canExecutingFlag.set(true);
			}
		} else {
			System.out.println(LocalDate.now() + " " + LocalTime.now() + "--" + Thread.currentThread().getName()
					+ "--已经存在处理中的业务，请稍后再试!");
		}
	}

	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);

		AtomicDemo demo = new AtomicDemo();

		for (int i = 0; i < 10; i++) {
			es.execute(new Runnable() {

				@Override
				public void run() {
					demo.executeBusiLogic();
				}
			});
		}
		System.out.println("循环结束");
		es.shutdown();
	}

}
