# Makefile ADT - Binary Search Tree

run : main.o complex.o
	gcc -o run main.o complex.o -lm
main.o complex.o :
	gcc -c $*.c

clean :
	rm *.o

super :
	make clean
	make
	./run
