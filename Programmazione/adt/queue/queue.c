// CODE SOURCE - ADT - QUEUE BIN
#include <stdio.h>
#include <stdlib.h>
#include "queue.h"
#include "item.h"

struct queue{
    FILE *fptr;
    int head;
    int tail;
    int N;
    char nameFile[20];
};

// FUNCTIONS
/* * * * initQ() * * * *
 * ritorna un puntatore a (struct queue) inizializzato;
 * */
Q initQ(){
    Q q = (Q) malloc(sizeof(struct queue));
    q->N = q->head = q->tail = 0;
    sprintf(q->nameFile,"%p.bin",q);
    q->fptr = fopen(q->nameFile,"wb+");
    fclose(q->fptr);
    return q;
}

/* * * * isEmpty() * * * *
 * ritorna 1 se la coda è vuota, 0 altrimenti
 * */
int isEmpty(Q q){
    return (q->N==0);
}

/* * * * enQ() * * * *
 * Inserisce una data in Coda
 * */
void enQ(Q q, int day,int month,int year){
    q->fptr = fopen(q->nameFile,"ab");
    Item data = {day,month,year};
    fseek(q->fptr,q->tail*sizeof(Item),SEEK_SET);
    fwrite(&data,sizeof(Item),1,q->fptr);
    q->tail++;
    q->N++;
    fclose(q->fptr);
}

/* * * * deQ() * * * *
 * Ritorna una data dalla testa della coda
 * sotto forma di stringa
 * */
char *deQ(Q q){
    if(isEmpty(q))
        return NULL;
    Item data;
    q->fptr = fopen(q->nameFile,"rb");
    fseek(q->fptr,q->head*sizeof(Item),SEEK_SET);
    fread(&data,sizeof(Item),1,q->fptr);
    q->N--;
    q->head++;
    char *s =(char *) malloc(20*sizeof(char)); // 00/00/0000 // standard di 10 caratteri // 20 per sicurezza
    fclose(q->fptr);
    sprintf(s,"%d/%d/%d",data.day,data.month,data.year);
    return s;
}
