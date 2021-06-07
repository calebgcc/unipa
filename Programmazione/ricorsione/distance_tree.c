/* Calcolare la distanza (definita come il numero di archi) tra due nodi, utilizzando una funzione ricorsiva
 * */
#include <stdio.h>
#include <stdlib.h>

struct node{
    struct node *sx;
    int i;
    struct node *dx;
};
typedef struct node Node;

void makeTree(Node **root,int i){
    if(*root == NULL){
        *root = (Node *) malloc(sizeof(Node));
        (*root)->i = i;
        (*root)->sx = (*root)->dx = NULL;
    }
    else{
        if(i > (*root)->i) makeTree(&((*root)->dx),i);
        else makeTree(&((*root)->sx),i);
    }
}

void visit(Node *root){
    if(root == NULL) return;
    visit(root->sx);
    printf("%d:",root->i);
    visit(root->dx);
    printf("\n");
}

int d(Node *root, int A, int B){
    if(root == NULL) return 0;
    if(A > B){ // swap
        A = A^B;
        B = A^B;
        A = A^B;
    }
    if(A==B){
        if(A == root->i) return 0;
        else if(A > root->i) return 1+d(root->dx,A,A);
        else return 1+d(root->sx,A,A);
    }
    else if(A<=root->i && B>=root->i){
        int result = 0;
        result = d(root,A,A);
        result+=d(root,B,B);
        return result;
    }
    else if(A > root->i)
        return d(root->dx,A,B);
    else
        return d(root->sx,A,B);

}

int main(int argc, char *argv[]){
    Node *root = NULL;
    makeTree(&root,5);
    makeTree(&root,2);
    makeTree(&root,3);
    makeTree(&root,7);
    makeTree(&root,6);
    makeTree(&root,8);
    makeTree(&root,9);
    printf("Distance:: %d\n",d(root,atoi(argv[1]),atoi(argv[2])));
    return 0;
}
