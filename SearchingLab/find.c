#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void find(char wordsearch[], int size){
    char x;
    int ind = -1;
    while((c = getchar()) != EOF){ //whole char is not eof
        ind++; //ind goes to 0
        if(x == "" || ind == 0){ // if the char is space or ind = 0 bevaise of first word case
            char t = x;
            char check [size]; // this is going to be used to compare to the inital input size is the length of the word
            int i;
            check[0] = x; //x is placed into check
            for(i = 1; i < size; i++){
                    check[i] = (t = getchar());
                    ind++; // places the length of the word characters into the array
            }
            t = getchar();
            if((t == ' ' || t == '\n') && (strcmp(wordsearch, check) == 0)){ //then if the char after is space and they are equal
                ind++;
                int wordPos = ind;
                wordPos -= size;
                printf("%d", wordPos);
                printf(" ");
            }
            else{
                ind++;
            }
        }
    }
}

int main(){

    char str [] = "the";

    find(str, 3);
}
