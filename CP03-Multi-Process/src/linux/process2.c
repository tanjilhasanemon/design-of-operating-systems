#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();

    if (pid == 0) {
        printf("[Child] Working... sleeping for 2 seconds\n");
        sleep(2);
        printf("[Child] Done! Exiting.\n");
    } else {
        printf("[Parent] Waiting for child to finish...\n");
        wait(NULL);
        printf("[Parent] Child has finished execution.\n");
    }
    return 0;
}
