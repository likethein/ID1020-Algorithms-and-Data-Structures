/* Algorithms and Data Structures ID1020
 * Constantin Wiederin
 * Lab 1
 * Exercise 6 
 * Ordered queue
 * Prints the content of the list after each insertion/deletion of an element
 * 6/9/2019
 */
import java.util.*;

public class orderedQueue {
	static Node head; 
	static Node last;
	static Node temp;

	public static class Node {

		public Node(int data) {
			this.data = data;
		}

		int data; 
		Node next;
		Node prev;
	}

	public void addsort(int data) {
		Node temp = head; 
		Node newNode = new Node(data);

		if (head == null) {
			head = newNode;//the case where there is no node yet
			last = head;
		} // three cases
		else if (head.data >= newNode.data) { // if the head is creater than the new node
			newNode.next = head;
			head.prev = newNode; // make the new node the head 
			head = newNode;
			print();
		} else if (newNode.data >= last.data) { //of the ned node data is more than the last node
			newNode.prev = last;
			last.next = newNode;
			last = newNode; //make the new node the tail
			last.next = null;
			print();

		} else {
			while (temp.next != null && newNode.data > temp.data) {
				temp = temp.next;
			}
			newNode.next = temp;
			newNode.prev = temp.prev;
			temp.prev.next = newNode; // the inbetween case where it foes till the new data is smaller than the one its at
			temp.prev = newNode;
			print();
		}
	}

	public void removeNode() {
		head = head.next; //remove from the front by making the next the head
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

	public static void main(String[] args) {

		orderedQueue test = new orderedQueue();

		test.addsort(10);
		test.addsort(5);
		test.addsort(12);
		test.addsort(6);

	}
}
