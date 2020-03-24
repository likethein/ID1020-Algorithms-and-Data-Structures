/* Lab3 assignment 2
 * Most of the code not in the main is copied from the book as said in the assignment
 * the program is a search tree
 * also contaions assignment 3 which sorts all the words inputted
 * into the order of their recurrence
 * input is any text 
 * better without any non char makes results more accurate
 */

package Lab3;

import java.util.*;

//most of non-main code copied from the book
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;

	private int N;

	public BinarySearchST(int size) { // See Algorithm 1.1 for standard array-resizing code.
			keys = (Key[]) new Comparable[size];
			vals = (Value[]) new Object[size];

		}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}

	public void put(Key key, Value val) { // Search for key. Update value if found; grow table if new.
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Key select(int k) {
		return keys[k];
	}

	public int rank(Key key) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public boolean contains(Key key) {
		if (key == null) {
			throw new IllegalArgumentException("Argument to contains() cannot be null");
		}
		return get(key) != null;
	}

	public Key min() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty symbol table");
		}

		return keys[0];
	}

	public Key max() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty symbol table");
		}

		return keys[N - 1];
	}

	public Iterable<Key> keys(Key low, Key high) {
		if (low == null || high == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}

		Queue<Key> queue = new LinkedList<>();

		for (int i = rank(low); i < rank(high); i++) {
			queue.add(keys[i]);
		}

		if (contains(high)) {
			queue.add(keys[rank(high)]);

		}

		return queue;
	}

	public Iterable<Key> keys() {
			return keys(min(), max());
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

			Scanner user = new Scanner(System.in);
			
			
			
			int cutofflength = 1; // key-length cutoff
			
			BinarySearchST<String, Integer> st;
			
			st = new BinarySearchST<String, Integer>(100000); //1000000 is the amount of words input

			while (user.hasNext()) {  //while there is more text
				String userword = user.next(); //user word is the next word
				if (userword.length() < cutofflength) // if the length is less than the cuttoff
					continue; // ignore the word
				if (!st.contains(userword)) //if the array does not contain the word 
					st.put(userword, 1); //place the word with a value of 1
				else
					st.put(userword, st.get(userword) + 1); //else add 1 to the already existing value
			}
			
			user.close(); // scanner closed

			Stopwatch time = new Stopwatch(); // time timer initialized gotten from online
			
			String[] recurrence = new String[97]; // recurrence is length 97 because it is prime and that is the length from the book
			
			String greatest = ""; // greatest is nothing
			
			st.put(greatest, 0); //value 0
			
			for (String word : st.keys()) //foreach loop
				if (st.get(word) > st.get(greatest)) //if the word is greater than greatest replace
					greatest = word;
			
			System.out.println(greatest + " " + st.get(greatest));
			
			double timepassed = time.elapsedTime();
			
			System.out.println(timepassed);
			

			recurrence[0] = greatest; 
			
			greatest = "";
			
			for (int i = 1; i < recurrence.length; i++) { 
				
				for (String word : st.keys()) {
					
					if (st.get(word) > st.get(greatest) && st.get(word) < st.get(recurrence[i - 1])) 
						
						greatest = word;
				} 
				recurrence[i] = greatest; 
				
				greatest = ""; 
			}
			for (int i = 1 - 1; i < 97; i++) 
				
				System.out.println((i + 1) + ". " + recurrence[i] + "  " + st.get(recurrence[i]));
	}
}