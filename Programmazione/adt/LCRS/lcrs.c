#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lcrs.h"


// STRUCT
struct node{
  struct node *ch; // Child
  struct node *sb; // Sibling
  char d;
};
typedef struct node Node;

struct lcrs{
  Node *root;
  char *table;
};

// PROTOTYPE
static Node *newNode(char d);
static void add(Node **root, char dChild, char dFather);
static void visit(Node *root);

//FUNCTIONS
/* * * * newLCRS() * * * *
 * ritorna un puntatore a (strcut lcrs)
 * inizializza root a NULL
 * table = char[256]
 * */
 LCRS newLCRS(){
   LCRS tree =(LCRS) malloc(sizeof(struct lcrs));
   tree->root = NULL;
   tree->table =(char *) malloc(256*sizeof(char));
   return tree;
 }


/* * * * newNode() * * * *
 * ritorna un nuovo nodo
 * */
Node *newNode(char d){
  Node *nd =(Node *) malloc(sizeof(Node));
  nd->ch = NULL;
  nd->sb = NULL;
  nd->d = d;
  return nd;
}

 /* * * * addNode() * * * *
  * interfaccio per l'utente
  * inserisce un nuovo nodo
  * - prima controlla se il valore era già stato inserito in precedenza (niente duplicati)
  * - ricerca il nodo (in cui inserire il nuovo nodo) tramite il suo valore (data), inserisce un nuovo nodo
  * */
void addNode(LCRS tree, char dChild, char dFather){
    if(tree->root == NULL){ // l'albero non ha nodi
      add(&tree->root,dChild,0);
      char *tmp = tree->table;
      *tmp = dChild;
      *(tmp+1) = 0;
      return;
    }

    if(strchr(tree->table, dChild)!= NULL) // il valore che si vuole inserire è un duplicato
      return;
    if(strchr(tree->table, dFather)== NULL)
      return;

    add(&tree->root,dChild,dFather);
    char *end = strchr(tree->table,0);
    *end = dChild;
    *(end+1) = 0;
    return;
}

/* * * * add() * * * *
 * cerca il nodo che ha come valore dFather
 * inserisce un nuovo nodo come figlio di dFather
 * */
static void add(Node **root, char dChild, char dFather){
    if(*root == NULL){
      if(dFather != 0) return;
      *root = newNode(dChild); // inserisco il nodo radice
      return;
    }

    if((*root)->d == dFather){ // inserisco un figlio a questo nodo
      Node *nd = newNode(dChild);
      nd->sb = (*root)->ch;
      (*root)->ch = nd;
      return;
    }

    add(&(*root)->ch,dChild,dFather);
    add(&(*root)->sb,dChild,dFather);
}

/* * * * visitLCRS() * * * *
 * interfaccia per l'utente
 * visita pre-order di un albero LCRS
 * */
void visitLCRS(LCRS tree){
  if(tree->root!=NULL)
    visit(tree->root);
  printf("\n");
}


/* * * * visit() * * * *
 * visita un albero lcrs
 * */
static void visit(Node *root){
  if(root==NULL) return;
  printf("%c|",root->d);
  visit(root->ch);
  visit(root->sb);
}
