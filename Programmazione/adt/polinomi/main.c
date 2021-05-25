// Sviluppare un ADT Polinomi
#include <stdio.h>
#include <stdlib.h>
#include "polinomi.h"

int main(){

  P primo = newP();
  addTerm(primo,1,1);
  addTerm(primo,1,0);
  print(primo);

  P secondo = newP();
  addTerm(secondo,2,2);
  addTerm(secondo,1,1);
  addTerm(secondo,2,0);
  print(secondo);

  P res = sumP(primo,secondo);
  print(res);

  P rees = mulP(primo,secondo);
  print(rees);


  return 0;
}
