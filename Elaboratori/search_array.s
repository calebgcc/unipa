@ Verranno passati come argomenti: una stringa di 6 nummeri non negativi Es: "12 34 45 0 2 10 " e una key non negativa da ricercare Es: "0"
@ Esempio: ./run "10 2 30 4 50 6 " "40"
@ Il programma dovrà inserire la stringa di 6 numeri all interno di un array e successivamente cercare la key ritornando il suo indice all interno dell array


.global main
.type main,%function

.text

main:
	PUSH {R4,R5,R6,R7,R8,LR}
	
	CMP R0,#3
	BNE arg_error		@ sono stati inseriti gli argomenti corretti?
	LDR R4, [R1,#4]		@ R4 argomento vettore in formato stringa
	LDR R5, [R1,#8]		@ R5 key da cercare in formato stringa
	
	MOV R2, #1
	MOV R3, #0			@ contatore vettore
	MOV R0, #0			@ contatore stringa
	MOV R1, #0			@ registro temporaneo
	MOV R6, #10			@ costante
	LDR R7, =vet
for_str:
	CMP R2, #0
	BEQ for_str_end		@ esco al terminatore stringa
	LDRB R2, [R4,R0]	@ carico un carattere
	CMP R2, #' '			@ è uno spazio ?
	BEQ add_vet	
	CMP R2, #'0'			@ è minore di '0' ?
	BLT continue
	CMP R2, #'9'			@ è maggiore di '9' ?
	BGT continue
	SUB R2, R2, #'0'
	MUL R1, R1, R6
	ADD R1, R1, R2
	ADD R2, R2, #'0'		@ in questo modo se avevo trasformato R2 in 0 ora ritorna '0' poichè 0 fa uscire dal ciclo
	B continue
	
add_vet:
	STR R1, [R7,R3]
	ADD R3, R3, #4
	MOV R1, #0
	B continue
continue:
	ADD R0, R0, #1
	B for_str
for_str_end:

						@ stampa il vettore
	LDR R0, =fmt_vet	
	BL printf
	LDR R7, =vet
	MOV R8, #0			@ index del vet
for_vet:
	CMP R8,#24
	BEQ for_vet_end
	LDR R0, =fmt_int
	LDR R1, [R7,R8]
	BL printf
	ADD R8, R8, #4
	B for_vet
for_vet_end:
	LDR R0, =fmt_ent
	BL printf
	
					@ cerco la chiave nel vettore
	MOV R1, #0		@ indice della stringa
	MOV R3, #0
	MOV R0, #10
for_key:
	LDRB R2, [R5,R1]
	CMP R2, #0
	BEQ for_key_end
	MUL R3, R3, R0
	SUB R2, R2, #'0'
	ADD R3, R3, R2
	ADD R1, R1, #1
	B for_key
for_key_end:
	MOV R5, R3
	
	MOV R1, #0		@ indice vettore
	LDR R7, =vet
for_search:
	CMP R1, #6
	BGE for_search_end
	LDR R2, [R7,R1, LSL #2]
	CMP R2, R5
	BEQ for_search_end
	ADD R1, R1, #1
	B for_search

for_search_end:
	CMP R1, #6
	BGE not_found
	LDR R0, =fmt_yes
	MOV R3, R1
	MOV R1, R5
	MOV R2, R3
	BL printf
	B end

not_found:
	LDR R0, =fmt_not
	MOV R1, R5
	BL printf
	B end
	
	
	
	
	
	
	
arg_error:
	LDR R0, =fmt_err
	BL printf
	B end
	
end:
	POP {R4,R5,R6,R7,R8,LR}
	MOV PC, LR			@ return
	
	
	

.data
	vet: .word 0, 0, 0, 0, 0, 0
	fmt_err : .asciz "Errore \n \t usage: ./run \"1 2 3 4 5 6\" \"7\"\n"
	fmt_vet : .asciz "Vettore caricato: "
	fmt_int : .asciz "%d "
	fmt_ent : .asciz "\n"
	fmt_not : .asciz "Key [%d] non trovata\n"
	fmt_yes : .asciz "Key [%d] trovata in posizione %d\n"
