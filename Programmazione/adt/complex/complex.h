// INTERFACE - ADT - COMPLEX NUMBER

typedef struct complex *Complex;
Complex newComplex(double m, double f);
void showComplex(Complex C);
void mulComplex(Complex A, Complex B);
void divComplex(Complex A, Complex B);
double getModulus(Complex C);
double getArgument(Complex C);
double getReal(Complex C);
double getIm(Complex C);
void addComplex(Complex A, Complex B);
void subComplex(Complex A, Complex B);
