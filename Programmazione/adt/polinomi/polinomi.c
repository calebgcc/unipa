// SourceCode - ADT Polinomi
#include <stdio.h>
#include <stdlib.h>
#include "polinomi.h"

// STRUCTS
struct node{
  int exp;
  double val;
  struct node *link;
};
typedef struct node Node;

struct polinomi{
  Node *head;
  int grd; // grado più alto
};

// PROTOTYPES
Node *newNode(double val, int exp);
void add(Node **head, double val, int exp);

// FUNCTIONS
/* * * * newP() * * * *
 * Restituisce un puntatore a struttura polinomi
 * */
P newP(){
  P p = (P) malloc(sizeof(struct polinomi));
  p->head = NULL;
  p->grd = -1;
  return p;
}

/* * * * addTerm() * * * *
 * Interfaccia per l'utente
 * inserisce un nodo in lista in maniera ordinata
 * */
void addTerm(P p, double val, int exp){
    add(&(p->head),val,exp);
    p->grd = (p->grd<exp) ? exp:p->grd;
}

/* * * * add() * * * *
 *  inserisce un nodo in lista in maniera ordinata
 * l'ordine è stabilito dall'esponente
 * se due nodi hanno lo stesso esponente salviamo un solo nodo che come val avrà la somma dei valori
 * */
void add(Node **head, double val, int exp){
  if(*head==NULL){ // la lista è vuota
      *head = newNode(val,exp);
  }
  else{ // la lista non è vuota
    Node *temp = *head;
    Node *past = NULL;
    Node *nd = NULL;
    while((temp->link!=NULL)&&(temp->exp<exp)){
      past = temp;
      temp = temp->link;
    }
    if(temp->exp == exp){ // stesso esponente, allora sommiamo
      temp->val+=val;
    }
    else if((past==NULL)&&(temp->exp>exp)){ // dobbiamo inserire in testa
      past = newNode(val, exp);
      past->link = *head;
      *head = past;
    }
    else if(temp->exp > exp){ // dobbiamo inserire in mezzo
      nd = newNode(val,exp);
      past->link = nd;
      nd->link = temp;
    }
    else if(temp->exp < exp){ // dobbiamo inserire in coda
        temp->link = newNode(val,exp);
    }
  }
}

/* * * * newNode() * * * *
 * ritorna un nuovo nodo inizializzato nella heap
 * */
Node *newNode(double val, int exp){
  Node *nd =(Node *) malloc(sizeof(Node));
  nd->link = NULL;
  nd->val = val;
  nd->exp = exp;
  return nd;
}

/* * * * print() * * * *
 * stampa il polinomio
 * */
void print(P p){
  Node *temp = p->head;
  for(;temp!=NULL;temp=temp->link){
    if(temp->exp == 0){
      if(temp->val == 0)
        continue;
      else
        printf("%.1lf",temp->val);
    }
    else if(temp->exp == 1){
      if(temp->val == 1)
        printf("x");
      else
        printf("%.1lfx",temp->val);
    }
    else{
      if(temp->val == 1)
        printf("x^%d",temp->exp);
      else
        printf("%.1lfx^%d",temp->val,temp->exp);
    }
    (temp->link == NULL) ? printf("\n"):printf(" + ");
  }
}

/* * * * sumP() * * * *
 * Somma due polinomi lasciandoli invariati
 * ritorna un nuovo polinomio
 * */
P sumP(P A, P B){
  Node *a = A->head;
  Node *b = B->head;
  P p = (P) malloc(sizeof(struct polinomi));
  p->grd = (A->grd > B->grd) ? A->grd:B->grd;
  p->head = newNode(a->val, a->exp);
  a = a->link;
  while(a!=NULL || b!=NULL){
    if(a!=NULL){
      add(&(p->head),a->val,a->exp);
      a = a->link;
    }
    if(b!=NULL){
      add(&(p->head),b->val,b->exp);
      b = b->link;
    }
  }
  return p;
}


/* * * * mulP() * * * *
 * Moltiplica due polinomi lasciandoli invariati
 * ritorna un nuovo polinomio
 * */
P mulP(P A, P B){
  Node *a = A->head;
  Node *b = B->head;
  P p = (P) malloc(sizeof(struct polinomi));
  p->grd = A->grd+B->grd;
  p->head = newNode(0,0);
  while(a!=NULL){
    while(b!=NULL){
      add(&(p->head),a->val*b->val,a->exp+b->exp);
      b = b->link;
    }
    a = a->link;
    b = B->head;
  }
  return p;
}
