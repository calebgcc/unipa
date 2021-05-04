#include <stdio.h>
#include <time.h>

/*
 MCD(m,n):
    m = nt + r
    n = rv + r1
    ....
    ...
*/

int MCD(int m, int n){
    if(n==0)
        return m;
    return MCD(n,m%n);
}

void main(){
    int m,n;
    printf("Inserire m,n::");
    scanf("%d %d,",&m,&n);

    time_t start = time(NULL);
    int result = MCD(m,n);
    time_t end = time(NULL);

    printf("MCD(%d,%d)::%d -- time::%f\n",m,n,result,difftime(end,start));
}