/* Scrivere una funzione ricorsiva che stampi tutte le sottoliste di una lista data
 * */
#include <stdio.h>
#include <stdlib.h>

struct node{
    int i;
    struct node *link;
};
typedef struct node Node;

void print(Node *list){
    if(list == NULL){
        printf("\n");
        return;
    }
    printf("%d-> ",list->i);
    print(list->link);
}

void addNode(Node **list,int i){
    if(*list == NULL){
        (*list) =(Node *) malloc(sizeof(Node));
        (*list)->i = i;
        (*list)->link = NULL;
    }
    else{
        Node *temp = *list;
        while(temp->link != NULL) temp = temp->link;
        temp->link =(Node *) malloc(sizeof(Node));
        (temp->link)->i = i;
        (temp->link)->link = NULL;
    }
}

void pop(Node **list){
    if(*list != NULL){
        Node *temp = *list;
        Node *past = NULL;
        while(temp->link != NULL){
            past = temp;
            temp = temp->link;
        }
        free(temp);
        if(past != NULL)
            past->link = NULL;
        else *list = NULL;
    }
}

void sublist(Node *list, Node *sub){
    if(list == NULL) return;
    Node *temp = list;
    while(temp != NULL){
        addNode(&sub,temp->i);
        print(sub);
        sublist(temp->link,sub);
        pop(&sub);
        temp = temp->link;
    }
}

int main(){
    Node *head = NULL;
    addNode(&head,1);
    addNode(&head,2);
    addNode(&head,3);
    sublist(head,NULL);

    return 0;
}
