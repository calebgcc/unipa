#include <stdio.h>
#include "gridlib.h"

void full_grid(int n){
    unsigned int i;
    for(i=1;i<=(n*n);i++)
        (i%n) ? printf("%d ",i):printf("%d\n",i);
}

void diagonale(int n){
    unsigned int i,k;
    for(i=0;i<n;i++){
        for(k=1;k<=i;k++)
            printf("-");
        printf("%d\n",k);
    }
}

void scala(int n){
    unsigned int i,k,count=1;
    for(i=0;i<=n;i++){
        for(k=1;k<=i;k++)
            printf("%d ",count++);
        printf("\n");
    }
}

