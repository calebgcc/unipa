#include <stdio.h>

unsigned long long int f(unsigned int n){
    if(n<=1) 
        return 1;
    else 
        return n*f(n-1);
}

void main(){
    unsigned int i;
    for(i=0;i<=21;i++)
        printf("%u!::%llu\n",i,f(i));
}