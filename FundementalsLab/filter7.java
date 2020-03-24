/* Algorithms and Data Structures ID1020
 * Constantin Wiederin
 * Lab 1
 * Exercise 7 
 * Can determine if parentheses are properly balanced
 * 6/9/2019
 */
import java.util.*;
public class filter7 {

	public static class Stack{

        private Node head; // the head of the stack 
        private int N; // the number of items on the stack

        private class Node{ //define nodes
            char data;
            Node next;
        }

        public boolean isEmpty() { return head == null; } // from the book

        public int size() { return N; }

        public void push(char item){ // Add item to head of stack.
            Node oldfirst = head;
            head = new Node();
            head.data = item;
            head.next = oldfirst;
            N++;
        }

        public void pop(){ // Remove item from head of stack.
        	char item = head.data;
            head = head.next;
            N--;
        }

        public boolean check (String item, filter7.Stack curly){
            int length = item.length();
            char save;
            for(int i = 0; i < length; i++){
                save = item.charAt(i);
                if(save == '{' || save == '[' || save == '('){ //if an open bracet comes push it on the stack
                    curly.push(save);
                }
                else if (!isEmpty() && ((save == '}' && head.data == '{') || (save == ']' && head.data == '[')
    				|| (save == ')' && head.data == '('))) { // if the start of the stack is equal to the closing one pop it
                	pop();
                }
                else if(save ==')' || save == '}' || save == ']') {
                	return false; //if a closed on appears with out an open one return wrong
                }
            }

            return curly.isEmpty();
        }
    }

    public static void main(String[] args) {
        filter7.Stack stack = new filter7.Stack();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        Boolean verification = stack.check(input, stack);

        if(verification){
            System.out.println("Balanced");
        }
        else{
            System.out.println("Unbalanced");
        }
    }
}