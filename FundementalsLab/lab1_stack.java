/* Algorithms and Data Structures ID1020
 * Constantin Wiederin
 * Lab 1
 * Exercise 2 
 * A stack that prints the inserted numbers in reverse order
 * 5/9/2019
 */
import java.util.*;

public class lab1_stack {
	char str[];
	int index;
	int size;
	
	public lab1_stack(int size) {
		index = 0;
		this.size = size; // taking in the size for the array
		str =  new char[size]; // creating the array with the right size
		
	}
		public void push(char input) { // Push method to push the number onto the stack
			if(index-1 >= size) {
				System.out.println("Stackoverflow"); // if there is too much in the stack
			}
			str[index] = input; // putting the input into the stack
			index ++; // increasing the pointer
		}
	
		public char pop() {
			if(index< 0) {
				System.out.println("Stack cleared"); // when the stack is empty
			}
			index --; // decreasing the index pointer
			return str[index]; // returning the poped item
		}
		
		public static void main(String[] args) {
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			lab1_stack reverse = new lab1_stack(str.length());
			
			for(int i=0; i<str.length();i++) {
				reverse.push(str.charAt(i));
			}
			System.out.print("[");
			for(int j=0; j<str.length(); j++) {
				System.out.print(reverse.pop());
				if(j<str.length()-1)
				System.out.print(",");
				
			}
			System.out.print("]");
			
		}
}


