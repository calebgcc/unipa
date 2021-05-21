@ Dato un array e la sua dimensione, stampare a video il vettore e ricercare il massimo 

.global main
.type main,%function

.text

main:
		PUSH {R4,R5,R6,R7, R8, LR}
		
		LDR R4, =vet			@ R4 = vet
		LDR R5, =dim			@ R5 = 6
		
		
print_vet:
		LDR R0, =fmt_str
		BL printf 
		MOV R6, #0				@ R6 = 0 = i counter var
print_for:
		CMP R6,R5
		BGE print_for_end
		LDR R0, =fmt_int
		LDR R1, [R4, R6, LSL #2]
		BL printf
		ADD R6, R6, #1
		B print_for
print_for_end:
		LDR R0, =fmt_ent
		BL printf

	
search_max:
		MOV R6, #0				@ R6 = 0 = i
		LDR R7, [R4, R6]		@ R7 = vet[0]
for_max:
		CMP R6,R5
		BGE for_max_end
		LDR R1, [R4, R6, LSL #2]
		CMP R7, R1				@ if ( R7 < R1 ) {
		BGT continue
		MOV R7, R1				@ R7 = R1
continue:
		ADD R6, R6, #1
		B for_max
for_max_end:
		LDR R0, =fmt_max
		MOV R1, R7
		BL printf

			
end:
		POP {R4,R5,R6,R7,R8,LR}
		MOV PC, LR
		

.data
	fmt_max: .asciz "[*] Il massimo è %d\n"
	fmt_int: .asciz "% d |"
	fmt_str: .asciz "[ ] Vettore: "
	fmt_ent: .asciz "\n"
	vet: .word 12, 32, -1, 0, 4, 9
	dim = 6
		
		
