#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int shared_var = 50;

int main() {
    pid_t pid = fork();

    if (pid == 0) {
        shared_var += 20;
        printf("[Child] Modified shared_var to: %d\n", shared_var);
    } else {
        wait(NULL);
        printf("[Parent] shared_var value remains: %d (Separate memory address space)\n", shared_var);
    }
    return 0;
}
