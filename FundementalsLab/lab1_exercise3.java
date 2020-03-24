
/* Algorithms and Data Structures ID1020
 * Constantin Wiederin
 * Lab 1
 * Exercise 3 
 * A FIFO Queue with a double linked list
 * Prints the content of the list after each insertion/deletion of an element
 * 6/9/2019
 */
import java.util.*;

public class lab1_exercise3<Item> {
	static Node head;
	static Node last;

	public static class Node<Item> {

		public Node(Item data) {
			this.data = data;
		}

		Item data;
		Node next;
		Node prev;
	}

	public void addend(Node insert_node) {
		if (head == null) { // if there is nothing in the list
			head = insert_node; // Making the new node both head and tail
			last = insert_node;
			print(); // printing each time
			return;
		}
		last.next = insert_node; // new node becomes the tail
		insert_node.prev = last; // creating the links to the new node
		last = insert_node; // making the new node the last node
		print();
	}

	public void removeNode() {
		head = head.next; // The head just gets moved by one
		print();
	}

	public static void print() {
		System.out.print("["); 
		Node printnode = head;
		while (printnode != null) {
			if (printnode.next == null) {
				System.out.println(printnode.data + "]");
				break;
			}
			System.out.print(printnode.data + ",");
			printnode = printnode.next;
		}
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = head;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
		}

		public Item next() {
			Item item = (Item) current.data;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		lab1_exercise3<Integer> test = new lab1_exercise3<>();
		
		/*for(int element : test) {
			System.out.print(element);
		}
		*/
		Node first = new Node(2);
		Node second = new Node(4);
		Node third = new Node(6);
		Node fourth = new Node(8);

		test.addend(first);
		test.addend(second);
		test.addend(third);
		test.addend(fourth);
		test.removeNode();

	}
}
