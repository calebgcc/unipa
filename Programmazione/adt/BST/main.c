/* Realizzare un ADT per un Binary Search Tree
 * */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "item.h"
#include "bst.h"
#define SIZE 20 // numbers of node

int main(){
    
    treeInit();

    int i;
    srand(time(NULL));
    for(i=0;i<SIZE;i++)
        treeAdd(rand()%100); // random key for node
    
    treeVisit(0); // visit inOrder
    treeVisit(1); // visit preOrder
    treeVisit(2); // visit postOrder

    treePrint(); // print tree

    printf("\nMIN::%d\n",treeMin()); // search for min

    treeDeleteMin(); // delete min
    treePrint();     // and print again tree

    Item key;
    printf("\n[?] Inserisci la key da cercare::");
    scanf("%d",&key);
    if(treeSearch(key))   // search a key
        printf("[*] Trovato\n");
    else
        printf("[!] Non trovato\n");


    return 0;
}
