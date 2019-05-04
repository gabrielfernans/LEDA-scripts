package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull())
			throw new HashtableOverflowException();
		
		if (element != null) {
	    	for (int i = 0; i < table.length; i++) {
	    		int key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
	    		if (element instanceof Storable ) {
	   				T auxKey = (T) table[key];
	   				
	    			if (auxKey == null || auxKey.equals(deletedElement)) {
	    				super.table[key] = element;
	    				super.elements ++;	                
	   					break;
	   				}
	   				super.COLLISIONS ++;
	   			}
	   		}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			for (int i = 0; i < table.length; i++ ) {
				int key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
				
				if (table[key] instanceof Storable) {
					T auxKey = (T) table[key];
					
					if (auxKey != null) {
						if (auxKey.equals(element)) {
							super.table[key] = deletedElement;
							super.elements --;
						}
					}
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (!isEmpty() && element != null) {
			for (int i = 0; i < table.length; i++) {
				int key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
				
				if (table[key] instanceof Storable) {
					T auxKey = (T) super.table[key];
					
					if (auxKey != null) {
						if (auxKey.equals(element)) {
							return auxKey;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		if (!isEmpty() && element != null) {
			for (int i = 0; i < table.length; i++) {
				int key = ((HashFunctionQuadraticProbing<T>) getHashFunction()).hash(element, i);
				
				if (super.table[key] instanceof Storable) {
					T auxKey = (T) super.table[key];
					
					if (auxKey != null) {
						if (!auxKey.equals(deletedElement) && auxKey.equals(element)) {
							return key;
						}
					}
				}
			}
		}
		return -1;
	}
}
