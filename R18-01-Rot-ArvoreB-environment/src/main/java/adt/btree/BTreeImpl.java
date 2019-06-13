package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int result = -1;
		return result;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public int size() {
		int result = -1;
		return result;
	}

	@Override
	public BNodePosition<T> search(T element) {
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void insert(T element) {

	}

	private void split(BNode<T> node) {
		int mediana = node.size() / 2;
		
		
	}

	private void promote(BNode<T> node) {
		
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
