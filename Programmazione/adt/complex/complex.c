#include "complex.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// STRUCT
struct complex{
  double m; // modulo
  double f; // fase
};

// FUNCTION
/* * * * newComplex() * * * *
 * alloca un nuovo complex e lo ritorna
 * */
Complex newComplex(double m, double f){
  Complex C = (Complex)malloc(sizeof(struct complex));
  C->m=m;
  C->f=f;
  return C;
}


/* * * * showComplex() * * * *
 * stampa a video i valori di un numero complesso
 * passato come parametro
 * */
void showComplex(Complex C){
  printf("%.1lf+i%.1lf\n",getReal(C),getIm(C));
}


 /* * * * mulComplex() * * * *
  * moltiplica due numeri complessi
  * A = A*B
  * */
void mulComplex(Complex A, Complex B){
  A->m = A->m*B->m;
  A->f = A->f+B->f;
}

/* * * * divComplex() * * * *
 * divide due numeri complessi
 * A = A/B
 * */
void divComplex(Complex A, Complex B){
  A->m = A->m/B->m;
  A->f = A->f-B->f;
}

 /* * * * getModulus() * * * *
  * restituisce il modulo di un numero complesso
  * */
double getModulus(Complex C){
    return C->m;
}

/* * * * getArgument() * * * *
 * restituisce la fase di un numero complesso
 * */
double getArgument(Complex C){
  return C->f;
}


/* * * * getReal() * * * *
 * restituisce la parte reale
 * */
double getReal(Complex C){
  return C->m*cos(C->f);
}

/* * * * getIm() * * * *
 * restituisce la parte immaginaria
 * */
double getIm(Complex C){
  return C->m*sin(C->f);
}


/* * * * addComplex() * * * *
 * somma due numeri complessi
 * A = A+B
 * */
 void addComplex(Complex A, Complex B){
   double rt = getReal(A)+getReal(B);
   double it = getIm(A)+getIm(B);
   A->m = sqrt((rt*rt) + (it*it));
   A->f = atan(it/rt);
 }


 /* * * * subComplex() * * * *
  * sottrae due numeri complessi
  * A = A - B
  * */
void subComplex(Complex A, Complex B){
  double rt = getReal(A)-getReal(B);
  double it = getIm(A)-getIm(B);
  A->m = sqrt((rt*rt) + (it*it));
  A->f = atan(it/rt);
  }


/* * * * nrootsComplex(Complex C, int n) * * * *
 * Ritorna un array di n complessi, ovvero le n radici di C
 * */
Complex *nrootsComplex(Complex C, int n){
  Complex *roots = (Complex *)malloc(n*sizeof(Complex));
  double mod = pow(abs(C->m),1.0/n);
  int k;
  for(k=0;k<n;k++){
    *(roots+k) =(Complex) malloc(sizeof(struct complex));
    (*(roots+k))->m = mod;
    (*(roots+k))->f = (C->f + 2*k*PI)/n;
  }

  return roots;
}