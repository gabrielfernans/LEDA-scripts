package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) {
			return;
		}
		
		int k = biggestElement(array, leftIndex, rightIndex);
		
		Integer[] c = new Integer[k + 1];
		
		fillArray(c);
		
		for (int i = 0; i < rightIndex + 1; i++) {
			c[array[i]]++;
		}
		
		for (int i = 1; i < c.length; i++) {
			c[i] = c[i] + c[i - 1];
		}
		
		Integer[] b = new Integer[array.length];
		
		for (int i = rightIndex; i >= 0; i --) {
			b[c[array[i]] - 1]  = array[i];
			c[array[i]]--;
		}
		
		copy(array, b);
	}
	
	/**
	 * It fills in all spaces of the vector with 0
	 * 
	 * @param array Vector to be filled
	 */
	private void fillArray(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}
	
	/**
	 * It returns the biggest element of the vector
	 * 
	 * @param array Array to find the biggest element
	 * @param leftIndex Inferior limit
	 * @param rightIndex Upper limit
	 * @return The biggest element of the vector
	 */
	private int biggestElement(Integer[] array, int leftIndex, int rightIndex) {
		int biggest = array[leftIndex];
		
		for (int i = leftIndex; i < rightIndex + 1; i++) {
			if (array[i] >= biggest) {
				biggest = array[i];
			}
		}
		return biggest;
		
	}
	 
	/**
	 * It creates a copy of the array given in the first parameter of the method
	 * 
	 * @param array Array to be copied
	 * @param secondArray Auxiliary array 
	 */
	private void copy(Integer[] array, Integer[] secondArray) {
		for (int i = 0; i < array.length; i++) {
			array[i] = secondArray[i];
		}
	}
}
