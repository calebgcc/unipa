#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM_OBJ 8
#define MAX_WEIGHT 25
#define max(a,b) ((a)>(b) ? (a) : (b) )



// S T R U C T
struct obj{
    int value;
    int weight;
};

typedef struct obj Obj;


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
    for(i=0;i<NUM_OBJ;i++){
        for(j=0;j<=MAX_WEIGHT;j++){
            // caso - base
            if(i==0)
                knapsack[i][j] = (shop[i].weight > j) ? 0:shop[i].value;
            else{
                if(shop[i].weight>j)
                    knapsack[i][j] = knapsack[i-1][j];
                else{
                    knapsack[i][j] = max(knapsack[i-1][j],shop[i].value+knapsack[i-1][j-shop[i].weight]);
                }
            }
        }
    } 

    printf("MAX_WEIGHT::%d\n",MAX_WEIGHT);
    printf("BEST_VALUE::%d\n",knapsack[NUM_OBJ-1][MAX_WEIGHT]);

}

