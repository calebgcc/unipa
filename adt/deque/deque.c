// FUNZIONI - DEQUE - ADT
#include <stdio.h>
#include <stdlib.h>
#include "item.h"
#include "deque.h"


struct node{
    Item value;
    struct node *link;
    struct node *prelink;
};
typedef struct node Node;

Node *head;
Node *tail;

/* * * * deque_init() * * * *
 * inizializza l'ADT
 * */
void deque_init(){
    head=NULL;
    tail=NULL;
}

/* * * * en_tail() * * * *
 * prende in input un valore
 * aggiunge il valore in coda
 * */
void en_tail(Item i){
    if(head==NULL){
        head =(Node *) malloc(sizeof(Node));
        head->link = NULL;
        head->prelink = NULL;
        head->value = i;
        tail = head;
        return; // EXIT
    }
    Node *x =(Node *) malloc(sizeof(Node));
    x->link = NULL;
    x->prelink = tail;
    x->value = i;
    tail->link = x;
    tail = x;
}

/* * * * en_top() * * * *
 * prende in input un valore
 * aggiugne il valore in testa
 * */
void en_top(Item i){
    if(head==NULL){
        head = (Node *) malloc(sizeof(Node));
        head->link = NULL;
        head->link = NULL;
        head->value = i;
        tail = head;
        return; // EXIT
    }
    Node *x =(Node *) malloc(sizeof(Node));
    x->link = head;
    x->prelink = NULL;
    x->value = i;
    head->prelink = x;
    head = x;
}

/* * * * de_tail() * * * *
 * ritorna il valore dell'ultimo nodo in coda
 * libera il nodo dalla deque
 * */
Item de_tail(){
    if(tail==NULL){
        return NULL_ITEM;
    }
    Node *x = tail;
    Item i = tail->value;
    if(tail->prelink!=NULL){
        tail = tail->prelink;
        tail->link=NULL;
    }
    else{
        head = NULL;
        tail = NULL;
    }
    free(x);
    return i;
}

/* * * * de_top() * * * *
 * ritorna il valore del primo nodo in coda
 * libera il nodo dalla deque
 * */
 Item de_top(){
     if(head==NULL){
         return NULL_ITEM;
     }
     Node *x = head;
     Item i = head->value;
     if(head->link!=NULL){
        head = head->link;
        head->prelink=NULL; 
     }
     else{
        head = NULL;
        tail = NULL;
     }
     free(x);
     return i;
 }






