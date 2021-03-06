package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) 
			return;
		
		for(int i = leftIndex; i <= rightIndex; i++) {
			int indexOfMinimum = i;
			
			for(int j = i; j <= rightIndex; j++) {
				if(array[j].compareTo(array[indexOfMinimum]) < 0) {
					indexOfMinimum = j;
				}
			}
			Util.swap(array, i, indexOfMinimum);
		}
	}
}
