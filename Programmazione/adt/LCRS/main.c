// Definire un ADT che sia un albero LCRS (Left Child Right Sibling)
// implementare una visita in pre-order
#include <stdio.h>
#include <stdlib.h>
#include "lcrs.h"


int main(){

  LCRS myTree = newLCRS();

  addNode(myTree,'1',0);
  addNode(myTree,'a','1');
  addNode(myTree,'b','1');
  addNode(myTree,'c','a');
  addNode(myTree,'#','a');
  addNode(myTree,'d','0'); // il nodo padre non esiste
  addNode(myTree,'#','b'); // valore duplicato
  addNode(myTree,'*','b');

  LCRS debugTree = newLCRS();
  addNode(debugTree,'r',0);
  addNode(debugTree,'e','r');
  addNode(debugTree,'a','e');
  addNode(debugTree,'l','a');

  visitLCRS(myTree);
  visitLCRS(debugTree);

  return 0;
}
