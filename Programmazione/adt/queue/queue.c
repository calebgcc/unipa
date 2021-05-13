// CODE SOURCE - ADT - QUEUE BIN
#include <stdio.h>
#include <stdlib.h>
#include "queue.h"
#include "item.h"

struct queue{
    FILE *fptr;
    int head;
    int tail;
};

// FUNCTIONS
/* * * * initQ() * * * *
 * ritorna un puntatore a (struct queue) inizializzato;
 * */
Q initQ(){
    Q q = (Q) malloc(sizeof(struct queue));
    q->head = q->tail = 0;
    q->fptr = fopen("prova","wb+");
    printf("%p\n",q);
    return q;
}

/*FILE *fptr = fopen("bella_per_te","wb+");
    Item data = {13,11,2001};
    
    fseek(fptr,4*sizeof(Item),SEEK_SET);
    fwrite(&data,sizeof(Item),1,fptr);

    data.day = 1;
    data.month = 2;
    data.year = 1999;
    fseek(fptr,1*sizeof(Item),SEEK_SET);
    fwrite(&data,sizeof(Item),1,fptr);

    data.day = 3;
    data.month = 7;
    data.year = 1998;
    fseek(fptr,sizeof(Item),SEEK_SET);
    fwrite(&data,sizeof(Item),1,fptr);

    fseek(fptr,0,SEEK_END);
    int dim = ftell(fptr)/sizeof(Item);
    Item vet[dim];

    fseek(fptr,0,SEEK_SET);
    fread(vet,sizeof(Item),dim,fptr);

    for(int i=0;i<dim;i++)
        printf("%d/%d/%d\n",vet[i].day,vet[i].month,vet[i].year);




    fclose(fptr);*/
