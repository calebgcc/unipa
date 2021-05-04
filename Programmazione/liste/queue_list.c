/* Implementazione della struttura dati 'queue' con liste
 * */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define LEN 11

// S T R U C T 
struct node{
    int value;
    struct node *link;
};
typedef struct node Node;

// P R O T O T I P I
Node *newNode(int value);
void printQueue(Node **head);
void enqueue(Node **head, Node **tail,int value); 
int dequeue(Node **head, Node **tail); 
char isEmpty(Node **head);

// M A I N
void main(){
    // V A R 
    Node *head = NULL;
    Node *tail = NULL;
    int i,value;
    
    // E N Q U E U E
    for(i=0;i<LEN;i++){
        value = rand()%100;
        printf("%d::",value);
        enqueue(&head,&tail,value);
    } 
    printf("\n");
    
    printQueue(&head);

    // D E Q U E U E
    while(!isEmpty(&head)){
        printf("%d::",dequeue(&head,&tail));
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


/* * * * printQueue() * * * *
 * prende in input la testa della coda
 * stampa la coda
 * */
void printQueue(Node **head){
    Node *currentNode = *head;
    while(currentNode!=NULL){
        printf("|%d| -> ",currentNode->value);
        currentNode = currentNode->link;
    }
    printf("null\n");
}


/* * * * enqueue() * * * *
 * in input riceve un node di testa e un valore
 * aggiunge un nuovo nodo in coda, solo in coda
 * */
void enqueue(Node **head,Node **tail,int value){
    if(*head==NULL){
        *head = newNode(value);
        *tail = *head;
    }
    else{
        (*tail)->link = newNode(value);
        *tail = (*tail)->link;
    }

}


/* * * * dequeue() * * * *
 * in input ricevo head e tail della coda
 * il primo elemento viene eliminato
 * il suo valore viene ritornato
 * */
int dequeue(Node **head, Node **tail){
    int value = (*head)->value;
    Node *temp = *head;
    *head = (*head)->link;
    if(*head==NULL){
        *tail == NULL;
    }
    free(temp);
    return value;
}


/* * * * isEmpty() * * * *
 * Controlla se esiste almeno un elemento in coda
 * */
char isEmpty(Node **head){
    return (*head == NULL) ? 1:0;
}




