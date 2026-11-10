# CP3: Multi-Process Programming - Concepts

## Overview
A process is an instance of a program in execution. Multi-process programming allows an operating system to run multiple concurrent tasks, taking advantage of multi-core CPU architectures.

This module explores process creation, synchronization, memory isolation, and cross-platform multi-processing across **Linux (C)**, **Windows (C/C++)**, **Java**, and **Python**.

---

## 1. Linux / UNIX Multi-Processing (`fork` model)
In Linux/UNIX systems, process creation follows the **Fork-Exec model**.

* **`fork()` System Call:** Clones the calling process to create a child process.
  * Returns `0` to the **Child Process**.
  * Returns the **Child's PID** (> 0) to the **Parent Process**.
  * Returns `< 0` if process creation fails.
* **Process Tree & `2^n` Rule:** Calling `fork()` $n$ times consecutively creates $2^n$ total processes.
* **Memory Isolation & Copy-on-Write:** Parent and child processes execute in separate virtual memory spaces. Changes made to global/local variables by the child do not affect the parent.
* **`wait()` / `waitpid()`:** Forces the parent process to suspend execution until child processes terminate, preventing zombie processes.
* **`exec` Family (`execlp`, `execvp`):** Replaces the process address space with a new executable program image.

---

## 2. Windows Process Model (`CreateProcess`)
Unlike UNIX's two-step `fork()` + `exec()` model, Windows uses an explicit single-step API:

* **`CreateProcess()`:** Directly creates a new process and its primary thread.
* Requires explicit initialization of OS data structures:
  * `STARTUPINFO`: Configures process startup parameters (e.g., standard handles, window state).
  * `PROCESS_INFORMATION`: Receives handles and process/thread IDs for the newly spawned process.
* **`WaitForSingleObject()`:** Blocks parent execution until the specified process handle signals termination.
* **`CloseHandle()`:** Cleans up kernel object reference handles to avoid kernel object memory leaks.

---

## 3. Multi-Processing in Java (`ProcessBuilder`)
Java relies on JVM-managed native wrappers to spawn OS-level processes platform-independently:

* **`ProcessBuilder`:** Constructs native OS processes with custom environment variables, working directories, and I/O streams.
* **`Process` Class:** Represents the running native process, providing methods like `.waitFor()`, `.exitValue()`, `.getInputStream()`, and `.destroy()`.

---

## 4. Multi-Processing in Python (`multiprocessing` & `subprocess`)
Python provides two main modules for handling processes:

* **`multiprocessing.Process`:** Bypasses Python's Global Interpreter Lock (GIL) by spawning separate Python interpreter processes, giving full multi-core CPU parallelism.
  * Uses `.start()` to launch execution and `.join()` to wait for process completion.
* **`subprocess.run()`:** Used to execute native system shell utilities (`ls`, `dir`, `ping`) and capture standard output/error.
