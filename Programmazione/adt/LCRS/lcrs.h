// INTERFACE - ADT di prima classe - LCRS Tree
typedef struct lcrs *LCRS;

LCRS newLCRS();
void addNode(LCRS tree, char dChild, char dFather);
void visitLCRS(LCRS tree);
