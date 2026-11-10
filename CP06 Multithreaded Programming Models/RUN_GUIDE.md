# CP6: Multithreaded Programming Models - Run Guide [cite: 18]

## 1. POSIX Threads (Linux / Ubuntu VM)
Navigate to `src/pthread/` and compile with `-lpthread`:

```bash
cd src/pthread

# Summation Example
gcc Pthread-summation.c -lpthread -o pthread_sum
./pthread_sum 10

# Cancellation Example
gcc Pthread_test_cncel.c -lpthread -o pthread_cancel
./pthread_cancel
```

## 2. Windows Threads (C/C++)
Navigate to `src/windows/` and compile using MinGW/GCC or MSVC:

```cmd
cd src\windows
gcc summation.c -o summation.exe
summation.exe 10
```

## 3. Java Multithreading & Concurrency
Navigate to `src/java/` and compile/run:

```bash
cd src/java

# Basic Threads & Drivers
javac MultiThread.java Driver.java DriverExecutor.java
java MultiThread
java Driver
java DriverExecutor

# Advanced Concurrency (Fork/Join & QuickSort)
javac ForkJoinThread.java QuickSortMultiThreading.java
java ForkJoinThread
java QuickSortMultiThreading

# Thread Cancellation & Thread Local
javac Thread_cancellation/*.java Thread_local/*.java
java Thread_cancellation.ThreadCancellationDemo
java Thread_cancellation.DeferredCancellationExample
java Thread_local.ThreadLocalDemo
java Thread_local.ThreadLocalExample
```

## 4. Python Multithreading
Navigate to `src/python/`:

```bash
cd src/python
python3 multithread1.py
python3 multithread2.py
python3 Summation.py 15
```
