/* Realizzare un ADT per un Binary Search Tree
 * */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "item.h"
#include "bst.h"
#define SIZE 20 // numero di nodi

void main(){
    
    int i;
    srand(time(NULL));
    for(i=0;i<SIZE;i++)
        treeAdd(rand()%20);
    treePrint();
}
