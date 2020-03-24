#include <stdio.h>
#include <stdlib.h>

void rev() {
  char t;
  if((t=getchar()) != '\n')rev(); //
    putchar(t); //
}

void iterationrev() {
  char* space = malloc(10 * sizeof(char)); // creates the space in the memory of length 10 and a pointer to it
  char* spacecopy = space; // creating a copy of the pointer

  while ((*space = getchar()) != '\n') { // while the char is not a new line
    space++; // increase the pointer till the end of the input
  }
  while (space != spacecopy) { // while the pointer that points to the end does not equal to the copy pointer
    putchar(*--space); // Print the char
    }
    printf("\n"); // new line
}

int main() {
  iterationrev();
  //putchar('\n');
}
