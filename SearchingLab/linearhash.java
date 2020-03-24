import java.util.*;
public class linearhash<Key, Value> {
	private static final int CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;    // the values
    
    public linearhash() {
        this(CAPACITY);
    }

    public linearhash(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];
    }

 
    public boolean isEmpty() {
        return size() == 0;
    }


    private int size() {
		return 0;
	}

	public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }


    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

   
    private void resize(int capacity) {
        linearhash<Key, Value> temp = new linearhash<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException();

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) resize(m/2);

        assert check();
    }

   
    public Iterable<Key> keys() {
        LinkedList<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a delete()
    private boolean check() {

        // check that hash table is at most 50% full
        if (m < 2*n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

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

        Stopwatch timer = new Stopwatch();

        linearhash<String, Integer> st = new linearhash<String, Integer>();
        for (int i = 0; input.hasNext(); i++) {
            String key = input.next();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));

        double time = timer.elapsedTime();
        System.out.println("LinearProbing: " + time);
    }
}
