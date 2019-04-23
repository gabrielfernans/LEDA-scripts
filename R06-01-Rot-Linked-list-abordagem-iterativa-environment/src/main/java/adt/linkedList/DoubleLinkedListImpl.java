package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super.head = new DoubleLinkedListNode<T>();
	}
	
	@Override
	public void insertFirst(T element) {
		SingleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) 
									   super.head, new DoubleLinkedListNode<>());
		super.head = node;
	}

	@Override
	public void removeFirst() {
		super.head = super.head.getNext();
	}

	@Override
	public void removeLast() {
		if (super.size() == 1) {
			super.setHead(new DoubleLinkedListNode<>());
			this.setLast(new DoubleLinkedListNode<>());
		}
		
		else if (super.size() == 2) {
			super.head.setNext(new DoubleLinkedListNode<>());
			this.setLast((DoubleLinkedListNode<T>) super.head);
		}
		
		else {
			this.setLast(this.last.getPrevious());
			this.last.setNext(new DoubleLinkedListNode<>());
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
	
	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			
			if (head.getData().equals(element)) {
				
				if (head.equals(last)) {
					head = new DoubleLinkedListNode<T>();
					last = (DoubleLinkedListNode<T>) head;
				}
				
				else {
					((DoubleLinkedListNode<T>) head.getNext()).setPrevious(((DoubleLinkedListNode<T>) head).getPrevious());
					head = ((DoubleLinkedListNode<T>) head.getNext());
				}
			} 
			
			else {
				DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) head;
				
				while (!node.isNIL() && !node.getData().equals(element)) {
					node = (DoubleLinkedListNode<T>) node.getNext();
				}
				
				if (node.equals(last)) {
					last.getPrevious().setNext(new DoubleLinkedListNode<>());
					last = last.getPrevious();	
				} 
				
				else {
					((DoubleLinkedListNode<T>) node.getNext()).setPrevious(node.getPrevious());
					node.getPrevious().setNext(node.getNext());
				}
			}
		}
	}
	
	@Override
	public void insert(T element) {
		if (super.isEmpty()) {
			super.head = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
			last = (DoubleLinkedListNode<T>) super.head;
		} 
		
		else if (super.size() == 1) {
			last = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), (DoubleLinkedListNode<T>) super.head);
			super.head.setNext(last);
		}
		
		else {
			SingleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), last);
			last.setNext(node);
			setLast((DoubleLinkedListNode<T>) last.getNext());
		}
	}
}
