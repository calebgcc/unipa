# Makefile ADT - Queue implementation with Binary File

run : main.o queue.o
        gcc -o run main.o queue.o
main.o queue.o :
        gcc -c $*.c
		
clean :
        rm *.o

super :
        make clean
        make
        ./run