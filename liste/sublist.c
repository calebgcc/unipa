/* scrivere una funzione che genera una lista contenente
 * tutte le sotto-liste di una lista passata alla funzione;
 * */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM 4

// S T R U C T 
struct node{
    int value;
    struct node *link;
};

struct supernode{
    int index;
    struct node *link;
    struct supernode *superLink;
};

typedef struct node Node;
typedef struct supernode superNode;

// P R O T O T I P I
Node *newNode(int value);
int printList(Node **head);
void printSuper(superNode **superHead);
void pushNode(Node **head,int value);
void list2array(Node **head, int vect[]);
superNode *sublist(int vect[], int len);
superNode *newSuper(int index);
Node *cpList(Node **head);
// M A I N
void main(){
    // V A R
    Node *head = NULL;
    int i, len;

    // M A K E - L I S T
    srand(time(NULL));
    for(i=0;i<NUM;i++) pushNode(&head,rand()%100);
    
    len = printList(&head);
    printf("-------------\n");

    // L I S T - 2 - A R R A Y
    int vect[len];
    list2array(&head,vect);


    // M A K E - L I S T - O F - S U B L I S T
    superNode *superHead = sublist(vect,len);
    printSuper(&superHead);

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

/* * * * newSuper() * * * *
 * Alloca un nuovo super nodo in memoria Dinamica
 * in input prende l'index del nuovo nodo
 * ritorna il nodo
 * */
superNode *newSuper(int index){
    superNode *nd = (superNode *)malloc(sizeof(superNode));
    nd->index = index;
    nd->link = NULL;
    nd->superLink = NULL;
    return nd!=NULL ? nd:NULL;
}

/* * * * printList() * * * *
 * prende in input la testa di una lista
 * stampa la lista
 * ritorna inoltre la lunghezza della lista
 * */
int printList(Node **head){
    Node *currentNode = *head;
    int i = 0;
    while(currentNode!=NULL){
        printf("|%d| -> ",currentNode->value);
        currentNode = currentNode->link;
        i++;
    }
    printf("null\n");
    return i;
}

/* * * * printSuper() * * * *
 * Stampa una super lista
 * */
void printSuper(superNode **superHead){
    superNode *superCurrent = *superHead;
    while(superCurrent!=NULL){
        printList(&superCurrent->link);
        superCurrent = superCurrent->superLink;
    }
} 

/* * * * pushNode() * * * *
 * in input riceve un node di testa e un valore
 * aggiunge un nuovo nodo in coda, solo in coda
 * */
void pushNode(Node **head,int value){
    if(*head==NULL){
        *head = newNode(value);
    }
    else{
        Node *currentNode = *head;
        while(currentNode->link!=NULL){
            currentNode = currentNode->link;
        }
        currentNode->link = newNode(value);
    }
}

/* * * * list2array() * * * *
 * Trasforma una lista in un array
 * utile poichè non abbiamo i limiti di accesso sequeunziale
 * */
void list2array(Node **head, int vect[]){
    Node *currentNode = *head;
    int i=0;
    while(currentNode!=NULL){
        vect[i++] = currentNode->value;
        currentNode = currentNode->link;
    }
}

/* * * * sublist() * * * *
 * Prende in input un array (list2array)
 * Ritorna una super lista, ogni super nodo sarà
 * linkato ad una sub list della lista originale
 * */
superNode *sublist(int vect[], int len){
    superNode *superHead = newSuper(-1); // nodo di appoggio/head
    superNode *R = superHead; // super nodo che contiene una sublist di riferimento
    superNode *superTail = NULL; // ultimo super nodo aggiunto alla super lista
    superNode *superPast = superHead; // super nodo precedente
    int i, pass=0; // pass viene utilizzato per entrare nel ciclo quando R->superLink = NULL
    
    while((pass==0)||(R->superLink != NULL)){
        for(i=(R->index)+1;i<len;i++){
            superTail = newSuper(i);
            superTail->link = cpList(&R->link);
            pushNode(&superTail->link,vect[i]);
            superPast->superLink = superTail;
            superPast = superTail;
        } 
        R = R->superLink;
        pass=1;
    }
    return superHead;
}


/* * * * cpList() * * * *
 * Prende in input una lista
 * ritorna la copia della lista
 * */
Node *cpList(Node **head){
    
    if(*head == NULL){
        return NULL;
    }

    Node *currentNode = *head;
    Node *cpHead = newNode((*head)->value);
    currentNode = currentNode->link;
    while(currentNode!=NULL){
        pushNode(&cpHead,currentNode->value);
        currentNode = currentNode->link;
    }
    return cpHead;
}




