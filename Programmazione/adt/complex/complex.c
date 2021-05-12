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
  printf("%.1lf[cos(%.1lf) + isen(%.1lf)]\n",C->m,C->f,C->f);
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
   A->f = atan(rt/it);
 }


 /* * * * subComplex() * * * *
  * sottrae due numeri complessi
  * A = A - B
  * */
void subComplex(Complex A, Complex B){
  double rt = getReal(A)-getReal(B);
  double it = getIm(A)-getIm(B);
  A->m = sqrt((rt*rt) + (it*it));
  A->f = atan(rt/it);
  }
