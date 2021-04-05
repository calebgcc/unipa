#include <stdio.h>

int binarySearch(const int *vet, int key, size_t l , size_t h){
    if(l>h)
        return -1; // not found

    int mid = (h+l)/2;
    if(*(vet+mid)==key)
        return mid;
    else if(*(vet+mid)>key)
        return binarySearch(vet,key,l,mid-1);
    return binarySearch(vet,key,mid+1,h);
}

void main(){
    int vet[] = {2,4,6,10,16,26,42,68,110,178};
    int size = sizeof(vet)/sizeof(int);
    
    int key;
    printf("KEY::");
    scanf("%d",&key);
    
    printf("[*] Key -%d- at index -%d-\n",key,binarySearch(vet,key,0,size-1));
}