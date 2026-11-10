# CP6: Multithreaded Programming Models - Concepts [cite: 18]

## Overview
A thread is the fundamental unit of CPU utilization, consisting of a thread ID, program counter, register set, and stack. Multithreading allows multiple flows of execution to occur concurrently within the context of a single process, sharing memory space, global variables, and open file descriptors.

This module explores multithreading implementations across **POSIX (Linux)**, **Windows**, **Java**, and **Python** [cite: 18].

---

## 1. POSIX Threads (`pthread`)
The POSIX standard (`pthread`) defines an API for thread creation and synchronization in C/C++ on Unix-like operating systems.
* **`pthread_create()`:** Spawns a new thread, assigning it an attribute set, a start routine function, and arguments.
* **`pthread_join()`:** Blocks the calling thread until the target thread terminates (similar to process `wait()`).
* **Thread Cancellation:** Threads can be cancelled mid-execution via `pthread_cancel()`. Cancellation points (`pthread_testcancel()`) define safe locations where a thread can respond to cancellation requests.

---

## 2. Windows Threads (`CreateThread`)
The Windows API handles native threading via the Win32 API.
* **`CreateThread()`:** Creates a thread within the virtual address space of the calling process, returning a handle and thread ID.
* **`WaitForSingleObject()`:** Pauses execution until the thread handle signals completion.
* **Memory Model:** Windows threads share process heap and data sections while maintaining independent thread stacks and register contexts.

---

## 3. Java Multithreading & Concurrency
Java provides built-in language support for concurrency through `Thread` objects, `Runnable` interfaces, and advanced concurrency packages (`java.util.concurrent`).
* **Executor Framework (`ExecutorService`):** Manages thread pools efficiently, avoiding the overhead of creating new threads for every task.
* **Fork/Join Framework (`RecursiveTask` / `RecursiveAction`):** Implements work-stealing algorithms ideal for recursive parallel divide-and-conquer tasks (like QuickSort or Fibonacci).
* **Thread-Local Storage (`ThreadLocal`):** Provides isolated variable instances specific to an individual thread, preventing race conditions without synchronization locks.
* **Thread Interruption:** Java handles cancellation cooperatively via `.interrupt()` and `InterruptedException`.

---

## 4. Python Multithreading (`threading`)
Python's `threading` module provides high-level thread management.
* **Global Interpreter Lock (GIL):** In CPython, the GIL ensures that only one thread executes Python bytecode at a time, making multithreading ideal for I/O-bound tasks rather than CPU-bound multi-core processing (which requires `multiprocessing`).
