/* Creare una lista circolare ordinata con campo informativo (char)
 * alla richiesta di stampa stampare la lista in ordine crescente e decrescente (con un unica funzione ricorsiva) */
#include <stdio.h>
#include <stdlib.h>

// STRUCT
struct node{
    char ch;
    struct node *link;
};
typedef struct node Node;

//FUNCTIONS
void addNode(Node **head,char ch);
Node *newNode(char ch);
void printList(Node *head,Node *stop);

int main(){ 
    
    char in;
    Node *head = NULL;
    printf("[:] per visualizzare\n[#] per uscire\n");
    do{
        printf("::");
        scanf(" %c",&in);
        if(in == ':'){
            printList(head,NULL);
            printf("\n");
        }
        else addNode(&head,in);
    }while(in!='#');

    return 0;
}


void addNode(Node **head,char ch){
    if(*head == NULL){ // creo la testa se non è presente
        *head = newNode(ch);
        (*head)->link = *head;
        return;
    }
    Node *temp = (*head);
    Node *past = NULL;
    while((temp->link!=(*head))&&(temp->ch < ch)){
        past = temp;
        temp = temp->link;
    }
    if((temp->link == *head)&&(ch>temp->ch)){ // in coda
        Node *nd = newNode(ch);
        nd->link = *head;
        temp->link = nd;
    }
    else if((past!=NULL)&&(temp->ch >= ch)){ // tra due nodi
        Node *nd = newNode(ch);
        past->link = nd;
        nd->link = temp;
    }
    else if((past==NULL)){ // in testa
        *head = newNode(ch);
        (*head)->link = temp;
        while((temp->link)!=(*head)->link) temp=temp->link;
        temp->link = *head;
    }
}

Node *newNode(char ch){
    Node *nd = (Node *) malloc(sizeof(Node));
    nd->ch = ch;
    nd->link = NULL;
    return nd;
}


void printList(Node *head,Node *stop){
    if(head == stop){
        printf("\n");
        return;
    }
    if(stop == NULL) stop = head;
    printf("%c-> ",head->ch);
    printList(head->link,stop);
    printf("%c-> ",head->ch);
}
