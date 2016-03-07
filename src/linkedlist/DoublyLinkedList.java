package linkedlist;

public class DoublyLinkedList<E> {
	int size;
	DNode<E> head;
	DNode<E> tail;

	DoublyLinkedList() {
		head = new DNode();
		tail = new DNode();
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		head.prev = null;
	}

	public void insetFirst(E element) {
		DNode<E> temp = new DNode();
		temp.item = element;
		head.next.prev = temp;
		temp.prev = head;
		temp.next = head.next;
		head.next = temp;
		size++;
	}

	private static class DNode<E> {
		E item;
		DNode prev;
		DNode next;

	}

	public void display() {
		DNode temp = head;
		for (int i = 0; i < size; i++) {
			temp = temp.next;
			System.out.println(temp.item);

		}
	}

	public void insertEnd(E element) {
		DNode<E> temp = new DNode();
		temp.item = element;
		tail.prev.next = temp;
		temp.prev = tail.prev;
		temp.next = tail;
		tail.prev = temp;
		size++;
	}

	public E deleteFirst() {
		DNode<E> elem = head.next;
		E retEl = elem.item;
		elem.next.prev = head;
		head.next = elem.next;
		size--;
		return retEl;

	}

	public E deleteLast() {
		DNode<E> elem = tail.prev;
		E retEl = elem.item;
		tail.prev = elem.prev;
		elem.prev.next = elem.next;
		size--;
		return retEl;

	}

	public static void main(String[] args) {
		DoublyLinkedList<String> dll = new DoublyLinkedList();
		dll.insetFirst("AAAAA");
		dll.insetFirst("BBBBB");
		dll.insertEnd("Vijay");
		dll.insertEnd("Bhatru");
		dll.display();
		System.out.println("Deleting first element");
		System.out.println(dll.deleteFirst());
		System.out.println(dll.deleteLast());
		System.out.println("Display +++++++++++++");
		dll.display();
	}

}
