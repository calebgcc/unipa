#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 100
#define MIN -100
#define RANGE (MAX-MIN)
#define DIV (RAND_MAX/RANGE)

//#define PRINT

float cubic(float arr[], size_t size){
    float max = 0.0;
    for(size_t L=0; L<size; ++L){
        for(size_t U=L; U<size; ++U){
            double sum = 0.0;
            for(size_t i=L; i<=U; ++i)
                sum += arr[i];
            max = max > sum ? max:sum;
        }
    }

    return max;
}

float quadratic(float arr[], size_t size){
    float max = 0.0;
    for(size_t i=0; i<size; ++i){
        float sum = 0.0;
        for(size_t j=i; j<size; ++j){
            sum += arr[j];
            max = max > sum ? max:sum;
        }
    }
    return max;
}

float prefixsum(float arr[], size_t size){
    float prefix[size];
    prefix[0] = arr[0];
    for(size_t i=1; i<size; ++i)
        prefix[i] = prefix[i-1]+arr[i];

    float sum = 0.0, max = 0.0;
    for(size_t i=0; i<size; ++i){
        for(size_t j=i; j<size; ++j){
            sum = prefix[j]-prefix[i]+arr[i]; 
            max = max > sum ? max:sum;
        }
    }
    return max;
}

float divide(float arr[], int s,int e){
   if(s > e)
    return 0.0;
   if(s == e)
    return arr[s]; 

    int m = ((e-s)/2) + s;
    float max_s=0.0, max_e=0.0, sum=0.0;
    for(int i=m; i>=s; --i){
        sum+=arr[i];
        max_s = max_s > sum ? max_s:sum;
    }
    sum = 0.0;
    for(int i=m+1; i<=e; ++i){
        sum+=arr[i];
        max_e = max_e > sum ? max_e:sum;
    }
    float max = max_e + max_s;


    max_s = divide(arr, s, m);
    max_e = divide(arr, m+1, e);
    
    max = max > max_s ? max:max_s;
    return max > max_e ? max:max_e;
}

float linear(float arr[], size_t size){
    float global_max = 0.0;
    float local_max = 0.0;
    for(size_t i=0; i<size; ++i){
        local_max += local_max+arr[i] > 0.0 ? arr[i]:-local_max;
        global_max = global_max > local_max ? global_max:local_max;
    }
    return global_max;
}

int main(){
    srand(time(NULL));

    int DIM[] = {100, 1000, 2000, 5000};
    int number_of_tests = (sizeof(DIM)/sizeof(int));
    for(int d=0; d<number_of_tests; ++d){

        float arr[DIM[d]];

        for(int i=0; i<DIM[d]; ++i){
            arr[i] = MIN + (((float) rand())/DIV);
            #ifdef PRINT
            printf("%.2f ", arr[i]);
            #endif
        }
        printf("\n");
        printf("Array size %d\n", DIM[d]);
        clock_t t;
        float max, time;
        
        t = clock();
        max = linear(arr, DIM[d]); 
        t = clock() - t;
        time = ((float) t)/CLOCKS_PER_SEC;
        printf("(linear) MAX :: %.2f -- TIME :: %f\n", max, time);

        t = clock();
        max = divide(arr, 0, DIM[d]-1); 
        t = clock() - t;
        time = ((float) t)/CLOCKS_PER_SEC;
        printf("(divide and conquer) MAX :: %.2f -- TIME :: %f\n", max, time);

        t = clock();
        max = quadratic(arr, DIM[d]); 
        t = clock() - t;
        time = ((float) t)/CLOCKS_PER_SEC;
        printf("(quadratic sum) MAX :: %.2f -- TIME :: %f\n", max, time);

        t = clock();
        max = prefixsum(arr, DIM[d]); 
        t = clock() - t;
        time = ((float) t)/CLOCKS_PER_SEC;
        printf("(prefixsum sum) MAX :: %.2f -- TIME :: %f\n", max, time);

        t = clock();
        max = cubic(arr, DIM[d]); 
        t = clock() - t;
        time = ((float) t)/CLOCKS_PER_SEC;
        printf("(cubic sum) MAX :: %.2f -- TIME :: %f\n", max, time);


        printf("--------------------\n");
    }

    return 0;
}