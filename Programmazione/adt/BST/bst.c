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
static void add(Node **root,Item i);
static void inOrder(Node *root);
static void printLink(int n);
static void print(Node *root, int liv);
static void preOrder(Node *root);
static void postOrder(Node *root);
static char search(Node *root,Item i);
static Item min(Node *root);
static void deleteMin(Node **root);


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
static void add(Node **root, Item i){
    if(*root == NULL){
        *root = newNode(i);
        return;
    }
    if(i>(*root)->value)
        add(&(*root)->dx,i);
    else if(i<(*root)->value)
        add(&(*root)->sx,i);
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
static void inOrder(Node *root){
    if(root==NULL) return;
    inOrder(root->sx);
    printf("%d:",root->value);
    inOrder(root->dx);
}

/* * * * preOrder() * * * *
 * visita i sotto-alberi sinistri e destri solo dopo
 * aver visitato il nodo centrale
 * */
 static void preOrder(Node *root){
    if(root==NULL) return;
    printf("%d:",root->value);
    preOrder(root->sx);
    preOrder(root->dx);
}

/* * * * postOrder() * * * *
 * visita i sotto-alberi sinistri e destri e solo dopo
 * visita il nodo centrale
 * */
 static void postOrder(Node *root){
    if(root==NULL) return;
    preOrder(root->sx);
    preOrder(root->dx);
    printf("%d:",root->value);
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
static void print(Node *root, int liv){
    if(root==NULL) return;
    print(root->dx,liv+1);
    printLink(liv);
    printf("(%d)\n",root->value);
    print(root->sx,liv+1);
}


/* * * * treeSearch() * * * *
 * interfaccia per l'utente
 * in input riceve un input da ricercare all'interno dell'albero
 * ritorna 1 se lo trova 0 altrimenti
 * */
 char treeSearch(Item i){
     if(root!=NULL)
        return search(root,i);
    return 0;
 }

/* * * * search() * * * *
 * cerca un item specifico in input
 * */
 static char search(Node *root,Item i){
    if(root==NULL) return 0;
    if(i>root->value)
        return search(root->dx,i);
    else if(i<root->value)
        return search(root->sx,i);
    else
        return 1;
 }

/* * * * treeMin() * * * *
 * interfaccia per l'utente
 * ricerca il minimo
 * */
 Item treeMin(){
    if(root!=NULL)
        return min(root);
    return NULL_ITEM;
 }

/* * * * min() * * * *
 * ricerca del minimo sfruttando
 * le proprietà dei BST
 * */
static Item min(Node *root){
    if(root->sx==NULL)
        return root->value;
    return min(root->sx);
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
static void deleteMin(Node **root){
    if((*root)->sx==NULL){
        Node *tmp = (*root)->dx;
        free(*root);
        *root = tmp;
    }
    else
        deleteMin(&((*root)->sx));
}

 /* * * * - * * * *
 *
 * */

 /* * * * - * * * *
 *
 * */
