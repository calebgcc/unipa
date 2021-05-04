#include "sortlib.h"
#include <stdio.h>
#include <stdlib.h>
// utility

extern int compare();

void swap(void **a,void **b){
    *a = (void *)((long int )*a ^ (long int )*b);
    *b = (void *)((long int )*a ^ (long int )*b);
    *a = (void *)((long int )*a ^ (long int )*b);
}

// sorting

void selection_sort(void **v, unsigned int dim){
    int i,j,min;
    for(i=0;i<dim-1;i++){
        min = i;
        for(j=i+1;j<dim;j++)
            min = (compare(v[j],v[min])==-1) ? j:min;
        if(min!=i) swap(&v[i],&v[min]);
    }
}

void bubble_sort(void **v, unsigned int dim){
    int i,flag;
    do{
        flag = 0;
        for(i=0;i<dim-1;i++)
            if(compare(v[i],v[i+1])==1){
                flag = 1;
                swap(&v[i],&v[i+1]);
            }
        dim--;
    }while(flag);

}

void insertion_sort(void **v,unsigned int dim){
    int i,j;
    void *value;
    for(i=0;i<dim;i++){
        value = v[i];
        for(j=i-1;(j>=0)&&(compare(v[j],value)==1);j--)
            v[j+1] = v[j];
        v[j+1] = value;
    }

}

void *binary_search(void **v,unsigned int dim, void *e){
    bubble_sort(v,dim);
    unsigned int inf=0, sup=dim-1, k=0;

    while(compare(*(v+k),e)&&(sup>inf)){
        k=(inf+sup)/2;
        //compare(*(v+k),e)>0 ? (sup=k-1):(inf=k+1);
        switch(compare(*(v+k),e)){
            case 1:
                sup=--k;
                break;
            case -1:
                inf=++k;
        }
    }
    return compare(*(v+k),e) ? NULL:*(v+k);
}

