// Scrivere una funzione ricorsiva che dato un array di interi
// restituisca una lista doppiamente concatenata
#include <stdio.h>
#include <stdlib.h>

// STRUCT
struct node{
  struct node *b;
  int i;
  struct node *f;
};
typedef struct node Node;

// FUNCTIONS
Node *toList(int *vet, int dim){
  if(dim == 0) return NULL; // caso base

  static Node *head = NULL; // testa della lista
  static Node *temp = NULL; // variabile di comodo

  if(head == NULL){
    head = (Node *) malloc(sizeof(Node));
    head->i = vet[0];
    head->f = head->b = NULL;
    temp = head;
  } else{
    Node *nd = (Node *) malloc(sizeof(Node));
    nd->i = vet[0];
    nd->f = NULL;
    nd->b = temp;
    temp->f = nd;
    temp = temp->f;
  }
  toList(&(vet[1]),dim-1);
  return head;
}

void printList(Node *head){

  Node *temp = head;
  while(temp->f!=NULL){
    printf("|%d|->",temp->i);
    temp=temp->f;
  }
  printf("|%d|->NULL\n",temp->i);
  while(temp!=NULL){
    printf("|%d|->",temp->i);
    temp = temp->b;
  }
  printf("NULL\n");
}


int main(){
  int vet[] = {1,3,5,7,9,11,13,15,17,19,21};
  Node *head = toList(vet,11);
  printList(head);
  return 0;
}
