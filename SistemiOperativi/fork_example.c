#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int fib(int n){
    if(n == 0 || n == 1)
        return n;
    return fib(n-2)+fib(n-1);
}


int main(){
    pid_t pid;
    /* fork a child process */
    printf("** Test fork() syscall**\n");
    pid = fork(); // the forked chil will start its execution starting from the very next instruction
    printf("pid: %d\n",pid);

    if (pid < 0) { /* error occurred */
        fprintf(stderr, "Fork Failed");
        return 1;
    }
    else if (pid == 0) { /* child process */
        execlp("/bin/ls","ls",NULL);
        // all the instructions after the exec will never be executed
        // ....
        // .....
    }
    else { /* parent process */
        /* parent will wait for the child to complete */
        return 0;
        int status;
        pid = wait(&status); // pid and status of the terminated child
        printf("Child complete, status:%d - pid:%d \n",status,pid);
    }
}