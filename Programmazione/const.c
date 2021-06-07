/* Diversi modi di utilizzare lo specificatore 'const'
 * */
#include <stdio.h>
#include <stdlib.h>

// puntatore non costante a valori non costanti
void toUpper(char *str){
    for(;*str;*str -= (*str >= 'a' && *str <= 'z') ? ' ':0,str++);
}

// puntatore non costante a dati costanti
void printInverse(const char *str){
    if(*str == 0) return;
    printInverse(str+1);
    printf("%c",*str);
    // *str = 'x'; => errore di compilazione
}

// puntatore costante a dati non costanti
void addSeven(int * const a){
    *a += 7;
    // a++; => errore di compilazione
}


int main(){

    char str[] = "ciao a tutti";
    int a = 73;

    addSeven(&a);
    printf("%d\n",a);

    printInverse(str);
    printf("\n");

    toUpper(str);
    printf("%s\n",str);

    return 0;
}
