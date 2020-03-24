/* Lab3 assignment 4
 * Most of the code not in the main is copied from the book as said in the assignment
 * the program is a  red black search tree
 * input is any text 
 * better without any non char makes results more accurate
 */

package Lab3;

import java.util.*;

public class redBlackTrees<Key extends Comparable<Key>, Value> {

	//beginning code from the book
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {
		Key key; // key
		Value val; // associated data
		Node left, right; // subtrees
		int N; // # nodes in this subtree
		boolean color; // color of link from
		// parent to this node

		Node(Key key, Value val, int N, boolean color) {
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}

	Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
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
	public boolean isEmpty() {
        return root == null;
    }

	public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
		root = put(root, key, val);
		root.color = BLACK;
	}

	private Node put(Node h, Key key, Value val) {
		if (h == null) // Do standard insert, with red link to parent.
			return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if (cmp < 0)
			h.left = put(h.left, key, val);
		else if (cmp > 0)
			h.right = put(h.right, key, val);
		else
			h.val = val;
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	 public Iterable<Key> keys() {
	        if (isEmpty()) return new LinkedList<Key>();
	        return keys(min(), max());
	    }

	    
	    public Iterable<Key> keys(Key lo, Key hi) {
	        if (lo == null) throw new IllegalArgumentException();
	        if (hi == null) throw new IllegalArgumentException();

	        Queue<Key> queue = new LinkedList<Key>();
	        keys(root, queue, lo, hi);
	        return queue;
	    } 

	   
	    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
	        if (x == null) return; 
	        int cmplo = lo.compareTo(x.key); 
	        int cmphi = hi.compareTo(x.key); 
	        if (cmplo < 0) keys(x.left, queue, lo, hi); 
	        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key); 
	        if (cmphi > 0) keys(x.right, queue, lo, hi); 
	    }
	    public Key min() {
	        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
	        return min(root).key;
	    } 

	    // the smallest key in subtree rooted at x; null if no such key
	    private Node min(Node x) { 
	        // assert x != null;
	        if (x.left == null) return x; 
	        else                return min(x.left); 
	    } 
	    public Key max() {
	        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
	        return max(root).key;
	    } 

	    // the largest key in the subtree rooted at x; null if no such key
	    private Node max(Node x) { 
	        // assert x != null;
	        if (x.right == null) return x; 
	        else                 return max(x.right); 
	    } 
	    public Value get(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to get() is null");
	        return get(root, key);
	    }

	    // value associated with the given key in subtree rooted at x; null if no such key
	    private Value get(Node x, Key key) {
	        while (x != null) {
	            int cmp = key.compareTo(x.key);
	            if      (cmp < 0) x = x.left;
	            else if (cmp > 0) x = x.right;
	            else              return x.val;
	        }
	        return null;
	    }
	    public boolean contains(Key key) {
	        return get(key) != null;
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

		Scanner input = new Scanner(System.in); 
		int cutoff = 1; // key-length cutoff
		
		
		redBlackTrees<String, Integer> st = new redBlackTrees<String, Integer>();
		 String greatest = "";
			st.put(greatest, 0);
		 while (input.hasNext()) { // Build symbol table and count frequencies.
				String wordinput = input.next();
				if (wordinput.length() < cutoff) 
					continue; // too short keys ignored
				if (!st.contains(wordinput))
					st.put(wordinput, 1);
				else
					st.put(wordinput, st.get(wordinput) + 1);
			}
	        
	        Stopwatch time = new Stopwatch();
	        for (String word : st.keys()) {
				if (st.get(word) > st.get(greatest)) {
					greatest = word;
				}
			}
			System.out.println(greatest + " " + st.get(greatest));
	        double timepassed = time.elapsedTime();
			System.out.println(timepassed);
	}
}
