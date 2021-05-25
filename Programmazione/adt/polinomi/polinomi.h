// Interface - ADT list
typedef struct polinomi *P;

P newP();
void addTerm(P p, double val, int exp);
void print(P p);
P sumP(P A, P B);
P mulP(P A, P B);
