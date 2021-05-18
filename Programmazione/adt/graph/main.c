// Realizzare un ADT Grafo di prima classe 
// utilizzando liste di adiacenza
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "graph.h"

int main(){


    Graph g = newGraph(4);

    setNode(g,1,2,2);
    setNode(g,2,3,1);
    setNode(g,0,0,0);
    setNode(g,1,-1,3);

    randomGraph(g);

    showLink(g, 0);
    showLink(g, 1);
    showLink(g, 2);
    showLink(g, 3);

    printf("\n");
    
    visitGraph(g, 0);
    visitGraph(g, 1);
    visitGraph(g, 2);
    visitGraph(g, 3);

    return 0;
}
