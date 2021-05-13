// Realizzare un ADT di prima classe coda con file binari
#include <stdio.h>
#include <stdlib.h>
#include "queue.h"
#include "item.h"


int main(){
    
    Q myQ = initQ();
    printf("%p\n",myQ);

    return 0;
}