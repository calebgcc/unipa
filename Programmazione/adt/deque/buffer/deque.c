// FUNZIONI - DEQUE - ADT
#include <stdio.h>
#include <stdlib.h>
#include "item.h"
#include "deque.h"

// VAR
Item *vet; // vettore
int dim = 4; // dimensione variabile
int N = 0; // numeri di elementi presenti
int head,tail; // indici di coda/testa

// PROTOTIPI STATIC
static void resize(int toN);

void debug(){
	printf("*** DIM=%d - HEAD=%d - TAIL =%d *** \n",dim,head,tail);
}

/* * * * deque_init() * * * *
 * inizializza l'ADT
 * */
void deque_init(){
    vet = (Item *) malloc(dim*sizeof(Item));
    head = tail = 0;
}

/* * * * en_tail() * * * *
 * prende in input un valore
 * aggiunge il valore in coda
 * */
void en_tail(Item i){
	if(N>=dim){
		resize(dim*2);
		dim = dim*2;
	}
	vet[tail]=i;
	tail=(tail+1)%dim;
	N++;
}

/* * * * en_top() * * * *
 * prende in input un valore
 * aggiugne il valore in testa
 * */
void en_top(Item i){
	if(N>=dim){
		resize(dim*2);
		dim = dim*2;
	}
	head = abs((dim + head-1)%dim);
	vet[head]= i;
	N++; 
}

/* * * * de_tail() * * * *
 * ritorna il valore dell'ultimo nodo in coda
 * libera il nodo dalla deque
 * */
Item de_tail(){
	if((dim>4)&&(N<(dim/8))){
		resize(dim/2);
		dim = dim/2;
	}
	if(N==0){
		return NULL_ITEM;
	} 
	tail = abs((tail-1)%dim);
	Item i = vet[tail];
	N--;
	return i;
}

/* * * * de_top() * * * *
 * ritorna il valore del primo nodo in coda
 * libera il nodo dalla deque
 * */
 Item de_top(){
 	if((dim>4)&&(N<(dim/8))){
		resize(dim/2);
		dim = dim/2;
	}
	if(N==0){
		return NULL_ITEM;
	}
	Item i = vet[head];
	head = (head+1)%dim;
	N--;
	return i;
 }


/* * * * resize() * * * *
 * resize vet ad una fissata dimensione con copia esplicita
 */
static void resize(int toN){
	Item *temp =(Item *) malloc(toN*sizeof(Item));
	int i,j;
	for(i=head,j=0;j<N;i=(i+1)%dim,j++)
		temp[j] = vet[i];
	free(vet);
	vet = temp;
	head = 0;
	tail = N;
}







