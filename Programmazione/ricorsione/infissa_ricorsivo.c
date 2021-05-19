// Realiazzare un valutatore di espressioni scritte in notazione infissa
// utilizzare una funzione ricorsiva
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int eval(char *str, int val);
int main(int argc, char *argv[]){

  if(argc != 2){
    printf("[!] Errore\n\t usare: ./run \"espressione\"\n");
    return -1;
  }

  char copia[50];
  strcpy(copia,argv[1]);
  printf("\t%s = %d \n",argv[1],eval(copia, 0));

}

int eval(char *str, int val){
  if(*str=='+'){
    *str = ' ';
    return  eval(str+1, 0)+ eval(str+1, 0);
  }
  else if(*str=='*'){
    *str = ' ';
    return eval(str+1, 0) * eval(str+1, 0);
  }
  else if(*str=='-'){
    *str = ' ';
    return eval(str+1, 0) - eval(str+1, 0);
  }
  else if(*str=='/'){
    *str = ' ';
    return eval(str+1, 0) / eval(str+1, 0);
  }
  else if(*str>='0' && *str<='9'){
    val = (val*10)+(*str-'0');
    *str = ' ';
    return eval(str+1, val);
  }
  if(*str==' ' || *str==0)
    return (val==0) ? eval(str+1,val):val;

}
