#include <stdio.h>

unsigned long long int f(unsigned int n){
    int i;
    unsigned long long int fat = 1;
    for(i=n;i>1;i--)
        fat*=i;
    return fat;
}

void main(){
    unsigned int i;
    for(i=0;i<=21;i++)
        printf("%u!::%llu\n",i,f(i));
}
