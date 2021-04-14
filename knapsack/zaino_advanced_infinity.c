#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM_OBJ 8
#define MAX_WEIGHT 25



// S T R U C T
struct obj{
    int value;
    int weight;
};

typedef struct obj Obj;


// U T I L I T Y
int max(int a,int b,int c){
    if(a>b){
        return (a>c) ? a:c;
    }
    return (b>c) ? b:c;
}

// M A I N
void main(){

    // V A R
    Obj shop[NUM_OBJ];
    int i,j;
    
    // I N I T - S H O P
    srand(time(NULL));
    for(i=0;i<NUM_OBJ;i++){
        shop[i].value = (rand() % 100)+1;
        shop[i].weight = (rand() % 15)+1;
    }

    // P R I N T - S H O P
    for(i=0;i<NUM_OBJ;i++){
        printf("[ %d$ %dKg ] ",shop[i].value, shop[i].weight);
        (i+1)%4 ? 0:printf("\n");
    }
    printf("\n"); 

    // B E S T - C H O I C E
    int knapsack[NUM_OBJ][MAX_WEIGHT+1];
    int multicopie, cp, singolacopia;
    for(i=0;i<NUM_OBJ;i++){
        for(j=0;j<=MAX_WEIGHT;j++){
            // caso - base
            if(i==0)
                knapsack[i][j] = (shop[i].weight > j) ? 0:(shop[i].value*(j/shop[i].weight));
            else{
                if(shop[i].weight>j)
                    knapsack[i][j] = knapsack[i-1][j];
                else{
                    // prima calcolo il caso in cui prendo multiocopie dell'elemento corrente
                    // se rimane spazio aggiungo anche j-peso della riga precedente
                    cp = j/shop[i].weight; // qunte cp (copie) posso prendere?
                    multicopie = shop[i].value*cp;
                    multicopie += (j-(shop[i].weight*cp))==0 ? 0:knapsack[i-1][j-(shop[i].weight*cp)];
                    // adesso prendo solo una copia dell'elemento corrente e aggiungo valore della rica precedente j-peso
                    singolacopia = shop[i].value+knapsack[i-1][j-shop[i].weight];
                    // massimo tra multicopie, singolacopia e knapsack[i-1][j] (massimo raggiunto prima)
                    knapsack[i][j] = max(knapsack[i-1][j],multicopie,singolacopia);
                }
            }
        }
    } 

    printf("MAX_WEIGHT::%d\n",MAX_WEIGHT);
    printf("BEST_VALUE::%d\n",knapsack[NUM_OBJ-1][MAX_WEIGHT]);

}

