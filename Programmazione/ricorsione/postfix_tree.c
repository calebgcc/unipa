/* Scrivere un valutatore di espressioni postfisse con un albero binario
 * */
#include <stdio.h>
#include <stdlib.h>

struct node{
    struct node *sx;
    int i;
    struct node *dx;
};
typedef struct node Node;

void makeTree(char **sptr, Node **root){
    while(**sptr == ' ') *sptr -=1;
    if(**sptr=='*'||**sptr=='/'||**sptr=='+'||**sptr=='-'){
        *root=(Node *) malloc(sizeof(Node));
        (*root)->i= **sptr;
        *sptr -= 1;
        makeTree(sptr,&((*root)->sx));
        *sptr -= 1;
        makeTree(sptr,&((*root)->dx));
    }
    else{
        while(**sptr>='0' && **sptr<='9') *sptr -= 1;
        *sptr += 1;
        *root =(Node *) malloc(sizeof(Node));
        (*root)->i = atoi(*sptr);
        (*root)->sx = (*root)->dx = NULL;
    }
}

int val(Node *root){
    if(root->sx == NULL && root->dx == NULL) return root->i;
    switch(root->i){
        case '+':
            return val(root->dx) + val(root->sx);
        case '-':
            return val(root->dx) - val(root->sx);
        case '*':
            return val(root->dx) * val(root->sx);
        case '/':
            return val(root->dx) / val(root->sx);
    }
}

int main(int argc, char *argv[]){
    Node *root = NULL;
    char *ptr = argv[1];
    for(;*ptr;ptr++);
    ptr--;
    makeTree(&ptr,&root);
    printf("%s = %d \n",argv[1],val(root));
    return 0;
}
