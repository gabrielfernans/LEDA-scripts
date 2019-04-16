package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		
		array[tail] = element;
		tail = (tail + 1) % array.length;
		elements ++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if(this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T element = array[head];
		this.head = (head + 1) % array.length;
		this.elements--;
		return element;
	}

	@Override
	public T head() {
		if(this.isEmpty()) {
			return null;
		}
		return array[head];
	}

	@Override
	public boolean isEmpty() {
		return (this.elements == -1);
		
	}

	@Override
	public boolean isFull() {
		return (this.elements == this.tail);
	}

}
