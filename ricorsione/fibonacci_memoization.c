#include <stdio.h>
#include <time.h>
#define MAX 50

unsigned long long int f(unsigned int n){
    // le variabili static e le globali sono inizializzate a 0
    static unsigned long long int memo[MAX]; 
    if(n==1||n==0) 
        return 1;
    if(memo[n]==0)
        memo[n] = f(n-1)+f(n-2);
    return memo[n];
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