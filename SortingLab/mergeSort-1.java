/*Lab2
 * Exercise 8
 * Code copied from the book and small alterations made
 * Mergesort from the book
 */
package Lab2;

import java.util.*;

public class mergeSort<Item> implements Comparable<Item> {
	@Override

	public int compareTo(Item o) {
		return 0;
	}

	private static Comparable[] aux; // auxiliary array for merges

	public static void merge(Comparable[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	public static void sort(Comparable[] a) { // Do lg N passes of pairwise merges.
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz + sz)
			// sz: subarray size
			for (int lo = 0; lo < N - sz; lo += sz + sz) // lo: subarray index
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));

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
