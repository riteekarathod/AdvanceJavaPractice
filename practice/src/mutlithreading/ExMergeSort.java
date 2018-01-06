package mutlithreading;

public class ExMergeSort {

	public static void main(String[] a) {

		int[] inputArr = { 4, 1, 7, 5, 3, 2, 6 };
		MergeSort ms = new MergeSort(inputArr);

		System.out.println("------------------\n Input \n------------------");
		ms.display();
		
		System.out.println("\n\n------------------\n After mergeSort() \n------------------");
		ms.sort();

		ms.display();
	}
}

class MergeSort {
	int[] arr;

	MergeSort(int[] arr) {
		this.arr = arr;
	}

	public void sort() {
		mergesort(this.arr);
	}

	public void display() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(" " + arr[i]);
		}
	}

	public void mergesort(int[] arr) {
		if (arr.length < 2) {
			return;
		}
		int mid = arr.length / 2;
		int[] leftArray = new int[mid];
		int[] rightArray = new int[arr.length - mid];

		for (int i = 0; i < mid; i++) {
			leftArray[i] = arr[i];
		}
		for (int i = mid; i < arr.length; i++) {
			rightArray[i-mid] = arr[i];                // tricky part
		}

		mergesort(leftArray); // left array
		mergesort(rightArray); // right array
		merge(leftArray, rightArray,arr);
		
		
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
		
		this.arr=originalArray;
		
		return originalArray;
	}
}
