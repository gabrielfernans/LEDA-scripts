package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex > rightIndex || array == null || array.length == 0 || rightIndex > array.length || leftIndex > array.length) {
			return;
		}
		
		int k = biggestElement(array, leftIndex, rightIndex);
		int m = smallestElement(array, leftIndex, rightIndex);
		
		Integer[] b = new Integer[k -m + 1];
		fillArray(b);
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			b[array[i] - m]++;
		}
		
		int i = leftIndex;
		int j = leftIndex;
		
		while (i < b.length) {
			while (b[i] != 0) {
				array[j] = i + m;
				j++;
				b[i]--;
			}
			i++;
		}
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
	 * It returns the smallest element of the vector
	 * 
	 * @param array Array to find the smallest element
	 * @param leftIndex Inferior limit
	 * @param rightIndex Upper limit
	 * @return The smallest element of the vector
	 */
	private int smallestElement(Integer[] array, int leftIndex, int rightIndex) {
		int smallest = leftIndex;;
		
		for (int i = leftIndex; i < rightIndex + 1; i++) {
			if (array[i] < smallest) {
				smallest = array[i];
			}
		}
		return smallest;
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
		int biggest = leftIndex;
		
		for (int i = leftIndex; i < rightIndex + 1; i++) {
			if (array[i] >= biggest) {
				biggest = array[i];
			}
		}
		return biggest;
	}

}
