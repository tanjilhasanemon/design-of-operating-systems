# CP2: Run Guide

This guide explains how to compile and execute both the Bash scripts and the custom C-based Minishell on a Linux environment (like your Ubuntu VM).

---

## Part 1: Running the Bash Scripts

### Quick Start
Open your terminal in the `src/` folder and run the following commands:

```bash
# 1. Make all script files executable
chmod +x *.sh

# 2. Run any script (example: menu.sh)
./menu.sh
```

### Line-by-Line Breakdown
* `chmod`: Stands for "change mode". This command changes file permissions.
* `+x`: Adds the "eXecutable" permission, telling Linux this is a program that can be run, not just a text file.
* `*.sh`: A wildcard that applies the command to every file ending in `.sh`.
* `./menu.sh`: The `./` tells the system to look in the *current directory* for the program named `menu.sh` and execute it. 

*(You can replace `menu.sh` with `./loop_script1.sh`, `./input_val.sh`, etc., to test the others.)*

---

## Part 2: Compiling and Running the Minishell (C Program)

### Quick Start
Open your terminal in the `src/` folder and run:

```bash
# 1. Compile the C source code into an executable
gcc minishell.c -o minishell

# 2. Run the newly created Minishell
./minishell
```

### Testing the Minishell
Once inside the `minishell> ` prompt, try these commands to see it work:
1. `ls` *(Lists files - tests fork and execvp)*
2. `ls > files.txt` *(Redirects the output to a file - tests dup2)*
3. `exit` *(Closes the Minishell and returns you to Ubuntu)*

### Line-by-Line Breakdown
* `gcc`: Invokes the GNU Compiler Collection (the standard C compiler on Linux).
* `minishell.c`: The source code file you are compiling.
* `-o minishell`: The `-o` flag specifies the Output filename. Without this, GCC would name your program `a.out` by default.
* `./minishell`: Executes your compiled shell program.
