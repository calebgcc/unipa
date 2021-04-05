#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define NUM_OBJ 10
#define MAX_WEIGHT 90


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
    int i;
    
    // I N I T - S H O P
    srand(time(NULL));
    for(i=0;i<NUM_OBJ;i++){
        shop[i].value = (rand() % 1000)+1;
        shop[i].weight = (rand() % 15)+1;
    }

    // P R I N T - S H O P
    for(i=0;i<NUM_OBJ;i++){
        printf("[ %d$ %dKg ] ",shop[i].value, shop[i].weight);
        (i+1)%4 ? 0:printf("\n");
    }
    printf("\n"); 

}