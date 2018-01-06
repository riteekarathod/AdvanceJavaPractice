package mutlithreading;

public class UseCustomeThreadPool {
	public static void main(String[] args) {

			CustomThreadPool tp= new CustomThreadPool();
			tp.fixedCustomThreadPool(5);
			for(int i=0;i<30;i++)
			tp.execute(() -> System.out.println("hello world"));
	}
}
