 @ stampare a video "Hello World!"
 @ arm-linux-gnueabihf-gcc -g -o0 -static -o Hello HelloWorld.s
 
 .global main
 .type main,%function
 
 .text
main:
	PUSH {LR}
	
	LDR R0, =str
	BL printf
	
	POP {LR}
	
	MOV PC, LR


.data
str: 
   .asciz "Hello World!\n"
	

