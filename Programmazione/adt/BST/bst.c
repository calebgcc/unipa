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
    nd->dx = nd->sx = NULL;
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

/* * * * treeInOrder() * * * *
 * interfaccio per l'utente
 * visita dell'albero InOrder
 * i valori restituiti sono in ordine crescente;
 * Un nodo non viene visitato se prima non viene visitato il suo
 * sott'albero sinistro, in seguito lui e in seguito il sott'albero destro
 * */
void treeInOrder(){
    if(root!=NULL)
        inOrder(root);
    printf("\n");
}

/* * * * inOrder * * * *
 * visita l'albero
 * */
static void inOrder(Node *root){
    if(root==NULL) return;
    inOrder(root->sx);
    printf("%d:",root->value);
    inOrder(root->dx);
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
        printf("|");
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




/* * * * - * * * *
 * 
 * */

/* * * * - * * * *
 * 
 * */

/* * * * - * * * *
 * 
 * */

/* * * * - * * * *
 * 
 * */

