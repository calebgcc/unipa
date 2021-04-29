/* Scrivere un valutore di espressioni postfisse
 * utilizzando un ADT Stack basato su liste
 * le espressioni possono contenere le operazione: + - * /
 * */
#include <stdio.h>
#include <string.h>
#include "item.h"
#include "stack.h"

void main(int argc, char *argv[]){

    // CTL - ARGV
    if(argc!=2){
        printf("[*] Errore: ./postfix [espressione] \n");
        return;
    }
    // VAR
    char *es = argv[1]; // espressione
    size_t len = strlen(es); // len espressione
    int i;
    Item op1, op2;

    // VALUTAZIONE - ESPRESSIONE
    stack_init();
    for(i=0;i<len;i++){
        // OPERAZIONI
        if(es[i]=='+')
            stack_push( stack_pop() + stack_pop());
        if(es[i]=='*')
            stack_push( stack_pop() * stack_pop());
        if(es[i]=='-'){
            op2 = stack_pop();
            op1 = stack_pop();
            stack_push(op1 - op2);
        }
        if(es[i]=='/'){
            op2 = stack_pop();
            op1 = stack_pop();
            stack_push(op1 / op2);
        }
            
        // CIFRE
        if((es[i]>='0')&&(es[i]<='9'))
            stack_push(0);
        while((es[i]>='0')&&(es[i]<='9'))
            stack_push(10 * stack_pop() +  es[i++]-'0');
    }
    printf("::%d \n",stack_pop());

}
