#include <stdio.h>

size_t minSearch(const int *vet, size_t index,int min, int dim){
    if(dim==-1)
        return index;
    if(*(vet+dim)<min)
        return minSearch(vet,dim,*(vet+dim),dim-1);
    return minSearch(vet,index,min,dim-1); 
}

void main(){
    int vet[] = {2,4,6,10,16,-1,26,42,68,-3,110,178,1};
    int size = sizeof(vet)/sizeof(int);
    
    printf("[*] Min at index [%ld]\n",minSearch(vet,0,vet[0],size-1));
}