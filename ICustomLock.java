package learning;

public interface ICustomLock {
	void lock();
	void unlock();
	boolean tryLock();

}
