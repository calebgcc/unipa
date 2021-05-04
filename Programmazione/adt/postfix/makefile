# Makefile ADT - postfix

run : main.o stack.o
	gcc -o postfix main.o stack.o
main.o stack.o :
	gcc -c $*.c

clean :
	rm *.o