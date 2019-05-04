package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull()) 
			throw new HashtableOverflowException();
		
		if (element!= null) {
			for (int i = 0; i < table.length; i++) {
				int key = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);
				
				if (element instanceof Storable) {
					T auxKey = (T) table[key];				
					if (auxKey == null || auxKey.equals(deletedElement)){
						super.table[key] = element;
						super.elements += 1;
						break;
					}
					COLLISIONS ++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element!=null) {
			for (int i = 0; i < table.length; i++) {
				int key = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element,i);
				
				if (table[key] instanceof Storable) {
					T auxKey = (T) table[key];
					
					if (auxKey != null) {
						if (auxKey.equals(element)) {
							super.table[key] = deletedElement;
							super.elements -= 1;
						}
						COLLISIONS --;
					}
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (!isEmpty() && element!=null) {
			for (int i = 0; i < table.length; i++) {
				int key = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);
				
				if (table[key] instanceof Storable) {
					T auxKey = (T) super.table[key];	
					if (auxKey != null) {
						if (auxKey.equals(element))
							return auxKey;
					}
				}
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		if (!isEmpty() && element!=null) { 
			for (int i = 0; i < super.table.length; i++) {
				int key = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);
				
				if (super.table[key] instanceof Storable ) {
					T auxKey = (T) super.table[key];
					
					if (auxKey != null ) 
						if (!auxKey.equals(deletedElement ) && auxKey.equals(element)) 
							return key;
				}
			}
		}
		return -1;
	}

}
