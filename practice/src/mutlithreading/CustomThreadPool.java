package mutlithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
	static BlockingQueue queue;
	static Integer CAPACITY;

	CustomThreadPool() {
	}

	public void fixedCustomThreadPool(int capacity) {
		queue = new LinkedBlockingQueue<>();
		
		System.out.println("Worker thread created");
		for (int i = 0; i < capacity; i++) {
			Thread t = new Thread(new Worker(queue));
			t.start();
		}
	
	}
	public void execute(Runnable task) {

		try {
			System.out.println("task added to queue");
			queue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

}

class  Worker implements Runnable {
	public static boolean shutdownFlag = false;
	BlockingQueue queue=null;
	Runnable task;

	public Worker(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		//System.out.println("Worker thread started 1");
		while (!shutdownFlag) {
			//System.out.println("Worker thread started 2");
			if (!queue.isEmpty()) {
				Runnable task;
				try {
					task = (Runnable) queue.take();
					System.out.println("Worker thread " + Thread.currentThread().getName());
					task.run();
					System.out.println("Worker thread end");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

}