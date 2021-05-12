// Realizzare un ADT di prima classe per
// operare con numeri complessi in forma polare
#include <stdio.h>
#include <stdlib.h>
#include "complex.h"
#define PI 3.14159265

int main(){
  Complex a = newComplex(2,PI/6);
  Complex b = newComplex(3,PI/4);

  addComplex(a,b);
  subComplex(a,b);
  showComplex(a);
  showComplex(b);

  return 0;
}
