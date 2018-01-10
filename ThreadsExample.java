package learning;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsExample implements Runnable {
    static int counter = 1; // a global counter
    static Lock lock = new ReentrantLock(true);

    public ThreadsExample() {
    }

    static  void incrementCounter() {
    	lock.lock();
    	try{
         System.out.println(Thread.currentThread().getName() + ": " + counter);
         counter++;
         }
    	finally{
        	 lock.unlock();
         }
    }

    @Override
    public void run() {
         while(counter<10){
              incrementCounter();
         }
    }

    public static void main(String[] args) {
         ThreadsExample te = new ThreadsExample();
         Thread thread1 = new Thread(te);
         Thread thread2 = new Thread(te);
         thread1.setName("first");
         thread2.setName("second");
         thread1.start();
         thread2.start();          
    }
}
