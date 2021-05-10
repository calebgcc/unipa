// INTERFACE - ADT - BST
#include <stdio.h>
#include <stdlib.h>
#include "item.h"
#include "bst.h"

// NODE - TREE
struct node{
    struct node *sx;
    Item value;
    struct node *dx;
};
typedef struct node Node;

// VAR - PROTOTIPI
Node *root;
static Node *newNode(Item i);
static void add(Node **treeNode,Item i);
static void inOrder(Node *treeNode);
static void printLink(int n);
static void print(Node *treeNode, int liv);
static void preOrder(Node *treeNode);
static void postOrder(Node *treeNode);
static Node *search(Node *treeNode,Item i);
static Node *min(Node *treeNode);
static void deleteMin(Node **treeNode);
static void delete(Node **treeNode, Item i);

/* * * * init() * * * *
 * inizializzo l'albero
 * */
void treeInit(){
    root = NULL;
}

/* * * * newNode() * * * *
 * in inputp prende un Item
 * restituisce un nodo
 * */
static Node *newNode(Item i){
    Node *nd =(Node *) malloc(sizeof(Node));
    nd->value=i;
    nd->dx = NULL;
    nd->sx = NULL;
    return nd;
}

/* * * * treeAdd() * * * *
 * interfaccia per l'utente
 * richiamo una funzione che aggiunge un nodo
 * in input prende un item, che sarà il valore del nuovo nodo
 * */
void treeAdd(Item i){
    add(&root,i);
}

/* * * * add() * * * *
 * aggiunge un nodo all'albero
 * */
static void add(Node **treeNode, Item i){
    if(*treeNode == NULL){
        *treeNode = newNode(i);
        return;
    }
    if(i>(*treeNode)->value)
        add(&(*treeNode)->dx,i);
    else if(i<(*treeNode)->value)
        add(&(*treeNode)->sx,i);
    // non aggiungo i duplicati
}

/* * * * treeVisit() * * * *
 * interfaccia per l'utente
 * visita dell'albero InOrder=0 preOrder=1 postOrder=2
 * i valori restituiti sono in ordine crescente;
 * Un nodo non viene visitato se prima non viene visitato il suo
 * sott'albero sinistro, in seguito lui e in seguito il sott'albero destro
 * */
void treeVisit(char ch){
    if(root!=NULL){
        if(ch==0)
            inOrder(root);
        else if(ch==1)
            preOrder(root);
        else if(ch==2)
            postOrder(root);
    }
    printf("\n");
}

/* * * * inOrder * * * *
 * visita prima i sotto-alberi sinistri
 * poi i nodi centrali
 * poi i sotto-alberi destri
 * */
static void inOrder(Node *treeNode){
    if(treeNode==NULL) return;
    inOrder(treeNode->sx);
    printf("%d:",treeNode->value);
    inOrder(treeNode->dx);
}

/* * * * preOrder() * * * *
 * visita i sotto-alberi sinistri e destri solo dopo
 * aver visitato il nodo centrale
 * */
 static void preOrder(Node *treeNode){
    if(treeNode==NULL) return;
    printf("%d:",treeNode->value);
    preOrder(treeNode->sx);
    preOrder(treeNode->dx);
}

/* * * * postOrder() * * * *
 * visita i sotto-alberi sinistri e destri e solo dopo
 * visita il nodo centrale
 * */
 static void postOrder(Node *treeNode){
    if(treeNode==NULL) return;
    preOrder(treeNode->sx);
    preOrder(treeNode->dx);
    printf("%d:",treeNode->value);
}

/* * * * printLink() * * * *
 * utility per stampare "--" durante la stampa
 * orizzontale dell'albero
 * in input prende il livello da cui dipende il numero di trattini
 * */
static void printLink(int n){
    printf("|");
    int i;
    for(i=3;i<(3*n);i++)
        printf(" ");
    printf("|--");
}


/* * * * treePrint() * * * *
 * interfaccia per l'utente
 * stampa l'albero in orizzontale
 * visita simile all'inOrder
 * */
void treePrint(){
    if(root!=NULL)
        print(root,1);
    printf("\n");
}


/* * * * print() * * * *
 * prende in input il nodo radice e il livello
 * */
static void print(Node *treeNode, int liv){
    if(treeNode==NULL) return;
    print(treeNode->dx,liv+1);
    printLink(liv);
    printf("(%d)\n",treeNode->value);
    print(treeNode->sx,liv+1);
}


/* * * * treeSearch() * * * *
 * interfaccia per l'utente
 * in input riceve un input da ricercare all'interno dell'albero
 * ritorna 1 se lo trova 0 altrimenti
 * */
 char treeSearch(Item i){
     if(root!=NULL)
        return (search(root,i))!=NULL ? 1:0;
    return 0;
 }

/* * * * search() * * * *
 * cerca un item specifico in input
 * */
 static Node *search(Node *treeNode,Item i){
    if(treeNode==NULL) return NULL;
    if(i>treeNode->value)
        return search(treeNode->dx,i);
    else if(i<treeNode->value)
        return search(treeNode->sx,i);
    else
        return treeNode;
 }

/* * * * treeMin() * * * *
 * interfaccia per l'utente
 * ricerca il minimo
 * */
 Item treeMin(){
    if(root!=NULL)
        return (min(root))->value;
    return NULL_ITEM;
 }

/* * * * min() * * * *
 * ricerca del minimo sfruttando
 * le proprietà dei BST
 * */
static Node *min(Node *treeNode){
    if(treeNode->sx==NULL)
        return treeNode;
    return min(treeNode->sx);
}

/* * * * treeDeleteMin() * * * *
 * interfaccia per l'utente
 * elimina il nodo contente le key minore
 * */
 void treeDeleteMin(){
    if(root!=NULL)
      deleteMin(&root);
 }

 /* * * * deleteMin() * * * *
 * cancella il minimo nodo
 * */
static void deleteMin(Node **treeNode){
    if((*treeNode)->sx==NULL){
        Node *tmp = (*treeNode)->dx;
        free(*treeNode);
        *treeNode = tmp;
    }
    else
        deleteMin(&((*treeNode)->sx));
}

 /* * * * treeDelete() * * * *
 * interfaccia per l'utente
 * cerca un nodo, si cancella il nodo
 * */
void treeDelete(Item i){
    if(root!=NULL)
        delete(&root,i);
}

 /* * * * delete() * * * *
 * elimina un nodo passato in input
 * */
 static void delete(Node **treeNode, Item i){
    if(*treeNode == NULL) return;
    // FASE DI RICERCA
    if(i<(*treeNode)->value){
        delete(&(*treeNode)->sx,i);
        return;
    }
    if(i>(*treeNode)->value){
        delete(&(*treeNode)->dx,i);
        return;
    }
    // FASE DI ELIMINAZIONE
    if((*treeNode)->dx==NULL){
        printf("[A]\n");
        Node *tmp = (*treeNode)->sx;
        free(*treeNode);
        *treeNode = tmp;
        return;
    }
    else if((*treeNode)->sx==NULL){
        printf("[B]\n");
        Node *tmp = (*treeNode)->dx;
        free(*treeNode);
        *treeNode = tmp;
        return;
    }
    else{
        printf("[C]\n");
        (*treeNode)->value = (min((*treeNode)->dx))->value;
        deleteMin(&((*treeNode)->dx));
    }
 }

  /* * * * - * * * *
 *
 * */

  /* * * * - * * * *
 *
 * */

  /* * * * - * * * *
 *
 * */
