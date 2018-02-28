package class1;

public class Interrupt1 {

	 /**
     * @param args
     */
    public static void main(String[] args) {
    	Interrupt1 main = new Interrupt1();
        Thread t = new Thread(main.runnable);
        System.out.println("mainmainmain");
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t.interrupt();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int i = 0;
            try {
                while (i < 1000) {
                    Thread.sleep(500);
                    System.out.println(i++);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
			
}
