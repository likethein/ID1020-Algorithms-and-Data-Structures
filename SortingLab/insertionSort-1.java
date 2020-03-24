/* Lab2
 * Exercise 1/2/3/4
 * Program sorts a inputed number of numbers
 * Printed after each inner loop iteration
 * 
 */

package Lab2;
import java.util.*;
public class insertionSort {
	void sort(int arr[]) 
    { 
		int counter = 0; // counter for the number of swaps
        int n = arr.length;  // n is the lenght given by the user
        for (int i = 1; i < n; ++i) {  //goes till the end of the array
            int key = arr[i];  
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) {  // while the number before is greater than the one infront of it
                arr[j + 1] = arr[j];  // the j number goes to the position ahead of itself
                j = j - 1; 
                counter ++;
            } 
            arr[j + 1] = key; 
            printArray(arr);
            System.out.println();
            System.out.print("the number of swaps = " + counter);
            System.out.println();
        } 
    } 
  
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; ++i) 
            System.out.print(arr[i] + " "); 
  
        System.out.println(); 
    }
    
    public static void inversions(int arr[]) {
    	int inversions = 0; //counter of inversions
    	for(int i = 1; i<arr.length; i++) {//for the whole array
    		for(int j = i-1; j> -1; j--) { //j goes from the front of i
    			if(arr[i] < arr[j]) { // if the one infront is larger than the one behind
    				System.out.println("[" + (i) + "," + arr[i] + "]" + "[" + j + "," + arr[j] + "] "); //change and print the two
    				inversions++;
    			}
    		}
    	}
    	System.out.println(inversions);
    }
  
    // Driver method 
    public static void main(String args[]) 
    { 
    	System.out.println("enter the number of numbers you want:");
    	Scanner input = new Scanner(System.in); 
    	int length = input.nextInt();
        int[] arr = new int[length]; 
        for(int i=0; i<length; i++) {
        	arr[i] = input.nextInt();
        }
        /*for(int i = 0; i<arr.length ; i++) {
        arr[i] = arr [i] * -1;
        } */ //exercise 2
    
        inversions(arr);
        insertionSort ob = new insertionSort(); 
        ob.sort(arr); 
        /*
        for(int i = 0; i<arr.length ; i++) {
            arr[i] = arr [i] * -1;
            } */ //This code is for exercise 2
        printArray(arr); 
    } 
}
