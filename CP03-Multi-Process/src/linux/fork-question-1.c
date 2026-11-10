#include <stdio.h>
#include <unistd.h>

// Question: How many times will "Hello" be printed?
// Formula: Total processes created = 2^n (where n = number of fork calls)
int main() {
    fork();
    fork();
    fork();
    printf("Hello from Process PID: %d\n", getpid());
    return 0;
}
