/* Algorithms and Data Structures ID1020
 * Constantin Wiederin
 * Lab 1
 * Exercise 5 
 * Allows the user to remove the kth element from the queue
 * Prints the content of the list after each insertion/deletion of an element
 * 6/9/2019
 */
import java.util.*;
public class kthQueue {
	static Node head; 
	static Node last;
	
	public static class Node{
		
		public Node(int data) {
			this.data = data;
		}
		int data; 
		Node next;
		Node prev;
	}
	public void addend(int data){
		Node newNode =  new Node(data);
		if(head == null) {
			head = newNode;//case if there is no node in the list
			last = newNode;// making the new node the head and tail 
			print();
			return;
		}
		last.next = newNode; // making the new node the lasts next
		newNode.prev = last;  
		last = newNode;	 // making the new node the last
		print();
	}
	
	public void removeKthNode(int k) {
		Node nodeRemove = last;
		for(int i=0; i<k; i++) {
			nodeRemove = nodeRemove.prev; //going throught the list till the kth one
		}
		if(nodeRemove == head) {
			head = nodeRemove.next; // the case where the kth is the heas the head just gets removed
			print();
		}
		else if(nodeRemove == last) {
			last = nodeRemove.prev; // removing when the kth is the last
			last.next = null;
			print();
		}
		else {
		nodeRemove.next.prev = nodeRemove.prev; //making the connections between the nodes
		nodeRemove.prev.next = nodeRemove.next;
		print();
		}
	}
	
	public static void print() {
		System.out.print("[");
		Node printnode = head;
		while(printnode != null) {
			if (printnode.next == null){
				System.out.println(printnode.data + "]");
				break;
			}
			System.out.print(printnode.data + ",");
			printnode = printnode.next;
		}
	}

	
	public static void main(String[] args) {
		
		kthQueue test = new kthQueue();
		
		test.addend(4);
		test.addend(3);
		test.addend(1);
		test.addend(5);
		test.removeKthNode(0);
	}
	
}

