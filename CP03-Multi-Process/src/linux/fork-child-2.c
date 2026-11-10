#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    pid_t pid1 = fork();

    if (pid1 == 0) {
        printf("[Child 1] PID: %d, Parent PID: %d\n", getpid(), getppid());
        pid_t pid2 = fork();
        if (pid2 == 0) {
            printf("[Grandchild] PID: %d, Parent PID (Child 1): %d\n", getpid(), getppid());
        } else {
            wait(NULL);
        }
    } else {
        wait(NULL);
        printf("[Parent Process] Finished waiting for Child 1.\n");
    }

    return 0;
}
