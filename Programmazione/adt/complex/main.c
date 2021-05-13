// Realizzare un ADT di prima classe per
// operare con numeri complessi in forma polare
#include <stdio.h>
#include <stdlib.h>
#include "complex.h"

int main(){
  Complex a = newComplex(8,0);
  Complex b = newComplex(3,PI/4);

  showComplex(a);

  //addComplex(a,b);
  //subComplex(a,b);

  //mulComplex(a,b);
  //divComplex(a,b);

  Complex *radici = nrootsComplex(a,3);
  int i;
  for(i=0;i<3;i++) showComplex(radici[i]);


  return 0;
}
