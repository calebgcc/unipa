/* Implementazione della struttura dati 'stack' con liste
 * */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM 5

// S T R U C T 
struct node{
    int value;
    struct node *link;
};
typedef struct node Node;

// P R O T O T I P I
Node *newNode(int value);
void pushNode(Node **head,int value);
int popNode(Node **head);
void printStack(Node **head);
char isEmpty(Node **head);

// M A I N
void main(){
    // V A R 
    Node *head = NULL;
    int i,v;

    // P U S H
    srand(time(NULL));
    for(i=0;i<NUM;i++){
        v = rand()%100;
        printf("%d::",v);
        pushNode(&head,v);
    }
    printf("\n");
    printStack(&head);

    // P O P
    while(!isEmpty(&head)){
        printf("%d::",popNode(&head));
    }
    printf("\n");
}

// F U N Z I O N I
/* * * * newNode() * * * *
 * Alloca un nuovo nodo in memoria Dinamica
 * in input prende i valori del nuovo nodo
 * ritorna il nodo
 * */
Node *newNode(int value){
    Node *nd =(Node *)malloc(sizeof(Node));
    nd->value = value;
    nd->link= NULL;
    return nd!=NULL ? nd:NULL;
}

/* * * * pushNode() * * * *
 * Inserisce un nuovo nodo nello stack
 * politica L.I.F.O.
 * */
void pushNode(Node **head,int value){
    if(*head==NULL){
        *head = newNode(value);
    }
    else{
        Node *temp = *head;
        Node *nd = newNode(value);
        nd->link=temp;
        *head = nd;
    }
}

/* * * * popNode() * * * *
 * in input prende la testa di uno stack
 * rimuove il primo elemento dello stack e ne ritorna il valore
 * */
int popNode(Node **head){
    Node *temp = *head;
    int value = temp->value;
    *head = (*head)->link;
    free(temp);
    return value;
}
 
/* * * * printStack() * * * *
 * prende in input la testa di uno stack
 * stampa lo stack
 * */
void printStack(Node **head){
    Node *currentNode = *head;
    while(currentNode!=NULL){
        printf("|%d| -> ",currentNode->value);
        currentNode = currentNode->link;
    }
    printf("null\n");
}

/* * * * isEmpty() * * * *
 * prende in input la testa di uno stack
 * ritorna 0 se vi è almeno un elemento
 * ritorna 1 altrimenti
 * */
char isEmpty(Node **head){
    return (*head==NULL) ? 1:0;
}
