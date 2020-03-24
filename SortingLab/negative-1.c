/*
Lab2
Exercise 5
The program places all negative numbers ahead of the positive without sorting the array
*/
#include <stdio.h>
#include <stdlib.h>
int main(void) {
  int numbers [] = {-11, -2, 1, 6, -5, -9, 7, -6, 8, 11};
  int t;
  int j;
  for(int i = 0;i< (sizeof(numbers)/sizeof(numbers[0])); i++){
    /*
    i goes till the end of the array
    and  checks for postive numbers
    */
    if(numbers[i]>0){

      for(j=i;j< (sizeof(numbers)/sizeof(numbers[0]));j++){
        /*
        J goes through the whole array from the point of i and searches for the next negative number
        */
        if(numbers[j]<0){
          t = numbers [i];
          numbers [i] = numbers [j]; // the negative number gets switched for the positive number
          numbers [j] = t;

        }
        else if(j== (sizeof(numbers)/sizeof(numbers[0]))){
          return 0;
        }
      }

    } //every number infront of I will be positive
  }
  int k;
  for(k=0; k< (sizeof(numbers)/sizeof(numbers[0]));k++){
    /*
    K goes through the whole array and prints the numbers
    */
  printf("%d\n", numbers[k]);
  }
}
/*

*/
