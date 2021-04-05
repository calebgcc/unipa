#include <stdio.h>
#include <time.h>
#define NDEBUG // se attivo gli assert non vengono presi in considerazione
#include <assert.h> // libreria per assert();
#include <limits.h> // libreria dei limiti di ogni variabile

typedef unsigned long long int ull;

void main(){
    unsigned int n,i;
    ull a=0,b=1,temp;

    printf("::");
    scanf("%d",&n);

    time_t start = time(NULL);
    for(i=1;i<=n;i++){
        temp=a;
        a=b;
        b=temp+b;
        /*
            Se b non dovesse essere minore del massimo numero
            che ULL può rappresentare diviso 2 allora
            darà errore; (poicè al prossimo ciclo avrei un overflow)
        */
        assert(b <(ULLONG_MAX/2));
    }
    time_t end = time(NULL);

    printf("FIB(%u)::%llu || time::%f\n",n,b,difftime(end,start));
}