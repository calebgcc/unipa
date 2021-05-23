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
  for(;temp->link!=NULL;temp=temp->link)
    (temp->exp>1) ? printf("%.1lfx^%d + ",temp->val,temp->exp):printf("%.1lf + ",temp->val);
  (temp->exp>1) ? printf("%.1lfx^%d \n ",temp->val,temp->exp):printf("%.1lf \n",temp->val);
}
