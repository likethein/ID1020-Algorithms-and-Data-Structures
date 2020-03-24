/*
Lab3 assignment 1
the program removes all non letters from a text
input is any text
it replaces the non letters with space
*/
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
int main() {
  char find; //Creating the find text where we will look for the a
  while ((find = getchar()) != EOF) { //Runs will the character equals the EOF character
    if(!isalpha(find)){ //If char is not a Character
      putchar(' '); //Put the space
    }
    else{
      putchar(find); //else just put the character of the text
    }
  }
}
