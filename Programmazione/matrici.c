// Questo file elenca diverse alternative per creare e lavorare con matrici in C
#include <stdio.h>
#include <stdlib.h>

void print_s(int *m){
    // printf("%d ",m[1][2]); non puoi usarlo
    // printf("%d ",*(*(m+1)+2)); non puoi usarlo
    printf("%d ",m[1*3+2]);
    printf("%d ",*(m+1*3+2)); // equivalente a *(m+5)
}

void print_p(int **m){
    // printf("%d ", *(*(m+1)+2)); non funziona
    printf("%d ",*(*m+1*3+2));
    // printf("%d ",m[1][2]); non funziona
    printf("%d ",**m+5); // questo equivale a matrix[0][0] + 5 // print 1+5=6
}

void print_d(int **m){
    printf("%d ",*(*(m+3)+2)); // equivalente a dmatrix[3][2];
    printf("%d ",m[3][2]);
    // printf("%d ",*(*m+3*3+2)); non funziona
    // printf("%d ",*(m+3*3+2)); non funziona
}

int main(){

    // Bidimensionali semplici
    int matrix[4][3] = {{1,3,5},{4,6,8},{10,20,30},{0,7,9}};
    printf("------\n");
    printf("%d ",matrix[1][2]); // print 8
    printf("%d ",*(*(matrix+1)+2)); // print 8
    printf("%d ",(*matrix)[1*3+2]);// [i*N+j] N numero di colonne // print 8
    printf("\n------\n");

    print_s((int *)matrix); // che equivale a &(matrix[0][0])
    printf("\n------\n");
    
    int *m = &(matrix[0][0]);
    print_p(&m);
    printf("\n------\n");

    // Dato un vettore tridimensionale la formula sarebbe:
    // (&B[0][0][0])[i*M*N+j*N+k] = B[i][j][k]

    // Bidimensionali dinamici
    printf(" -- Dinamici -- \n");
    int **dmatrix =(int **) malloc(4*sizeof(int *)); //4 righe,ovvero 4 vettori (int *)
    for(int i=0;i<4;i++){
        dmatrix[i] = (int *) malloc(3*sizeof(int)); // 3 colonne
        for(int j=0;j<3;j++){
            dmatrix[i][j] = (i+1)*(j+1);
            printf("%d ",dmatrix[i][j]);
        }
        printf("\n");
    }

    printf("----\n");
    printf("%d ",dmatrix[3][2]); // print 12
    printf("%d ",(*dmatrix)[3*3+2]); // numero a caso | [i*N+j] non funziona
    printf("%d ",*(*(dmatrix+3)+2)); // print 12
    
    printf("\n----\n");
    print_d(dmatrix);
    printf("\n----\n");
    
    return 0;
}
