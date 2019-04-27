package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}
	
	public RecursiveDoubleLinkedListImpl(T data, 
		   RecursiveSingleLinkedListImpl<T> next,
		   RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> node = new 
		RecursiveDoubleLinkedListImpl<>(super.getData(), super.getNext(), this);
		
		super.setData(element);
		super.setNext(node);
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (getPrevious().isEmpty()) {
				setData(getNext().getData());
				setNext(getNext().getNext());
				
				if (getNext() != null) {
					((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
				}
			} 
			else {
				((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (getNext().isEmpty()) {
				setData(null);
				setNext(null);
				
				if (getPrevious().isEmpty())
					setPrevious(null);
				} 
			else {
				((RecursiveDoubleLinkedListImpl<T>) getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if (super.size() == 0) {
			super.setData(element);
			super.setNext(new RecursiveDoubleLinkedListImpl<>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<>());	
		}
		
		else if (this.getNext().getData() == null) {
			this.setNext(new RecursiveDoubleLinkedListImpl<>(element, new RecursiveDoubleLinkedListImpl<>(), this));
		}
		
		else {
			this.getNext().insert(element);
		}
	}
	
	@Override
	public void remove(T element) {
		if (super.getData().equals(element)) {
			((RecursiveDoubleLinkedListImpl<T>) super.getNext()).setPrevious(this.getPrevious());
			this.getPrevious().setNext(this.getNext());
		}
		else {
			this.getNext().remove(element);
		}
	}
}
