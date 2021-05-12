@ Testare i principali operandi

.global main
.type main,%function

.text
main:
	PUSH {LR}
	
	LDR R0 , =fmt_add  	@ R0 = "%d + %d = %d"
	MOV R1, #8
	MOV R2, #4
	ADD R3, R1, R2 		@ R3 = R1 + R2
	BL printf
	
	LDR R0 , =fmt_sub  	@ R0 = "%d - %d = %d"
	MOV R1, #8
	MOV R2, #4
	SUB R3, R1, R2 		@ R3 = R1 - R2
	BL printf
	
	LDR R0, =fmt_number @ R0 = "%d = 0x%X\n"
	MOV R1, #8
	LSL R1, #2			@ R1 = R1 << 2
	MOV R2, R1
	BL printf
	
	LDR R0, =fmt_number @ R0 = "%d = 0x%X\n"
	MOV R1, #8
	LSR R1, #2			@ R1 = R1 >> 2
	MOV R2, R1
	BL printf
	
	LDR R0, =fmt_number @ R0 = "%d = 0x%X\n"
	MOV R1, #-8			@ equivale a => (MVN R1, #8 | ADD R1, R1, #1)
	ASR R1, #2			@ R1 = R1 >>> 2
	MOV R2, R1
	BL printf
	
	LDR R0, =fmt_number @ R0 = "%d = 0x%X\n"
	MOV R1, #8
	ROR R1, #16			@ R1 = R1 ROR 2
	MOV R2, R1
	BL printf
	
	LDR R0, =fmt_number @ R0 = "%d = 0x%X\n"
	MOV R1, #0xaa
	ORR R1, R1, #0xbb00
	ORR R1, R1, #0xcc0000
	ORR R1, R1, #0xdd000000
	MOV R2, R1
	BL printf
	
	POP {LR}
	
	MOV PC, LR



.data
fmt_add:
	.asciz "%d + %d = %d\n"
fmt_sub:
	.asciz "%d - %d = %d\n"
fmt_number:
	.asciz "%d = 0x%X\n"
