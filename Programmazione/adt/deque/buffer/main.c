/* Realizzare un coda come ADT
 * utilizzando una lista doppiamente concatenata
 * affinchè si possa accedere sia dalla testa che della coda
 * */
#include <stdio.h>
#include <stdlib.h>
#include "item.h"
#include "deque.h"

// MAIN
void main(){
    // VAR 
    int scelta;

    // INIT
    deque_init();

    do{
        printf("1: Aggiungi in Coda\n2: Aggiungi in Testa\n3: Rimuovi in Coda\n4: Rimuovi in testa\n? ");
        scanf("%d",&scelta);
        switch(scelta){
            case 1:
                printf("::");
                scanf("%d",&scelta);
                en_tail(scelta);
            break;
            case 2:
                printf("::");
                scanf("%d",&scelta);
                en_top(scelta);
            break;
            case 3:
                printf(":: %d\n",de_tail());
            break;
            case 4:
                printf(":: %d\n",de_top());
            break;
        }
    }while(1);
}