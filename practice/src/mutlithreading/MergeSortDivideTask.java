package mutlithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MergeSortDivideTask extends RecursiveTask<int[]>{

	int[] arr;

	MergeSortDivideTask(int[] arr) {
		this.arr = arr;
	}
	
	@Override
	protected int[] compute() {
		
		// if Array length is greater than 1, we further divide it
		if (arr.length > 1) {
		 List<int[]> dividedArray= divideArray();	
		 MergeSortDivideTask task1=  new MergeSortDivideTask(dividedArray.get(0));
		 MergeSortDivideTask task2=  new MergeSortDivideTask(dividedArray.get(1));
		 
		 invokeAll(task1, task2);
		 
		 // wait from results from both the task 
		 int[] array1 = task1.join();
	     int[] array2 = task2.join();
	     
	   //Initialize a merged array
	      int[] mergedArray = 
	              new int[array1.length + array2.length];
	      
	      merge(array1, array2, mergedArray);
	      return mergedArray;
		
		}
		
		return arr;
	}
	
	protected List<int[]> divideArray()
	{
		int mid = arr.length / 2;
		int[] leftArray = new int[mid];
		int[] rightArray = new int[arr.length - mid];

		for (int i = 0; i < mid; i++) {
			leftArray[i] = arr[i];
		}
		for (int i = mid; i < arr.length; i++) {
			rightArray[i-mid] = arr[i];                
		}
		
		return Arrays.asList(leftArray,rightArray);
		
	}

  public int[] merge(int[] leftArray, int[] rightArray,int[] originalArray) {
		
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < leftArray.length && j < rightArray.length) {
			if (leftArray[i] < rightArray[j]) {
				originalArray[k] = leftArray[i];
				i++;
			} else {
				originalArray[k] = rightArray[j];
				j++;
			}
			k++;
		}

		while (i < leftArray.length) {
			originalArray[k] = leftArray[i];
			i++;
			k++;
		}

		while (j < rightArray.length) {
			originalArray[k] = rightArray[j];
			j++;
			k++;
		}
		
		//this.arr=originalArray;
		
		return originalArray;
	}
}
