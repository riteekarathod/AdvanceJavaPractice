package mutlithreading;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ExForkJoinPool {

	public static void main(String[] a)
	{
		int[] input= {5,8,6,3,2,4,1,0,9,7};
		
		System.out.println("unSorted Array : "+Arrays.toString(input));
		
		MergeSortDivideTask task= new MergeSortDivideTask(input);
		ForkJoinPool fp= new ForkJoinPool();
		fp.invoke(task);
		
		System.out.println("Sorted Array : " + Arrays.toString(task.join()));
		
	}
}
