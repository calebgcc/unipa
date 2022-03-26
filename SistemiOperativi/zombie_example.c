#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int main(){
    pid_t pid;
    /* fork a child process */
    printf("** Test fork() syscall**\n");
    pid = fork(); // the forked child will start its execution from the very next instruction
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
        /* parent will not call the wait() function
	using the unix utility `top` you will see that there is a zombie in you system :)  */
        sleep(20);
	printf("Parent process terminated\n");
    }
}
