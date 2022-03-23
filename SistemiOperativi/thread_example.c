#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int A[3][3]= {{1,0,0},
               {0,0,1},
               {0,1,0}};

int B[3][3] = {{1,2,3},
               {2,1,4},
               {3,4,1}};

int C[3][3]= {{0,0,0},
               {0,0,0},
               {0,0,0}};

int sum = 0;
// we want to compute A*B putting the result in C

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;


void* matrix_product(void* row){
    int r = (int) row;
    for(int i=0; i<3; ++i){
        C[r][i] = A[r][0]*B[0][i]+A[r][1]*B[1][i]+A[r][2]*B[2][i];
        pthread_mutex_lock(&mutex);
        sum += C[r][i];
        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]){
    pthread_t tid[3]; // thread identifier

    // pthread_attr_t attr; //set of thread attributes
    // set the default attributes of the thread
    //pthread_attr_init(&attr);

    for(int i=0;i<3;++i){
        // create the thread
        pthread_create(&tid[i],NULL, matrix_product, i);
    }

    for(int i=0;i<3;++i){    
        // wait for the thread to exit
        pthread_join(tid[i],NULL);
    }

    for(int i=0; i<3; ++i)
        printf("%d %d %d\n",C[i][0],C[i][1],C[i][2]);
    
    printf("The sum is :: %d\n",sum);

    return 0;
}

