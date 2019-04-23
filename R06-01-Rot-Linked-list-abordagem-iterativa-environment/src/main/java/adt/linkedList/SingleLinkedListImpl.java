package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		
		SingleLinkedListNode<T> node = this.head;
		
		while (!node.isNIL()) {
			size ++;
			node = node.getNext();
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> node = this.head;
		
		while (!(node.isNIL() || node.getData().equals(element))) {
			node = node.getNext();
		}
		
		return node.getData();
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
		
		if (this.isEmpty()) {
			this.head = newNode;
		}
			
		else {
			SingleLinkedListNode<T> node = this.head;
			
			while (!node.getNext().isNIL()) {
				node = node.getNext();
				
			}
			node.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		if (!this.isEmpty())
			if (this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			}
			
			else {
				SingleLinkedListNode<T> node = head;
				
				while (!node.getNext().getData().equals(element)) {
					node = node.getNext();
				}
				node.next = node.getNext().getNext();
			}	
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		
		int i = 0;
		
		SingleLinkedListNode<T> node = this.head;
		
		while (!node.isNIL()) {
			array[i] = node.getData();
			i++;
			node = node.getNext();
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
