package adt.bst;

import java.util.ArrayList;
import java.util.List;

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
		return height(this.root, -1);
	}

	protected int height(BSTNode<T> node, int currentHeight) {
		if (!node.isEmpty()) {
			int left = height((BSTNode<T>) node.getLeft(), currentHeight + 1);
			int right = height((BSTNode<T>) node.getRight(), currentHeight + 1);

			currentHeight = Math.max(left, right);
		}
		return currentHeight;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BSTNode<T> search(T element) {
		if (!isEmpty()) {
			return searchRec(this.root, element);
		} 
		
		else {
			return new BSTNode.Builder().build();
		}	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private BSTNode<T> searchRec(BSTNode<T> node, T element) {
		BSTNode<T> result;

		if (node.isEmpty()) {
			result = new BSTNode.Builder().build();
		}

		else if (element.compareTo(node.getData()) == 0) {
			result = node;
		}
			
		else if (element.compareTo(node.getData()) > 0) {
			result = searchRec((BSTNode<T>) node.getRight(), element);
		}
			
		else {
			result = searchRec((BSTNode<T>) node.getLeft(), element);
		}
		
		return result;
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} 
		
		else {
			if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			}	

			else if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}	
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) {
			return null;
		}
		
		else {
			return maximum(this.root);
		}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {
			return maximum((BSTNode<T>) node.getRight());
		} 
		
		else {
			return node;
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) {
			return null;
		}
			
		else {
			return minimum(this.root);
		}	
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			return minimum((BSTNode<T>) node.getLeft());
		} 
		
		else {
			return node;
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		
		if (!node.isEmpty()) {
			
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			}
				
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) < 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			}
				
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) > 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
			} 
			
			else if (hasOneChild(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} 
						
						else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} 
					
					else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} 
						
						else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} 
				
				else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} 
					
					else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
			} 
			
			else {
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}

	protected boolean hasOneChild(BSTNode<T> node) {
		return ((node.getRight().isEmpty() && !node.getLeft().isEmpty() || node.getLeft().isEmpty() && !node.getRight().isEmpty()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			preOrderRec(this.root, aux);
			aux.toArray(arrayResult);
		}
		return arrayResult;
	}

	private void preOrderRec(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrderRec((BSTNode<T>) node.getLeft(), array);
			preOrderRec((BSTNode<T>) node.getRight(), array);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			OrderRec(this.root, aux);
			aux.toArray(arrayResult);
		}
		return arrayResult;
	}

	private void OrderRec(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			OrderRec((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			OrderRec((BSTNode<T>) node.getRight(), array);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] result = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			postOrderRec(this.root, aux);
			aux.toArray(result);
		}
		return result;
	}

	private void postOrderRec(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			postOrderRec((BSTNode<T>) node.getLeft(), array);
			postOrderRec((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	@Override
	public int size() {
		return size(this.root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
