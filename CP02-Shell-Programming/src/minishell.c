#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>

#define MAX_LINE 80
#define MAX_ARGS 10

void execute_command(char *args[]) {
    int pid;
    int redirect_idx = -1;
    char *outfile = NULL;

    // TASK 2: Scan the arguments array for the '>' redirection operator
    for (int i = 0; args[i] != NULL; i++) {
        if (strcmp(args[i], ">") == 0) {
            redirect_idx = i;
            outfile = args[i + 1]; // The token after '>' is the filename
            break;
        }
    }

    // If '>' was found, truncate the args array to hide '>' and the filename from execvp
    if (redirect_idx != -1) {
        if (outfile == NULL) {
            fprintf(stderr, "minishell: syntax error near unexpected token 'newline'\n");
            return;
        }
        args[redirect_idx] = NULL;
    }

    // TASK 1: Fork a child process to execute the command
    pid = fork();

    if (pid < 0) {
        // Fork failed
        perror("minishell: fork failed");
        exit(1);
    } 
    else if (pid == 0) {
        // --- CHILD PROCESS CODE ---

        // TASK 2: Handle output redirection if a target file was specified
        if (outfile != NULL) {
            // Open file for writing: Write-Only, Create if missing, Truncate if exists
            // Permissions set to 0644 (Read/Write for owner, Read for others)
            int fd = open(outfile, O_WRONLY | O_CREAT | O_TRUNC, 0644);
            if (fd < 0) {
                perror("minishell: open failed");
                exit(1);
            }
            
            // Redirect standard output (STDOUT_FILENO) to the file descriptor
            if (dup2(fd, STDOUT_FILENO) < 0) {
                perror("minishell: dup2 failed");
                close(fd);
                exit(1);
            }
            
            // Close the original descriptor since it's no longer needed after dup2
            close(fd); 
        }

        // Execute the program
        if (execvp(args[0], args) < 0) {
            perror("minishell: execution failed");
            exit(1);
        }
    } 
    else {
        // --- PARENT PROCESS CODE ---
        // Wait for the child process to complete execution before returning to loop
        wait(NULL);
    }
}

int main(void) {
    char input[MAX_LINE];
    char *args[MAX_ARGS];
    
    while (1) {
        printf("minishell> ");
        fflush(stdout);
        
        // Read input from user
        if (!fgets(input, MAX_LINE, stdin)) break;
        
        // Remove trailing newline character
        input[strcspn(input, "\n")] = '\0';
        
        // Tokenize input string into arguments
        int i = 0;
        args[i] = strtok(input, " ");
        while (args[i] != NULL && i < MAX_ARGS - 1) {
            i++;
            args[i] = strtok(NULL, " ");
        }
        args[i] = NULL; // Null-terminate argument list

        if (args[0] == NULL) continue; // Empty command
        if (strcmp(args[0], "exit") == 0) break; // Exit shell

        execute_command(args);
    }
    return 0;
}
