#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void *thread_func(void *arg) {
    printf("[Thread] Worker thread started. Running background tasks...\n");
    while (1) {
        // Enable cancellation point
        pthread_testcancel();
        printf("[Thread] Working...\n");
        sleep(1);
    }
    return NULL;
}

int main() {
    pthread_t tid;
    pthread_create(&tid, NULL, thread_func, NULL);

    sleep(3); // Let worker run for 3 seconds
    printf("[Main] Cancelling worker thread...\n");
    pthread_cancel(tid);

    pthread_join(tid, NULL);
    printf("[Main] Worker thread successfully cancelled and joined.\n");
    return 0;
}
