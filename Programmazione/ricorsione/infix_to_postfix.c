/*  Traduttore da notazione infissa a notazione postfissa ricorsivo
 *  con stack implementato attraverso una stringa
 * */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void toPostfix(char *postfix,char *infix);

int main(int argc, char *argv[]){

    char postfix[50]; 
    strcat(argv[1],")");
    toPostfix(postfix,argv[1]);
    printf("%s\n",postfix);

    return 0;
}

void toPostfix(char *postfix,char *infix){
    // CASO BASE
    if(*infix == 0){
        *postfix = 0;
        return;
    }
   // STACK DEGLI OPERATORI
   static char stack_vector[50]="$(";
   static char *stack = &(stack_vector[1]);

   if(*infix == '('){
       stack+=1;
       *stack = '(';
   }
   else if((*infix >= '0')&&(*infix <= '9')){
        *postfix = *infix; 
        postfix += 1;
   }
   else if((*infix == '+')||(*infix == '-')){
       *postfix = ' ';
       postfix += 1;
       while((*stack=='+')||(*stack == '-')||(*stack == '*')||(*stack == '/')){
           *postfix = *stack;
           postfix += 1;
           stack -= 1;
           *postfix = ' ';
           postfix += 1;
       }
       stack += 1;
       *stack = *infix;
   }
   else if((*infix == '*')||(*infix == '/')){
       *postfix = ' ';
       postfix += 1;
        while((*stack == '*')||(*stack == '/')){
           *postfix = *stack;
           postfix += 1;
           stack -= 1;
           *postfix = ' ';
           postfix += 1;
       }
       stack += 1;
       *stack = *infix;
   }
   else if(*infix == ')'){
       *postfix = ' ';
       postfix += 1;
        while((*stack=='+')||(*stack == '-')||(*stack == '*')||(*stack == '/')){
           *postfix = *stack;
           postfix += 1;
           stack -= 1;
           *postfix = ' ';
           postfix += 1;
       }
       stack -= 1;
   }
   toPostfix(postfix,infix+1); 
}
