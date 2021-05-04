#include <stdio.h>
#include "gridlib.h"

void main(){

    unsigned int n;

    printf("N::");
    scanf("%d",&n);

    scala(n);
    printf("\n");
    diagonale(n);
    printf("\n");
    full_grid(n);
}