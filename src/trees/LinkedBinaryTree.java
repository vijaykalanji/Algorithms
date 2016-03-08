package trees;

import java.util.ArrayList;

public class LinkedBinaryTree<E> {

	protected static class Node<E> {
		private E element; // an element stored at this node
		private Node<E> parent; // a reference to the parent node (if any)
		private Node<E> left; // a reference to the left child (if any)
		private Node<E> right; // a reference to the right child (if any)

		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}

		// accessor methods
		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public Node<E> getRight() {
			return right;
		}

		// update methods
		public void setElement(E e) {
			element = e;
		}

		public void setParent(Node<E> parentNode) {
			parent = parentNode;
		}

		public void setLeft(Node<E> leftChild) {
			left = leftChild;
		}

		public void setRight(Node<E> rightChild) {
			right = rightChild;
		}
	} // ----------- end of nested Node class -----------

	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<E>(e, parent, left, right);
	}

	// LinkedBinaryTree instance variables
	/** The root of the binary tree */
	protected Node<E> root = null; // root of the tree

	/** The number of nodes in the binary tree */
	private int size = 0; // number of nodes in the tree

	// constructor
	/** Construts an empty binary tree. */
	public LinkedBinaryTree() {
	} // constructs an empty binary tree

	protected Node<E> validate(Node<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	public int size() {
		return size;
	}

	public Node<E> root() {
		return root;
	}

	public Node<E> parent(Node<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	public Node<E> left(Node<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	public Node<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty())
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Node<E> addLeft(Node<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	public Node<E> addRight(Node<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}

	public E set(Node<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	public E remove(Node<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if (child != null)
			child.setParent(node.getParent()); // child's grandparent becomes
												// its parent
		if (node == root)
			root = child; // child becomes root
		else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null); // help garbage collection
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node); // our convention for defunct node
		return temp;
	}

	public int numChildren(Node<E> p) {
		int count = 0;
		if (left(p) != null)
			count++;
		if (right(p) != null)
			count++;
		return count;
	}

	public Node<E> right(Node<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	public Iterable<Node<E>> positions() {
		return postorder();
	}

	private Iterable<Node<E>> inorder() {
		ArrayList<Node<E>> listOfNodes = new ArrayList<Node<E>>();
		inorderSubtree(root(), listOfNodes);
		return listOfNodes;

	}

	private void inorderSubtree(Node<E> node, ArrayList<Node<E>> listOfNodes) {
		if (node.left != null) {
			inorderSubtree(node.left, listOfNodes);
		}
		listOfNodes.add(node);
		if (node.right != null) {
			inorderSubtree(node.right, listOfNodes);
		}

	}

	private Iterable<Node<E>> preorder() {
		ArrayList<Node<E>> listOfNodes = new ArrayList<Node<E>>();
		inorderSubtree(root(), listOfNodes);
		return listOfNodes;

	}

	private void preorderSubtree(Node<E> node, ArrayList<Node<E>> listOfNodes) {
		listOfNodes.add(node);
		if (node.left != null) {
			inorderSubtree(node.left, listOfNodes);
		}
		if (node.right != null) {
			inorderSubtree(node.right, listOfNodes);
		}

	}

	private Iterable<Node<E>> postorder() {
		ArrayList<Node<E>> listOfNodes = new ArrayList<Node<E>>();
		postorderSubtree(root(), listOfNodes);
		return listOfNodes;

	}

	private void postorderSubtree(Node<E> node, ArrayList<Node<E>> listOfNodes) {
		
		if (node.left != null) {
			postorderSubtree(node.left, listOfNodes);
		}
		if (node.right != null) {
			postorderSubtree(node.right, listOfNodes);
		}
		listOfNodes.add(node);

	}

	public static void main(String[] args) {
		LinkedBinaryTree<String> lbt = new LinkedBinaryTree<String>();
		Node<String> root = lbt.addRoot("Root");
		Node<String> leftCh = lbt.addLeft(root, "left");
		Node<String> rightCh = lbt.addRight(root, "right");
		lbt.addLeft(leftCh, "Left Left ");
		ArrayList<Node<String>> arList = (ArrayList<Node<String>>) lbt.positions();
		for (Node<String> node : arList) {
			System.out.println(node.element);
		}
	}

}
