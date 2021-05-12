 @ stampare a video "Hello World!"
 
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
	

