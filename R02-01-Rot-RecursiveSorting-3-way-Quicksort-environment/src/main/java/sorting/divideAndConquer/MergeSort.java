package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex > rightIndex || array == null || array.length == 0 || array.length < rightIndex || leftIndex > array.length) 
			return;

		if (leftIndex < rightIndex) {
			int meio = (leftIndex + rightIndex) / 2;

			// Passo recursivo
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			mergeSort(array, leftIndex, meio, rightIndex);
		}
	}

	private void mergeSort(T[] array, int inicio, int meio, int fim) {
		// Copia tudo para o array auxiliar
		T[] copia = Arrays.copyOf(array, fim + 1);

		// Define limites de meio para a comparação
		int metade1 = inicio;
		int metade2 = meio + 1;
		int i = inicio;

		// Compara as "duas" listas
		while (metade1 <= meio && metade2 <= fim) {
			if (copia[metade1].compareTo(copia[metade2]) < 0) {
				array[i] = copia[metade1];
				metade1++;
			} else {
				array[i] = copia[metade2];
				metade2++;
			}
			i++;
		}

		// Copia depois do final da ordenação;
		while (metade1 <= meio) {
			array[i] = copia[metade1];
			metade1++;
			i++;
		}

		while (metade2 <= fim) {
			array[i] = copia[metade2];
			metade2++;
			i++;
		}
	}
}
