package linkedlist;

public class SinlgyLinkedList<E> {
	Node<E> head;
	int size;

	SinlgyLinkedList() {
		head = new Node(null, null);
	}

	private static class Node<E> {
		private E element;
		private Node next;

		Node(E it, Node n) {
			this.element = it;
			this.next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> n) {
			next = n;
		}

	}

	public int getSize() {
		return size;
	}

	public void display() {
		Node<E> temp = head.next;
		for (int i = 0; i < size; i++) {
			System.out.println(temp.element);
			temp = temp.next;
		}
	}

	public void insertFirst(E element) {
		Node<E> newNode = new Node(element, head.next);
		head.next = newNode;
		size++;
	}

	public E deleteLast() {
		Node<E> temp = head.next;
		Node<E> prev = head;
		while ((temp!=null) && temp.next != null) {
			prev = temp;
			temp = temp.next;
		}
		prev.next = null;
		if (temp != null) {
			return temp.element;
		} else {
			return null;
		}

	}

	public static void main(String[] args) {
		SinlgyLinkedList<String> sll = new SinlgyLinkedList();
		sll.insertFirst("Vidya");
		sll.insertFirst("Nay segani");
		sll.insertFirst("Vijay");
		sll.insertFirst("Zoi Ram");
		System.out.println(sll.getSize());
		sll.display();
		System.out.println(sll.deleteLast());
		System.out.println(sll.deleteLast());
		System.out.println(sll.deleteLast());
		System.out.println(sll.deleteLast());
		System.out.println(sll.deleteLast());
	}

}
