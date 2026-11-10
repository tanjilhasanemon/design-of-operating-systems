#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();

    if (pid == 0) {
        printf("[Child] Terminating with exit status code 42.\n");
        exit(42);
    } else {
        int status;
        waitpid(pid, &status, 0);
        if (WIFEXITED(status)) {
            printf("[Parent] Child exited normally with status code: %d\n", WEXITSTATUS(status));
        }
    }
    return 0;
}
