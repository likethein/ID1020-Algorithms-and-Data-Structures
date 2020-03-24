/* Algorithms and Data Structures ID1020
 * Constantin Wiederin
 * Lab 1
 * Exercise 4 
 * Circular linked list which allows the user to insert and remove elements to/from the front and back end of the queue
 * Prints the content of the list after each insertion/deletion of an element
 * 6/9/2019
 */
import java.util.*;
public class circularlist_lab1 {
	public class Node {
		int data;
		Node next;
		Node prev;
		public Node(int data) {
			this.data=data;
		}
	}
	public static Node head = null;
	public static Node tail = null;
	
	public void addTailNode(int data) {
		Node newNode =  new Node(data);
		if (head == null) { // case if there is no node
			head = newNode;
			head.next = head;
			head.prev = head; // making the new node the head and tail at the same time
			tail = head;
		}
		
		newNode.next = tail.next; //
		newNode.prev = tail; // the new nodes previous is the tail
		tail.next = newNode; // making the old tails next the new node
		newNode.next.prev = newNode; // new (nodes next) previous the new node
		tail = newNode; // maiking the tail the new node
	}
	
	public void addFrontNode(int data) {
		Node newNode =  new Node(data); // same as the tail add
		if (head == null) {
			head = newNode;
			head.next = head;
			head.prev = head;
			tail = head;
		}
		
		newNode.next = tail.next;
		newNode.prev = tail;
		tail.next = newNode;
		newNode.next.prev = newNode;
		head = newNode;
	}
	public void removeFrontNode() {	
		head = head.next; // making the new head the one next to the head
		tail.next = head; // connecting the tail to the new head
		head.prev = tail;
	}
	public void removeBehindNode() {	
		tail = tail.prev; // removing the tail 
		tail.next = head; // connecting the new tail with the head again
		head.prev = tail;
	}
	public static void printlist() {
		System.out.print("[");
		Node printnode = head;
		while(printnode != null) {
			if (printnode.next == head){
				System.out.println(printnode.data + "]");
				break;
			}
			System.out.print(printnode.data + ",");
			printnode = printnode.next;
		}
	}
	/*
	private class ListIterator implements Iterator<Item> {
		private Node current = head;

		public boolean hasNext() {
			return current != null;
		}
	}
	*/
public static void main(String[] args) {
	
	circularlist_lab1 test = new circularlist_lab1();
	
	test.addFrontNode(4);
	test.addFrontNode(5);
	test.addFrontNode(6);
	test.addFrontNode(8);
	test.removeFrontNode();
	test.printlist();
	
	
}
}
