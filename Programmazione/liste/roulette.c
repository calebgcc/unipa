/* Realizzare una Roulette utilizzando una lista circolare
 * */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NERO 0
#define ROSSO 1
#define VERDE 2

// STRUCT
struct node{
    unsigned int n : 6; // cella contentenente numeri da 0 a 36
    unsigned int   : 0;
    unsigned int c : 2; // colore della cella
    struct node *link;
};
typedef struct node Node;

// Global string array
char *colorVector[] = {"Nero","Rosso","Verde"};

Node *newNode(unsigned int n,unsigned int c);
void addNode(Node **roulette,unsigned int n);
void printRoulette(Node *roulette);
void spinRoulette(Node **ball);

// FUNCTIONS
//
int main(){
    srand(time(NULL));
    unsigned int valueVector[] = {0,35,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26};
    Node *roulette = NULL;
    int i;
    for(i=0;i<37;i++) addNode(&roulette,valueVector[i]); // making roulette
    printRoulette(roulette);
    printf("\n");

    Node *ball = roulette;
    spinRoulette(&ball);
    spinRoulette(&ball);
    spinRoulette(&ball);
    spinRoulette(&ball);
    spinRoulette(&ball);
    spinRoulette(&ball);
    return 0;
}

Node *newNode(unsigned int n,unsigned int c){
    Node *nd = (Node *) malloc(sizeof(Node));
    nd->n = n;
    nd->c = c;
    nd->link = NULL;
    return nd;
}

void addNode(Node **roulette, unsigned int n){
    if(*roulette == NULL){
        *roulette = newNode(n,VERDE);
        (*roulette)->link = *roulette;
        return;
    }
    Node *temp = *roulette;
    while(temp->link != *roulette) temp = temp->link;
    temp->link = newNode(n,(temp->c +1)%2);
    temp = temp->link;
    temp->link = *roulette;
}

void printRoulette(Node *roulette){
    Node *temp = roulette;
    int i=0;
    do{
        printf("[%u|%s]",temp->n,colorVector[temp->c]);
        temp = temp->link;
        printf("%c", (i++%6) ? ' ':'\n');
    }while(temp != roulette);
}



void spinRoulette(Node **ball){
    int spin = (random()%101)+100; 
    for(int i=0;i<spin;i++) (*ball) = (*ball)->link;
    printf("Numero estratto: %u %s\n",(*ball)->n,colorVector[(*ball)->c]);
}


