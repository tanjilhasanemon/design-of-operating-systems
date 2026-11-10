#include <windows.h>
#include <stdio.h>

DWORD WINAPI SummationThread(LPVOID Param) {
    long upper = *(long*)Param;
    long sum = 0;
    for (long i = 1; i <= upper; i++) {
        sum += i;
    }
    *(long*)Param = sum; // Return sum through parameter pointer
    return 0;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: summation.exe <integer>\n");
        return 1;
    }

    long val = atol(argv[1]);
    DWORD ThreadId;
    HANDLE ThreadHandle;

    // Create Windows Thread
    ThreadHandle = CreateThread(
        NULL,                   // default security attributes
        0,                      // default stack size
        SummationThread,        // thread function
        &val,                   // parameter to thread function
        0,                      // default creation flags
        &ThreadId               // receive thread ID
    );

    if (ThreadHandle == NULL) {
        printf("Error creating thread.\n");
        return 1;
    }

    // Wait until thread is finished
    WaitForSingleObject(ThreadHandle, INFINITE);
    CloseHandle(ThreadHandle);

    printf("sum = %ld\n", val);
    return 0;
}
