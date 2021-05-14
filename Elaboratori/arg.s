@ Ricevere un nome come argomento, stampare a vide "Ciao [Nome]\n"
@ arm-linux-gnueabihf-gcc -g -o0 -static -o run arg.s

.global main
.type main,%function

.text

main:

	PUSH {R4,LR}		@ R4 non lo uso, ma allineo il push a multipli di 2
	
	CMP R0, #1			@ R0 <= 1 ?
	BLE error
	LDR R0, =fmt_str
	LDR R1, [R1, #4]	@ prendo la seconda stringa ["./run" , "Nome"]
	BL printf
	B end
	
error:
	LDR R0, =err_str	@ se non viene inserito nessun nome da salutare si ottiene un errore
	BL printf
	B end
	
end:
	POP {R4, LR}
	MOV PC, LR

.data
fmt_str:
		.asciz "Ciao %s!\n"
err_str:
		.asciz "[*] Errore! inserire un Nome: \n usage: \t./run [Nome] \n"
