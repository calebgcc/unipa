/* MergeSort ottimizzato
 * Per array di dimensione M viene utilizzato InsertionSort
 * */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define M 30// gli array di 30 elementi saranno ordinati da selection sort

typedef unsigned long long int ull;

void insertionSort(ull *vet,ull start, ull end,char (*compare)(ull a,ull b));
void mergeSort(ull *vet, ull dim,char (*compare)(ull a,ull b));
void subSort(ull *vet,ull start, ull end,char (*compare)(ull a,ull b));
void merge(ull*vet, ull start, ull mid1, ull mid2, ull end,char (*compare)(ull a,ull b));
char ascending(ull a,ull b);
char descending(ull a, ull b);

void main(){

    // V A R I A B I L I
    ull N;
    int pref; // numero di elementi | preferenza crescente/decrescente
    char (*menu[])(ull a,ull b)={ascending,descending}; // funzioni menu
    char output; // stampre l'array ordinato si/no

    // M E N Ù
    printf("[] Inserire il numero di elementi::");
    scanf("%llu",&N);
    printf("0. Crescente\n1. Decrescente\n");
    printf("Preferenza::");
    scanf("%d",&pref);
    printf("[] Stampare l'array ordinato? [s/n]::");
    scanf(" %c",&output);

    // G E N E R A Z I O N E _ A R R A Y
    srand(time(NULL));
    ull vet[N], i;
    for(i=0;i<N;i++)
        vet[i] = rand() % N+1;

    // P R I N T _ A R R A Y
    if(output=='s'){
        for(i=0;i<N;i++)
            printf("%llu|",vet[i]);
        printf("\n");
    }
    
    // O R D I N A M E N T O
    struct timespec start,end;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&start);
    mergeSort(vet,N,menu[pref]);
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID,&end);
   
    // P R I N T _ A R R A Y
    if(output=='s'){
        for(i=0;i<N;i++)
            printf("%llu|",vet[i]);
        printf("\n");
    }

    // S T A T I S T I C H E
    printf("\n ******************* \n");
    printf("[-] Dimensione Array::%llu\n",N);
    printf("[-] Usato InsertionSort quando N=%d\n",M);
    printf("[-] Tempo per riordinare::%ld\n",(end.tv_sec*1000000 + end.tv_nsec/1000)-(start.tv_sec*1000000 + start.tv_nsec/1000));
    printf(" ******************* \n");

}

void insertionSort(ull *vet,ull start, ull end,char (*compare)(ull a,ull b)){
    ull i,value; 
    long long int j;

    for(i=start+1;i<=end;i++){
        value = vet[i];
        for(j=i-1;(j>=(long long int)start)&&(*compare)(value,vet[j]);j--){
            vet[j+1] = vet[j];
        }

        vet[j+1] = value;
    }
}

void mergeSort(ull *vet, ull dim,char (*compare)(ull a,ull b)){
    subSort(vet,0,dim-1,compare);
}

void subSort(ull *vet,ull start, ull end,char (*compare)(ull a,ull b)){
    if((end-start)>=M){ // end-start = 0 => end=start => elemento singolo
        ull mid = (start+end)/2;

        subSort(vet,start,mid,compare);
        subSort(vet,mid+1,end,compare);

        merge(vet, start,mid,mid+1,end,compare);
    }
    else
        insertionSort(vet,start,end,compare);

}

void merge(ull *vet, ull start, ull mid1, ull mid2, ull end,char (*compare)(ull a,ull b)){
    
    ull *tmpArray = (ull *)calloc(end-start+1,sizeof(ull));
    
    ull i=start, j=mid2,c=0;
    while(i<=mid1 && j<=end){
        if((*compare)(vet[i],vet[j])){
            tmpArray[c++] = vet[i++];
        }
        else{
            tmpArray[c++] = vet[j++];
        }
    }
    
    if(i==mid2){
        while(j<=end)
          tmpArray[c++] = vet[j++];  
    }
    else{
        while(i<=mid1)
          tmpArray[c++] = vet[i++];
    }

    c=0;
    for(i=start;i<=end;i++)
        vet[i] = tmpArray[c++];
    
}

char ascending(ull a,ull b){
    return a<b;
}

char descending(ull a, ull b){
    return a>b;
}
