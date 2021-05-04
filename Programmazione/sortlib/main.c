#include <stdio.h>
#include <stdlib.h>
#include "sortlib.h"
#define DIM 15

int compare(double *a, double *b){
    return (*a>*b) - (*a<*b);
}


void main(){
    
    //int v[DIM] = {-5,0,2,-3,6,-1,-7,-2,4,-4,7,5,3,1,-6};
    //void **A =(void **)malloc(DIM*sizeof(int *));
    //for(int k=0;k<DIM;k++) printf("%d|",v[k]);
    //printf("\n");

    double v[DIM] = {6.6,5.5,1.3,-1.0,-2.0,2.0,3.0,7.0,9.0,-3.0,4.1,3.2,5.1,7.6,8.9};
    void **A =(void **)malloc(DIM*sizeof(void *));
    for(int k=0;k<DIM;k++) printf("%.1f|",v[k]);
    printf("\n");

    int i;
    for(i=0;i<DIM;i++)
        A[i] = &v[i];
    
    //selection_sort(A,DIM);
    //bubble_sort(A,DIM);
    insertion_sort(A,DIM);

    /*double S;
    printf("Ricercare valore::");
    scanf("%lf",&S);
    void *ptr = binary_search(A,DIM,(void *)&S);
    (ptr == NULL) ? printf("[] Elemento non trovato\n"):printf("[*] Elemento trovato\n"); */

    for(i=0;i<DIM;i++) printf("%1.f|",*((double*)A[i]));
    printf("\n");

}
