// Interface - ADT list
typedef struct polinomi *P;

P newP();
void addTerm(P p, double val, int exp);
void print(P p);
