package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
		
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}
	
	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		if (this.getData() == null) {
			return 0;
		}
				
		else {
			return 1 + this.getNext().size();
		}
	}

	@Override
	public T search(T element) {
		T node = null;
		
		if (this.getData() == null || this.getData().equals(element) ) {
			node = this.getData();
		}	
		else {
			node = this.getNext().search(element);
		}
		return node;
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			this.setData( element );
			this.setNext(new RecursiveSingleLinkedListImpl<>());
		} 
		else {
			this.getNext().insert( element );
		}
	}

	@Override
	public void remove(T element) {
		if (this.getData().equals( element)) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
		}
		else {
			this.getNext().remove(element );
		}
	}
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[this.size()];
		toArrayRecursive(result, this, 0);
		
		return result;
	}

	private void toArrayRecursive(T[] array, RecursiveSingleLinkedListImpl<T> node, int cont) {
		if (!node.isEmpty()) {
			array[cont] = node.getData();
			toArrayRecursive(array, node.next, ++cont);
		}	
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
