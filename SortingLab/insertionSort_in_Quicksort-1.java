/*Lab2
 * Exercise 9
 * Code copied from the book and small alterations made
 *  both methods from the book
 */
package Lab2;

import java.util.*;

public class insertionSort_in_Quicksort {
	static int m = 10;

	public static void sort(Comparable[] a) {
		Collections.shuffle(Arrays.asList(a)); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo + m) { 
			Insertion.sort(a, lo, hi);
			return;
		}
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

	public static class Insertion {
		public static void sort(Comparable[] a, int lo, int hi) { // Sort a[] into increasing order.
			int N = a.length;
			for (int i = (lo +1); i < hi; i++) { // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
				for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
					exch(a, j, j - 1);
			}
		}

		public static boolean less(Comparable v, Comparable w) {
			return v.compareTo(w) < 0;
		}

		public static void exch(Comparable[] a, int i, int j) {
			Comparable t = a[i];
			a[i] = a[j];
			a[j] = t;
		}

		public static void show(Comparable[] a) { // Print the array, on a single line.
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
}
