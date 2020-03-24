/* Lab3 assignment 5
 * Most of the code not in the main is copied from the book as said in the assignment
 * the program is a hash table
 */
import java.util.*;
public class Hashing <Key, Value >{

	  private int n;       // number of key-value pairs
	    private int m;       // hash table size
	    private Node[] st;   // array of linked-list symbol tables

	    // linked list data type
	    private static class Node {
	        private Object key;
	        private Object val;
	        private Node next;

	        public Node(Object key, Object val, Node next)  {
	            this.key  = key;
	            this.val  = val;
	            this.next = next;
	        }
	    }

	    // new chaining hash table
	    public Hashing() {
	        this(997); //from the book 
	    }

	    // new chaining hash table with m lists
	    public Hashing(int m) {
	        this.m = m;
	        st = new Node[m];
	    }


	    // values between 0 and m-1
	    private int hash(Key key) {
	        return (key.hashCode() & 0x7fffffff) % m;
	    }

	    // return number of key-value pairs in symbol table
	    public int size() {
	        return n;
	    }

	    // c
	    public boolean isEmpty() {
	        return size() == 0;
	    }

	    // check is the symbol table empty
	    public boolean contains(Key key) {
	        return get(key) != null;
	    }

	    // return value associated with key, null if no such key
	    public Value get(Key key) {
	        int i = hash(key);
	        for (Node x = st[i]; x != null; x = x.next) {
	            if (key.equals(x.key)) return (Value) x.val;
	        }
	        return null;
	    }

	    // insert key-value pair into the table
	    public void put(Key key, Value val) {
	        if (val == null) {
	            delete(key);
	            return;
	        }
	        int i = hash(key);
	        for (Node x = st[i]; x != null; x = x.next) {
	            if (key.equals(x.key)) {
	                x.val = val;
	                return;
	            }
	        }
	        n++;
	        st[i] = new Node(key, val, st[i]);
	    }

	    // delete key (and associated value) from the symbol table
	    public void delete(Key key) {
	        throw new UnsupportedOperationException();
	    }

	    // return all keys as an Iterable
	    public Iterable<Key> keys()  {
	        LinkedList<Key> queue = new LinkedList<Key>();
	        for (int i = 0; i < m; i++) {
	            for (Node x = st[i]; x != null; x = x.next) {
	                queue.add((Key) x.key);
	            }
	        }
	        return queue;
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

	        Hashing<String, Integer> st = new Hashing<String, Integer>(97);
	        for (int i = 0; input.hasNext(); i++) {
	            String key = input.next();
	            st.put(key, i);
	        }

	        
	        // print keys
	       /* for (String s : st.keys()) {
	           System.out.println(s + " " + st.get(s));
	          
	        }*/
	  
	        for (String s : st.keys()) {
	        	System.out.println(  st.get(s)%97);
	        }
	        
	        /*for (int j = 0; j<evenHashes.length; j++ ) {
	        System.out.println(  evenHashes[j]%97);	//modulo 97 as that is the length of the array this gets you the hash value. 
	        }*/
	        
	        
	    }

}

