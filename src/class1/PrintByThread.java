package class1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 63289 on 2017/4/2.
 */
class ThreadGroup implements Runnable{
    //所有thread共享同一个单例计数器，记录输出总数
    //private static Integer count=new Integer(0);
    
    private static AtomicInteger count = new AtomicInteger(0);
    
    //用于输出
    private PrintWriter printWriter;
    //轮到奇数线程则为true，偶数线程为false
    private boolean isOdd;
    //输入的转换成数组的长度2000的文件
    private int[] rec;
    //记录奇数光标位置
    private int oddPosition=0;
    //记录偶数光标位置
    private int evenPosition=0;
    
    CountDownLatch latch;
    
    //构造器
    public ThreadGroup(PrintWriter printWriter, int[] rec, CountDownLatch cdl, boolean isOdd){
        this.printWriter=printWriter;
        this.rec=rec;
        this.isOdd=isOdd;
        this.latch = cdl;
        System.out.println(Thread.currentThread().getName() + ":"+rec.length);
    }
    //管理计数器和输出
    private static synchronized void increaseCountAndPrintInfo(){
    	int a = count.incrementAndGet();
        //++count;
        if(a%1000==0){
            System.out.println("当前完成数量："+count);
            
        }
    }

    @Override
    public void run(){
        //循环打印
        /*while (print()){

        }*/
    	synchronized (rec){
    		if (isOdd) {
                //如果是奇数
                int num = 0;
                for(int i = 0; i < 2000; i++){
                	if (rec[i] % 2 == 1) {
                        //统计本轮输出了多少数
                        //输出
                        //System.out.println(Thread.currentThread().getName() + "奇數"+num+"輸出" + rec[i]);
                		//System.out.println(Thread.currentThread().getName() +":"+ i);
                        printWriter.print(rec[oddPosition] + " ");
                        printWriter.flush();
                        //控制台输出
                        increaseCountAndPrintInfo();
                        if(++num == 10  ){
                    		//System.out.println("奇數"+num);
                    		num = 0;
                    		rec.notifyAll();
                    		if(i < 1999){
                    			try {
        							rec.wait();
        						} catch (InterruptedException e) {
        							// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
                    		}
                    		
                    	}
                    }
                	
                	
            	}
                
                
                
            } else {
                
                int num = 0;
                for(int i = 0; i < 2000; i++){
                	if (rec[i] % 2 == 0) {
                		//System.out.println(Thread.currentThread().getName() +":"+ i);
                       // System.out.println(Thread.currentThread().getName() + "偶數" +num+"輸出" + rec[i]);
                        printWriter.print(rec[evenPosition] + " ");
                        printWriter.flush();
                        increaseCountAndPrintInfo();
                        if(++num == 10){
                    		rec.notifyAll();
                    		//System.out.println("偶數"+num);
                    		
                    		if(i < 1999){
                    			num = 0;
                    			try {
        							rec.wait();
        						} catch (InterruptedException e) {
        							// TODO Auto-generated catch block
        							e.printStackTrace();
        						}
                    		}
                    	}
                    }
                	
                	
                	
            	}
            }
    		
    	}
    	
    	System.out.println(Thread.currentThread().getName() +":"+"countDown");
    	
        
        latch.countDown();
        System.out.println(latch.getCount());
    }
}
public class PrintByThread {
    //生成一个随机的10000数字文件，解释略
    private static boolean setUpFile(){
        try{
            PrintWriter printWriter=new PrintWriter(new FileWriter(new File("input.txt"),true));
            Random random=new Random();
            for(int i=0;i<10000;++i){
            	//System.out.println(Math.abs(random.nextInt()%100));
                printWriter.print(Math.abs(random.nextInt()%100)+" ");
            }
            printWriter.flush();
            printWriter.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) throws InterruptedException{
    	CountDownLatch cdl = new CountDownLatch(10);
        try {
            //如果生成文件成功
            if(setUpFile()){
                //读取并转换为String数组
                BufferedReader reader=new BufferedReader(new FileReader("input.txt"));
                String str=reader.readLine();
                reader.close();
                String[] strs=str.split(" ");
                //分5组把String数组转换成int数组发给线程
                int j=0;
                for(int i=0;i<5;++i){
                    int[] rec=new int[2000];
                    for(int k=0;k<2000;++k){
                        rec[k]=Integer.parseInt(strs[j]);
                        ++j;
                    }


                    PrintWriter printWriter=new PrintWriter(new FileWriter(new File("output"+i+".txt")),true);
                    ThreadGroup threadGroup1=new ThreadGroup(printWriter,rec, cdl, true);
                    ThreadGroup threadGroup2=new ThreadGroup(printWriter,rec, cdl, false);
                    //开启两个同组线程
                    new Thread(threadGroup1, "Thread"+(2*i + 1)).start();
                    new Thread(threadGroup2, "Thread"+(2*(i+1))).start();
                   // break;
                }
            }
            cdl.await();
            System.out.println("done");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}