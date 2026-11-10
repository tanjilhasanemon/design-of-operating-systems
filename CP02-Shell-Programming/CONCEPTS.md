# CP2: Shell Programming and Scripts - Concepts

## Overview
This class performance covers two critical aspects of Operating Systems:
1. **Shell Scripting (Bash):** Automating interactions with the operating system kernel using a command-line interface.
2. **Process Management & I/O Redirection (C):** Understanding how a shell actually works under the hood by building a custom C-based `minishell`.

---

## 1. Shell Scripting Fundamentals (Bash)
A shell script is simply a text file containing a sequence of commands for a UNIX-based operating system. 

* **The Shebang (`#!/bin/bash`):** Placed at the very top of a script, this tells the OS exactly which interpreter to use to run the file.
* **Variables & I/O (`read` / `echo`):** Scripts can store data temporarily in variables (e.g., `USER_AGE`) and request input interactively from the user.
* **Control Structures:**
  * **Conditionals (`if`, `else`):** Allows the script to make decisions (e.g., checking if a number is greater than 10).
  * **Switch Cases (`case`):** A cleaner way to write multiple `if/else` statements, especially useful for building menus.
* **Loops:**
  * **`for` loops:** Iterate over a specific list of items (like a list of names).
  * **`while` loops:** Continue executing as long as a condition is true (e.g., waiting for the user to guess a password).

---

## 2. Under the Hood: Building a Minishell in C
When you type a command (like `ls` or `df`) into a terminal, the shell program must interact with the OS Kernel to execute it. The `minishell.c` program demonstrates the four most important System Calls used to achieve this:

### Process Creation and Management
1. **`fork()`:** This system call clones the current process. It creates a "Child Process" that is an exact duplicate of the "Parent Process" (the shell). We do this because if the shell executed the command directly, the shell itself would be replaced and terminate.
2. **`execvp()`:** Used *inside* the child process. It replaces the child's memory space with a brand new program (e.g., replacing the clone of the shell with the `ls` program).
3. **`wait()`:** Used *inside* the parent process. It forces the shell to pause and wait for the child process to finish running before asking the user for the next command.

### I/O Redirection (`>`)
In Unix, everything is a file. Standard output (printing to the screen) is represented by a File Descriptor called `STDOUT_FILENO` (usually 1). 
1. **`open()`:** Opens the target file (e.g., `output.txt`) and assigns it a new file descriptor (e.g., `fd = 3`).
2. **`dup2(fd, STDOUT_FILENO)`:** This duplicates descriptor `3` onto descriptor `1`. Now, whenever the newly executed program tries to print to the screen (descriptor 1), the kernel routes that text directly into the file!
