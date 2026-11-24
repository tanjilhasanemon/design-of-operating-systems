# CP8: CPU & Thread Scheduling - Run Guide

This guide explains how to compile and execute the Java CPU scheduling simulators and the C/Java thread scheduling programs across Linux (Ubuntu VM) and Windows.

---

## 1. Running the Java CPU Scheduling Simulators

Navigate to `src/cpu_scheduling_java/`:

```bash
cd src/cpu_scheduling_java
```

> **Important Note on Java Packages:** `FCFS.java`, `SJFSimulator.java`, and `PriorityScheduler.java` declare `package` statements at the top of the file, while `RRSimulator.java` uses the default package. Using `javac -d . *.java` automatically creates the proper package folders (`fcfs/`, `sjfsimulator/`, `priorityscheduler/`).

### Quick Start Commands
```bash
# 1. Compile all simulators into their respective package directories
javac -d . *.java

# 2. Run First-Come, First-Served (Interactive input)
java fcfs.FCFS

# 3. Run Shortest Job First (Pre-configured process dataset)
java sjfsimulator.SJFSimulator

# 4. Run Priority Scheduler (Interactive input)
java priorityscheduler.PriorityScheduler

# 5. Run Round Robin Simulator (Interactive input: Quantum + Processes)
java RRSimulator
```

### Line-by-Line Breakdown
* `javac -d . *.java`: Compiles all `.java` files and places the resulting `.class` bytecode files into subdirectories matching their `package` declarations inside the current directory (`.`).
* `java fcfs.FCFS`: Launches the JVM and executes the `FCFS` class inside the `fcfs` package.
* `java RRSimulator`: Executes `RRSimulator` directly from the current directory since it has no package declaration.

### Sample Test Input (for `RRSimulator`)
```text
Enter Time Quantum: 2
Enter number of processes: 3
P1 Arrival & Burst: 0 5
P2 Arrival & Burst: 1 3
P3 Arrival & Burst: 2 1
```

---

## 2. Running Thread Scheduling Programs

Navigate to `src/thread_scheduling/`:

```bash
cd src/thread_scheduling
```

### A. Java Thread Priority (`ThreadScheduling.java`)
Note that `ThreadScheduling.java` defines `package app3;` and `public class App3`:

```bash
# Compile into the app3 package folder and run
javac -d . ThreadScheduling.java
java app3.App3
```

### B. POSIX Pthread Scheduling Scope & Real-Time Policy (Linux / Ubuntu VM)
Compile the C files with `-lpthread` to link the POSIX Threads library:

```bash
# 1. Contention Scope (PCS vs. SCS)
gcc pthread_scope_scheduling.c -lpthread -o pthread_scope
./pthread_scope

# 2. Real-Time Scheduling Policy (SCHED_OTHER / SCHED_RR / SCHED_FIFO)
gcc realtime_schedular.c -lpthread -o realtime_schedular
./realtime_schedular
```

### Line-by-Line Breakdown
* `gcc <file.c> -lpthread -o <binary>`: Compiles the C program and links `libpthread` for POSIX thread creation and attribute inspection.
* `./realtime_schedular`: Prints the default thread scheduling policy (`SCHED_OTHER`), attempts to configure `SCHED_RR`, spawns 6 worker threads, and joins them. *(Note: On Linux, changing policy to `SCHED_RR` or `SCHED_FIFO` may require root privileges via `sudo ./realtime_schedular` and explicit `PTHREAD_EXPLICIT_SCHED` inheritance.)*
