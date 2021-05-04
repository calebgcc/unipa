#include <stdio.h>
#include <time.h>

unsigned long long int f(unsigned int n){
    if(n==1||n==0) 
        return 1;
    else 
        return f(n-1)+f(n-2);
}

void main(){
    unsigned int n;
    printf("::");
    scanf("%d",&n);

    time_t start = time(NULL);
    unsigned long long int result = f(n);
    time_t end = time(NULL);

    printf("FIB(%u)::%llu || time::%f\n",n,result,difftime(end,start));
}