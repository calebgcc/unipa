// INTERFACE - ADT Graph
typedef struct graph *Graph;

Graph newGraph(int nNode);
void setNode(Graph g, float x, float y, size_t i);
void randomGraph(Graph g);
void visitGraph(Graph g, size_t i);
void showLink(Graph g, size_t i);