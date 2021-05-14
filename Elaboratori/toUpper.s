@ Data una stringa come argomento , modificare ogni carattere (a-z) in Uppercase, dunque stampare la stringa

.global main

.type main,%function


.text

main:
	PUSH {R4,LR}

	LDR R4, [R1, #4]		@ R4 = argv[1]
	MOV R1, #0				@ R1 = i = 0

for_loop:
	LDRB R2, [R4,R1]		@ R2 = vet[i] = R0[R1]
	CMP R2, #'a'			@ R2 < 'a' ?
	BLT exit_condition
	CMP R2, #'z'			@ R2 > 'z' ?
	BGT exit_condition
	SUB R2, #32				@ R2 = R2-32
	STRB R2, [R4,R1]		@ vet[i] = R2 (modificato)
	B continue
	
exit_condition:
	CMP R2, #0
	BEQ for_end

continue:
	ADD R1, R1, #1			@ i++ = R1 = R1 + 1
	B for_loop
	
	
for_end:
	LDR R0, =fmt_str
	MOV R1, R4
	@LDR R1, =str
	BL printf
	
end:
	POP {R4,LR}
	MOV PC, LR
	

.data
fmt_str:
	.asciz "toUpper: %s\n"
	
