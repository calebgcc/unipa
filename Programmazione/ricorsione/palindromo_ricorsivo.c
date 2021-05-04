#include <stdio.h>

int palindrome(const char * const str){
    static int len;
    static int count;
    if(!(str[len]))
        return 1;
    else{
        len++;
        return palindrome(str) * (str[len-1-count]==str[count++] ? 1:0);   
    }
}


void main(){
    char *str = "abcba";
    printf("%d\n",palindrome(str)); // 1 se palindromo 0 altrimenti
}