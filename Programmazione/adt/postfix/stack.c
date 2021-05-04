// ADT - STACK - LISTE
#include <stdio.h>
#include <stdlib.h>
#include "item.h"

struct node{
    Item value;
    struct node* link;
};
typedef struct node Node;

static Node *head;


/* * * * init() * * * *
 * init dello stack
 * */
void stack_init(){
    head = NULL;
}

/* * * * stack_push() * * * * 
 * prende in input un item
 * fa il push dell'item nello stack
 * */
void stack_push(Item i){
    Node *nd = (Node *)malloc(sizeof(Node));
    nd->value = i;
    nd->link = head;
    head = nd;
}

/* * * * stack_pop() * * * *
 * ritorna l'ultimo item aggiunto allo stack
 * libera la memoria allocata dinamicamente
 * */ 
Item stack_pop(){
    if(head != NULL){
        Node *nd = head;
        Item i = head->value;
        head = head->link;
        free(nd);
        return i;
    }
    return NULL_ITEM;
}



