#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main() {
    printf("Parent process executing... Spawning child to run 'ls -l'\n");
    pid_t pid = fork();

    if (pid == 0) {
        // Child replaces its memory image with 'ls' program
        execlp("ls", "ls", "-l", NULL);
        perror("exec failed");
        exit(1);
    } else {
        printf("Parent process created child PID %d\n", pid);
    }
    return 0;
}
