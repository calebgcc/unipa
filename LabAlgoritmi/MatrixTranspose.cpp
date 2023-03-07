#include <iostream>
#include <cstdlib>

using namespace std;

#define MAX 100
#define MIN -100


void transpose(int **matrix, int SIZE){
  for(size_t i=0; i<SIZE; ++i)
    for(size_t j=i; j<SIZE; ++j)
      swap(matrix[i][j], matrix[j][i]);
}


void print_matrix(int **matrix, int SIZE){
  for(size_t i=0; i<SIZE; ++i){
    for(size_t j=0; j<SIZE; ++j)
      cout << matrix[i][j] << " ";
    cout << "\n";
  }
}


int main(){

  srand(time(NULL));
  
  int SIZE;

  cout << "Dimensione matrice ::";
  cin >> SIZE;

  int **matrix = new int*[SIZE];
  for(size_t i=0; i<SIZE; ++i)
    matrix[i] = new int[SIZE];
  
  for(size_t i=0; i<SIZE; ++i){
    for(size_t j=0; j<SIZE; ++j){
      matrix[i][j] = rand() % (MAX-MIN+1) + MIN;
    }
  }

  print_matrix(matrix, SIZE);
  transpose(matrix, SIZE);
  cout << "-------------\n";
  print_matrix(matrix, SIZE);
  
  return 0;
}
