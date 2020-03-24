/*Lab2
 * Exercise 8
 * Code copied from the book and small alterations made
 * quicksort from the book
 */
package Lab2;

import java.util.*;

public class quickSort {

	public static void sort(Comparable[] a) {
		Collections.shuffle(Arrays.asList(a)); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // Partition (see page 291).
		sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
	}

	private static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item
		while (true) { // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}

		exch(a, lo, j);
		return j;
		// Put v = a[j] into position
		// with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}

	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	private static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a) { // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	public static void main(String[] args) { // Read strings from standard input, sort them, and print.
		Scanner in = new Scanner(System.in);
		System.out.println("size of the array:");
		int N = in.nextInt();

		Comparable<Integer>[] arr = new Comparable[N];

		Random rand = new Random();

		for (int i = 0; i < N; i++) {
			arr[i] = rand.nextInt();
		}
		
		in.close();

		sort(arr);
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.println("\n");
	}
}
