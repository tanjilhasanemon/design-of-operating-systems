# CP3: Multi-Process Programming - Run Guide

This guide provides execution instructions for Linux (Ubuntu VM), Windows, Java, and Python environments.

---

## 1. Running Linux C Programs (Ubuntu VM)

Navigate to `src/linux/` inside your terminal:

```bash
cd src/linux
```

### Commands:
```bash
# Basic Forking
gcc forking.c -o forking && ./forking

# Process Tree & Grandchild
gcc fork-child-2.c -o fork-child-2 && ./fork-child-2

# Concept Questions (Process Tracing)
gcc fork-question-1.c -o fork-question-1 && ./fork-question-1
gcc fork-question-2.c -o fork-question-2 && ./fork-question-2

# Process Execution & Waiting
gcc process1.c -o process1 && ./process1
gcc process2.c -o process2 && ./process2

# Memory Isolation & Exit Status
gcc process3.c -o process3 && ./process3
gcc process4.c -o process4 && ./process4
```

### Command Breakdown:
* `gcc <file.c> -o <output>`: Compiles the C source file into a Linux executable binary.
* `&&`: Sequential execution operator (runs the program only if compilation succeeds).
* `./<output>`: Executes the generated binary in the current directory.

---

## 2. Running Windows C/C++ Programs (Windows OS)

Open Command Prompt or Developer PowerShell in `src/windows/`:

```cmd
cd src\windows
```

### Commands (using MSVC `cl` or GCC/MinGW):
```cmd
:: Using GCC / MinGW on Windows:
gcc MultiprocessWindows.c -o MultiprocessWindows.exe
g++ HelloworldWindows.cpp -o HelloworldWindows.exe

:: Run the executables:
MultiprocessWindows.exe
HelloworldWindows.exe
```

### Command Breakdown:
* `CreateProcess()` opens a system process (e.g. `notepad.exe`).
* `WaitForSingleObject()` halts the parent application until the Notepad window is closed by the user.

---

## 3. Running Java Multi-Processing Programs

Navigate to `src/java/`:

```bash
cd src/java
```

### Commands:
```bash
# 1. Compile all Java files
javac OSProcess.java ProcessBuilderDemo.java ProcessBuilderExample1.java

# 2. Execute class files
java OSProcess
java ProcessBuilderDemo
java ProcessBuilderExample1
```

### Command Breakdown:
* `javac <file.java>`: Compiles Java source code into JVM bytecode `.class` files.
* `java <ClassName>`: Launches the Java Virtual Machine to execute the main method of the compiled class.

---

## 4. Running Python Multi-Processing Scripts

Navigate to `src/python/`:

```bash
cd src/python
```

### Commands:
```bash
# Works on both Linux and Windows
python3 Multiprocess1.py
python3 MultiprocessPython.py
python3 Python_mp1.py
python3 PythonPrecess.py
```

### Command Breakdown:
* `python3 <script.py>`: Executes Python scripts using the Python 3 interpreter.
* Note: On Windows, use `python <script.py>` if `python3` alias is not set.
