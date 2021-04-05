#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define BLOCK_LIST 13

// S T R U C T 
struct node{
    int value;
    struct node *link;
};
typedef struct node Node;

// P R O T O T I P I
void addNode(Node **head,int value);
Node *newNode(int value);
void printList(Node **head);
Node *midList(Node **head);
Node *searchList(Node **head,int key);
// M A I N
void main(){
    Node *head = NULL;
    int i;
    srand(time(NULL));
    for(i=0;i<BLOCK_LIST;i++) addNode(&head,rand()%100);

    Node *midNode = midList(&head);
    printList(&head);

    printf("Inserire il numero da ricercare::");
    scanf("%d",&i);

    Node *el = searchList((midNode->value>i ? &head:&midNode),i);
    if(el!=NULL)
        printf("Trovato\n");
    else
        printf("Non trovato\n");
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


/* * * * addNode() * * * *
 * aggiunge un nuovo nodo alla lista
 * inserisce il nodo seguendo un ordine crescente
 * in input prende la testa della lista e il valore da dare al nuovo nodo
 * */
void addNode(Node **head,int value){
    if(*head==NULL){
        *head =newNode(value);
    }   
    else{
        Node *currentNode = *head;
        Node *pastNode = NULL;
        while((currentNode != NULL) && (value>currentNode->value)){
            pastNode =  currentNode;
            currentNode = currentNode->link;
        }
        if(pastNode == NULL){ // inserimento in testa
            pastNode = *head; // uso pastNode come elemento temporaneo
            Node *nd = newNode(value);
            nd->link=pastNode;
            *head = nd;
        }
        else if(currentNode==NULL){ // inserimento in coda
            Node *nd = newNode(value);
            pastNode->link = nd;
        }
        else{ // inserimento in mezzo
            Node *nd = newNode(value);
            pastNode->link = nd;
            nd->link = currentNode;
        }
    }
}

/* * * * printList() * * * *
 * prende in input la testa di una lista
 * stampa la lista
 * */
void printList(Node **head){
    Node *currentNode = *head;
    while(currentNode!=NULL){
        printf("|%d| -> ",currentNode->value);
        currentNode = currentNode->link;
    }
    printf("null\n");
}


/* * * * midList() * * * *
 * ritorna elemento centrale della lista
 * */
Node *midList(Node **head){
    static int pos;
    if(*head == NULL){
        pos = pos/2;
        return NULL;
    }
    pos++;
    Node *temp = midList(&(*head)->link);
    if(--pos ==0)
        return *head;
    return temp;
}


/* * * * searchList() * * * * 
 * ricerca una key all'interno della lista
 * */
Node *searchList(Node **head,int key){
    if((*head == NULL)||((*head)->value>key))
        return NULL;
    printf("%d-> ",(*head)->value);
    if((*head)->value==key)
        return *head;
    return searchList(&(*head)->link,key);
}






