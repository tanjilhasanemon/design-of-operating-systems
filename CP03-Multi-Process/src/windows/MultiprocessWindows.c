#include <windows.h>
#include <stdio.h>

int main() {
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    ZeroMemory(&pi, sizeof(pi));

    // Create child process on Windows
    if (!CreateProcess(
        "C:\\Windows\\System32\\notepad.exe", // Executable path
        NULL,                                   // Command line arguments
        NULL,                                   // Process handle not inheritable
        NULL,                                   // Thread handle not inheritable
        FALSE,                                  // Set handle inheritance to FALSE
        0,                                      // No creation flags
        NULL,                                   // Use parent's environment block
        NULL,                                   // Use parent's starting directory
        &si,                                    // Pointer to STARTUPINFO structure
        &pi)                                    // Pointer to PROCESS_INFORMATION structure
    ) {
        printf("CreateProcess failed (%d).\n", GetLastError());
        return 1;
    }

    printf("[Parent] Created Windows process with PID: %d\n", pi.dwProcessId);

    // Wait until child process exits
    WaitForSingleObject(pi.hProcess, INFINITE);
    printf("[Parent] Child process terminated.\n");

    // Close process and thread handles
    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);

    return 0;
}
