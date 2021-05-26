// Dato un vettore (ordinato) scrivere una funzione ricorsiva
// che resituisca un albero binario di ricerca bilanciato con gli elementi del vettore
#include <stdio.h>
#include <stdlib.h>

struct node{
    struct node *sx;
    int i;
    struct node *dx;
};
typedef struct node Node;

void visit(Node *tree){
    printf("%d:",tree->i);
    if(tree->sx != NULL) visit(tree->sx);
    if(tree->dx != NULL) visit(tree->dx);
}

Node *inalbera(int *a, int N){
    if(N == 0) return NULL;
    int mid = (N-1)/2;
    static Node *root = NULL;
    if(root == NULL){
        root = (Node *) malloc(sizeof(Node));
        root->sx = root->dx = NULL;
        root->i = a[mid];
    }
    else{
        int i = a[mid];
        Node *temp = root;
        Node *past = NULL;
        while(temp!=NULL){
            if(i > temp->i){
                past = temp;
                temp = temp->dx;
            }
            else{
                past = temp;
                temp = temp->sx;
            }
        }
        if(i > past->i){
            past->dx =(Node *) malloc(sizeof(Node));
            past = past->dx;
            past->i = i;
            past->sx = past->dx = NULL;
        }
        else{
            past->sx = (Node *) malloc(sizeof(Node));
            past = past->sx;
            past->i = i;
            past->sx = past->dx = NULL;
        }
    }
    if(N > 1){
        inalbera(a,mid);
        inalbera(&(a[mid+1]),N-mid-1);
    }
    return root;
}

int main(){

    int vet[] = {0,1,3,5,7,9,12,13,15,16};
    Node *tree = inalbera(vet,10);
    visit(tree);
    printf("\n");
    return 0;
}
