// Realizzare un ADT di prima classe coda con file binari
#include <stdio.h>
#include <stdlib.h>
#include "queue.h"
#include "item.h"


int main(){
    
    Q eventi = initQ();
    Q vacanze = initQ();

    enQ(eventi,7,8,2021);
    enQ(eventi,8,8,2021);

    enQ(vacanze,10,8,2021);
    enQ(vacanze,11,8,2021);
    enQ(vacanze,12,8,2021);

    enQ(eventi,30,8,2021);
    enQ(eventi,31,8,2021);

    printf("%s\n",deQ(eventi));
    printf("%s\n",deQ(eventi));
    printf("%s\n",deQ(eventi));
    printf("%s\n",deQ(eventi));

    printf("---------------\n");

    printf("%s\n",deQ(vacanze));
    printf("%s\n",deQ(vacanze));
    printf("%s\n",deQ(vacanze));

    return 0;
}