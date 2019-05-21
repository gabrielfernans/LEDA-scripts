package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(BTNode<T> node) {
		int result = -1;
		
		if (!node.isEmpty()) {
			int leftHeight = height((BSTNode<T>) node.getLeft());
			int rightHeight = height((BSTNode<T>) node.getRight());
			
			if (leftHeight > rightHeight) {
				result = leftHeight + 1;
			}
			else {
				result = rightHeight + 1;
			}
		}
		return result;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.isEmpty() || element == null) {
			return new BSTNode<T>();
		}
		
		if (element.compareTo(node.getData()) == 0) {
			return node;
		}
		
		else if (node.getData().compareTo(element) > 0) {
			return search(element,(BSTNode<T>) node.getLeft());
		}
		
		else {
			return search(element,(BSTNode<T>) node.getRight());
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
		
		else {
			if (node.getData().compareTo(element) > 0) {
				insert(element, (BSTNode<T>) node.getLeft());
			}
			
			else {
				insert(element, (BSTNode<T>) node.getRight());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		
		if (node.getRight().isEmpty()) {
			return node;
		}
		return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}
		
		if (node.getLeft().isEmpty()) {
			return node;
		}
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		return sucessor(node);
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return null;
		}
		
		BSTNode<T> minRight = minimum((BSTNode<T>) node.getRight());
		
		if (minRight != null) {
			return minRight;
		}
		
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		while(parent != null && parent.getData().compareTo(node.getData()) < 0) {
			parent = (BSTNode<T>) parent.getParent();
		}
		return parent;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		return predecessor(node);
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return null;
		}
		
		BSTNode<T> maxLeft = maximum((BSTNode<T>) node.getLeft());
		
		if (maxLeft != null) {
			return maxLeft;
		}
		
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		while(parent != null && parent.getData().compareTo(node.getData()) > 0) {
			parent = (BSTNode<T>) parent.getParent();
		}
		return parent;
	}

	@Override
	public void remove(T element) {
		if (element!=null) {
			BSTNode<T> node = search(element);
			
			if (!node.isEmpty()) {
				remove(node);
			}
		}
	}

	private void remove(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		
		boolean option = node.getLeft().isEmpty() ^ node.getRight().isEmpty();
		
		if (node.isLeaf()) {
			node.setData(null);
		}
		
		else if (option) {
			
			if (parent != null) {
				
				if (!node.getLeft().isEmpty()) {
					
					if (isLeftChild(node, parent)) {
						parent.setLeft(node.getLeft());
						node.getLeft().setParent(parent);
					}
					else {
						parent.setRight(node.getLeft());
						node.getLeft().setParent(parent);
					}
				}
				else {
					if (isLeftChild(node, parent)) {
						parent.setLeft(node.getRight());
						node.getRight().setParent(parent);
					}
					else {
						parent.setRight(node.getRight());
						node.getRight().setParent(parent);
					}
				}
			}
			else {
				if (node.getLeft() == null || node.getLeft().isEmpty()) {
					root = (BSTNode<T>) node.getRight();
				}
				else {
					root = (BSTNode<T>) node.getLeft();
				}
				root.setParent(null);
			}
		}
		else {
			BSTNode<T> nodeAux = sucessor(node);
			T aux = node.getData();
			node.setData(nodeAux.getData());
			nodeAux.setData(aux);
			remove(nodeAux);
		}
	}

	@Override
	public T[] preOrder() {
		T[] ts = (T[]) new Comparable[this.size()];
		T[] array = ts;
		preOrder(array, root, 0);
		return array;
	}

	private int preOrder(T[] array, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			array[i++] = node.getData();
			i = preOrder(array, (BSTNode<T>) node.getLeft(), i);
			i = preOrder(array, (BSTNode<T>) node.getRight(), i);
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(array, root, 0);
		return array;
	}

	private int order(T[] array, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			i = order(array, (BSTNode<T>) node.getLeft(), i);
			array[i++] = node.getData();
			i = order(array, (BSTNode<T>) node.getRight(), i);
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(array, root, 0);
		return array;
	}

	private int postOrder(T[] array, BSTNode<T> node, int i) {
		if (!node.isEmpty()) {
			i = postOrder(array, (BSTNode<T>) node.getLeft(), i);
			i = postOrder(array, (BSTNode<T>) node.getRight(), i);
			array[i++] = node.getData();
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
	
	private boolean isLeftChild(BSTNode<T> node, BSTNode<T> parent) {
		return parent.getLeft().equals(node);
	}
}
