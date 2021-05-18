// Source Code - ADT - Graph
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "graph.h"


// STRUCT
struct link{
    size_t v;
    struct link *li;
};
typedef struct link Link;

struct node{
    float x;
    float y;
    int N;
    Link *list;
};
typedef struct node Node; 

struct graph{
    Node *vet;
    int N;
};

//PROTOTYPES
static void visit(Node *vet, size_t i, char *memo, size_t N,size_t set);
static void addLink(Link **head, size_t index);
static char areLinked(Link *head, size_t index);
static int realRandom(int max, int min, int diff);
static Link *newLink(size_t v);



//FUNCTIONS

/* * * * newGraph() * * * *
 * Ritorna un grafo inizializzato per avere nNode
 * */
Graph newGraph(int nNode){
    if(nNode<4) return NULL;
    Graph g =(Graph) malloc(sizeof(struct graph));
    g->vet =(Node *) malloc(nNode*sizeof(Node));
    g->N = nNode;
    return g;
}

/* * * * newLink() * * * *
 * ritorna un Link inizializzato con indice a v
 * */
static Link *newLink(size_t v){
    Link *link =(Link *) malloc(sizeof(Link));
    link->v = v;
    link->li = NULL;
    return link;
}


 /* * * * setNode() * * * *
 * imposta le coordinate x e y del nodo i-esimo
 * */
 void setNode(Graph g, float x, float y, size_t i){
     g->vet[i].x = x;
     g->vet[i].y = y;
     g->vet[i].N = 0;
     return;
 }


 /* * * * randomGraph() * * * *
 * crea connessioni random tra i nodi dei grafi
 * */
void randomGraph(Graph g){
    int i, j, nLink, linkTo;
    srand(time(NULL));
    for(i=0;i<g->N;i++){
        nLink = realRandom(((int)(g->N*3)/10),1,0); // quanti link avrà il nodo i? (30% dei nodi totali al massimo)
        for(j=0;j<nLink;j++){
            linkTo = realRandom(g->N-1,0,i); // il nodo di indice 'i' lo colleghiamo al nodo di indice 'linkTo'
            if(areLinked(g->vet[i].list,linkTo)) continue; // se son già collegati andiamo avanti
            addLink(&(g->vet[i].list), linkTo);
            addLink(&(g->vet[linkTo].list), i);
            g->vet[i].N++;
            g->vet[linkTo].N++;
        }
    }
}

/* * * * realRandom() * * * *
 * numero casuale differente da 'diff'
 * compreso tra 'max' e 'min'
 * */
static int realRandom(int max, int min, int diff){
    int flag = diff;
    while(flag==diff)
        flag = (random()%(max-min+1))+min;
    return flag;
 }


 /* * * * areLinked() * * * *
 * ricerca un indice in una lista di connessioni
 * ritorna 1 se lo trova 0 altrimenti
 * */
static char areLinked(Link *head, size_t index){
    while(head!=NULL){
        if(head->v==index) return 1;
        head = head->li;
    }
    return 0;
}

 /* * * * addLink() * * * *
 * aggiunge un collegamento in testa
 * */
static void addLink(Link **head, size_t index){
    if(*head == NULL){
        *head = newLink(index);
        return;
    }
    Link *nd = newLink(index);
    nd->li = *head;
    *head = nd;
    return;
}

 /* * * * visitGraph() * * * *
 * interfaccia per l'utente
 * visita un grafo in pre ordine partendo dal nodo i-esimo
 * */
void visitGraph(Graph g, size_t i){
    char *memo = (char *) calloc(g->N,sizeof(char));
    visit(g->vet,i,memo, g->N,0);
    printf("\n");
}


 /* * * * visit() * * * *
 * visita un grafo in pre ordine partendo dal nodo i-esimo
 * */
static void visit(Node *vet, size_t i, char *memo, size_t N,size_t set){
    if(set==N) return;
    printf("(%.1f,%.1f)-+-",vet[i].x,vet[i].y);
    memo[i]++; set++;
    Link *tmp = vet[i].list;
    while(tmp!=NULL){
        if(!memo[tmp->v]) visit(vet,tmp->v, memo, N, set);
        tmp = tmp->li;
    }
    return;
}


/* * * * showLink() * * * *
 * Mostra i nodi con cui un nodo i-esimo è collegato
 * */
void showLink(Graph g, size_t i){
    Link *tmp = g->vet[i].list;
    printf("[*] Il nodo (%.1f,%.1f) è collegato a: ",g->vet[i].x, g->vet[i].y);
    while(tmp != NULL){
        i = tmp->v;
        printf("(%.1f,%.1f) ",g->vet[i].x, g->vet[i].y);
        tmp = tmp->li;
    }
    printf("\n");
}
