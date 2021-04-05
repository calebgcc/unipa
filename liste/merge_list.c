#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define LEN 8

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
Node *mergeList(Node **sxList,Node **dxList);

// M A I N
void main(){
    Node *list1 = NULL;
    Node *list2 = NULL;
    int i;
    srand(time(NULL));
    for(i=0;i<LEN;i++) addNode(&list1,rand()%100);
    srand(time(NULL)+200);
    for(i=0;i<(LEN+2);i++) addNode(&list2,rand()%100);
    
    Node *head = mergeList(&list1,&list2);

    printList(&list1);
    printList(&list2);
    printList(&head);
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


/* * * * mergeList() * * * *
 * riceve in input due liste
 * fa il merge delle liste e ritorna la lista fusa
 * */
Node  *mergeList(Node **sxList,Node **dxList){
    Node *endList = NULL;
    Node *sx = *sxList;
    Node *dx = *dxList;
    while((sx!=NULL)&&(dx!=NULL)){
        if(sx->value<dx->value){
            if(endList==NULL)
                endList= newNode(sx->value);
            else
                addNode(&endList,sx->value);
            sx=sx->link;
        }
        else{ 
            if(endList==NULL)
                endList= newNode(dx->value);
            else
                addNode(&endList,dx->value);
            dx=dx->link;
        }
    }

    if(sx!=NULL){
        while(sx!=NULL){
            addNode(&endList,sx->value);
            sx=sx->link;
        }
    }
    if(dx!=NULL){
        while(dx!=NULL){
            addNode(&endList,dx->value);
            dx=dx->link;
        }
    }
    return endList;
}








