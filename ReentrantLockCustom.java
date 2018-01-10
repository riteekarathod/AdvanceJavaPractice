package learning;

public class ReentrantLockCustom implements ICustomLock{
	int lockHoldCount;
	long IdOfCurrentlyHoldingThread;
	public ReentrantLockCustom() {

lockHoldCount = 0;
	}
	@Override
	public synchronized void lock() {
		if(lockHoldCount == 0 ){
			lockHoldCount++;
			IdOfCurrentlyHoldingThread = Thread.currentThread().getId();
			
		}else if(lockHoldCount > 0 && IdOfCurrentlyHoldingThread == Thread.currentThread().getId()){
			lockHoldCount++;
		}else{
			try {
				wait();
				lockHoldCount++;
				IdOfCurrentlyHoldingThread = Thread.currentThread().getId();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	@Override
	public synchronized void unlock() {
		if(lockHoldCount==0)
			throw new IllegalMonitorStateException();
		lockHoldCount--;
		
		if(lockHoldCount==0)
			notify();
		
	}
	@Override
	public boolean tryLock() {
		if(lockHoldCount ==0){
			lock();
			return true;
			}
		else return false;
	}

	
	
	
	
	

}
