// Sviluppare un ADT Polinomi
#include <stdio.h>
#include <stdlib.h>
#include "polinomi.h"

int main(){

  P primo = newP();
  addTerm(primo,2,2); // 2x²
  addTerm(primo,1,1); // x
  addTerm(primo,3,2); // 3x²
  addTerm(primo,1,3); // x³

  print(primo);
  
  return 0;
}
