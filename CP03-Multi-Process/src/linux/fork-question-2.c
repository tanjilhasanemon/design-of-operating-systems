#include <stdio.h>
#include <unistd.h>

// Question: Trace the parent and child values of variable x.
int main() {
    int x = 100;
    pid_t pid = fork();

    if (pid == 0) {
        x += 50;
        printf("[Child] x = %d (Address: %p)\n", x, (void*)&x);
    } else {
        x -= 50;
        printf("[Parent] x = %d (Address: %p)\n", x, (void*)&x);
    }
    return 0;
}
