package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull())
			throw new QueueOverflowException();
		
		else
			list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty())
			throw new QueueUnderflowException();
		
		@SuppressWarnings("Unchecked")
		T node = (T) ((DoubleLinkedListImpl<T>) list).getHead();
		list.removeFirst();
		return node;
	}

	@Override
	public T head() {
		@SuppressWarnings("Unchecked")
		T node = (T) ((DoubleLinkedListImpl<T>) list).getHead();
		return node;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return list.size() == this.size;
	}

}
