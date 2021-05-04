#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a; 
    *a = *b; 
    *b = temp;  
} 
  
size_t findMin(int *vet, size_t dim){
    if(dim==1) return 0;
    int min = vet[0];
    size_t mp = 1 + findMin(&vet[1],dim-1);
    if(vet[mp]<min) return mp;
    return 0;
}

void count(int *vet, int i,int n){
    if(i>n)
        return;
    int min = findMin(&vet[i],n-i+1)+i; 
    swap(&vet[min], &vet[i]);
    return count(vet,i+1,n);
}

void selectionSort(int *vet, int n) { 
    int i,min; 
    count(vet,0,n);
} 

void main(){
    int vet[] = {11,39,2,1,18,3,7,4,-1};
    size_t dim = sizeof(vet)/sizeof(vet[0]);

    selectionSort(vet,dim);

    int i;
    for(i=0;i<dim;i++)
        printf("%d|",vet[i]);
    printf("\n");
}