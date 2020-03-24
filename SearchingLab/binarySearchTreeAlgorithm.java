/* Lab3 assignment 2
 * Most of the code not in the main is copied from the book as said in the assignment
 * the program is a Binary search tree
 * also contaions assignment 3 which sorts all the words inputted
 * into the order of their recurrence
 * input is any text 
 * better without any non char makes results more accurate
 */
package Lab3;

import java.util.*;

import java.util.Scanner;
import java.util.*;

public class binarySearchTreeAlgorithm<Key extends Comparable<Key>, Value> {
	private Node root; // root of BST
//copied from the book
	private class Node {
		private Key key; // key
		private Value val; // associated value
		private Node left, right; // links to subtrees
		private int N; // # nodes in subtree rooted here

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) { // Return value associated with key in the subtree rooted at x;
											// return null if key not present in subtree rooted at x.
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		// Change key’s value to val if key in subtree rooted at x.
		// Otherwise, add new node to subtree associating key with val.
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	public Key select(int k) {
		return select(root, k).key;
	}

	private Node select(Node x, int k) { // Return Node containing key of rank k.
		if (x == null)
			return null;
		int t = size(x.left);
		if (t > k)
			return select(x.left, k);
		else if (t < k)
			return select(x.right, k - t - 1);
		else
			return x;
	}

	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node x) { // Return number of keys less than x.key in the subtree rooted at x.
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new LinkedList<>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			queue.add(x.key);
		if (cmphi > 0)
			keys(x.right, queue, lo, hi);
	}

	public Key min() {
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		return min(x.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		return max(x.right);
	}
	
	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() cannot be null");
		}
		return get(key) != null;
	}
	public boolean contains_word(String[] words, String word) {
		for (int i = 0; i < words.length; i++) {
			if (word.equals(words[i])) {
				return true;
			}
		}
		return false;
	}
	

	public static void main(String[] args) {
		class Stopwatch {
			private final long start;

			public Stopwatch() {
				start = System.currentTimeMillis();
			}

			public double elapsedTime() {
				long now = System.currentTimeMillis();
				return (now - start) / 1000.0;
			}
		}

		Scanner sc = new Scanner(System.in);
		
		int cutOff = 1; // key-length cutoff
		
		binarySearchTreeAlgorithm<String, Integer> st = new binarySearchTreeAlgorithm<String, Integer>();
		
		while (sc.hasNext()) { // Build symbol table and count frequencies.
			String enter = sc.next(); //enter is the next input
			if (enter.length() < cutOff) //if enters length is less than the Cutoff length
				continue; // ignore the word
			if (!st.contains(enter)) // if the array does not already contain the word
				st.put(enter, 1); // put the word in
			else
				st.put(enter, st.get(enter) + 1); // put add 1 to the amount of times appeared
		}
		String[] numberofTimes = new String[97]; // the number of times has length of 97 which is the length of the hash table and prime
		
		String greatest = "";  //greatest is nothing
		
		st.put(greatest, 0); //greatest is put first position
		
		Stopwatch timer = new Stopwatch(); // the stopwatch initalized. Method gotten from the book
		
		String[] max = new String[97]; // the array max is 97 also because of the reasons number of times is that length
		
		int index = 0; //index initalized
		
		for (String word : st.keys()) { //foreach loop
			if (st.get(word) > st.get(greatest)) { //if the word is greater than the value stored at greatest
				greatest = word; //greatest becomes the new word
			}
		}
		
		System.out.println(greatest + " " + st.get(greatest)); // printing the greatest value
		
		double time = timer.elapsedTime();
		
		System.out.println(time);
		
		// assignment 3
		numberofTimes[0] = greatest; // put that key at index 0 in the list
		
		max[index] = greatest; //the greatest value is placed at index in the max array
		
		index++; // index is increased to the next one so the next greatest can be placed in
		
		greatest = ""; // greatest is reset to nothing
		
		for (int i = 1; i < numberofTimes.length; i++) { // now finding the 2nd most frequent word and stored at i in array
			for (String word : st.keys()) { //foreach loop again
				if (st.get(word) >= st.get(greatest) && st.get(word) <= st.get(numberofTimes[i - 1])) { // if the frequency is more than greatest and smaller than the value at max -1																					
					if (!st.contains_word(max, word)) { // if the word is not in max already
						greatest = word; //make the greatest = the word
					}
				}
			}
			
			numberofTimes[i] = greatest; // update the list with the key
			
			max[index] = greatest; //the greatest gets put into the max array
			
			index++;
			
			greatest = ""; // greatest value reset
		}
		int total = 0;
		
		for (int i = (1 - 1); i < 97; i++) { // prints all greatest values right now can be changed to a range
			
			System.out.println((i + 1) + ". " + numberofTimes[i] + "  " + st.get(numberofTimes[i]));
			
			total = total + st.get(numberofTimes[i]);  
		}
	}
}