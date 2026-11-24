# CP8: CPU & Thread Scheduling (SCH) - Concepts

## Overview
In a multiprogrammed operating system, multiple processes and threads compete for CPU execution time. **CPU Scheduling** is the mechanism by which the OS scheduler selects which ready process or thread runs next on a CPU core, maximizing CPU utilization, throughput, and responsiveness while minimizing waiting and turnaround times.

This module covers two major areas:
1. **Classic CPU Scheduling Algorithms (Simulated in Java):** FCFS, SJF, Priority Scheduling, and Round Robin.
2. **OS & Real-Time Thread Scheduling (Java & POSIX Pthreads):** Thread priorities, contention scope (PCS vs. SCS), and POSIX real-time scheduling policies (`SCHED_OTHER`, `SCHED_RR`, `SCHED_FIFO`).

---

## 1. Key Scheduling Metrics & Formulas
To compare scheduling algorithms, operating systems measure four core time metrics for each process:
* **Arrival Time ($AT$):** The exact time a process enters the Ready Queue.
* **Burst Time ($BT$):** The total CPU execution time required by the process.
* **Completion Time ($CT$):** The exact time at which the process finishes execution.
* **Turnaround Time ($TAT$):** Total time elapsed from submission to completion:
  $$TAT = CT - AT$$
* **Waiting Time ($WT$):** Total time a process spends waiting in the Ready Queue:
  $$WT = TAT - BT$$
* **Response Time ($RT$):** Time from arrival until the CPU executes the process for the *first* time:
  $$RT = \text{First Run Time} - AT$$

---

## 2. CPU Scheduling Algorithms (`src/cpu_scheduling_java/`)

### A. First-Come, First-Served (`FCFS.java`)
* **How it works:** Non-preemptive algorithm that executes processes strictly in the order they arrive (FIFO queue).
* **Behavior:** If the CPU is idle when a new process arrives later, the CPU waits until `arrivalTime[i]`.
* **Pros/Cons:** Simple to implement, but suffers from the **Convoy Effect** (short processes get stuck waiting behind one long CPU-bound process, increasing average waiting time).

### B. Shortest Job First (`SJFSimulator.java`)
* **How it works:** Non-preemptive algorithm that inspects all processes currently in the Ready Queue (`arrivalTime <= currentTime`) and selects the one with the smallest `burstTime`.
* **Pros/Cons:** Provably optimal for minimizing average waiting time among non-preemptive algorithms, but requires knowing burst lengths in advance and can cause **starvation** for long jobs if short jobs keep arriving.

### C. Priority Scheduling (`PriorityScheduler.java`)
* **How it works:** Each process is assigned an integer priority (in this implementation, a **lower number = higher priority**, e.g., Priority 1 runs before Priority 3). Processes are sorted by priority and executed accordingly.
* **Pros/Cons:** Critical system tasks run immediately, but low-priority processes risk indefinite **starvation** (typically solved in real OS kernels using **aging**).

### D. Round Robin (`RRSimulator.java`)
* **How it works:** Preemptive time-sharing algorithm designed for interactive systems. Every process gets a fixed CPU slice called a **Time Quantum ($q$)**.
  * If `remainingTime <= quantum`, the process finishes and records its `completionTime`.
  * If `remainingTime > quantum`, the process runs for $q$ units, is preempted, and is placed back at the tail of the `readyQueue`.
* **Pros/Cons:** Guarantees fair CPU sharing and fast **Response Time ($RT$)** without starvation, though overly small quantums increase context-switch overhead.

---

## 3. Thread Scheduling (`src/thread_scheduling/`)

### A. Java Thread Priority (`ThreadScheduling.java`)
* Java threads can request priority hints from `Thread.MIN_PRIORITY` (1) to `Thread.MAX_PRIORITY` (10) via `setPriority()`.
* Here, `t1` gets priority `9` and `t2` gets priority `1`. Because Java maps threads directly onto native OS kernel threads, exact execution order still depends on the underlying OS scheduler (Windows vs. Linux).

### B. POSIX Contention Scope (`pthread_scope_scheduling.c`)
* **`PTHREAD_SCOPE_PROCESS` (PCS):** Process-Contention Scope—user-level threads compete for available LWPs (Lightweight Processes) within the same process (Many-to-One / Many-to-Many models).
* **`PTHREAD_SCOPE_SYSTEM` (SCS):** System-Contention Scope—each thread is bound directly to a kernel thread and competes globally across all processes in the OS (used by Linux and Windows One-to-One threading models).

### C. POSIX Real-Time Scheduling Policies (`realtime_schedular.c`)
POSIX defines three primary scheduling policies via `pthread_attr_setschedpolicy()`:
* **`SCHED_OTHER`:** Standard Linux time-sharing policy (CFS — Completely Fair Scheduler).
* **`SCHED_FIFO`:** Real-time First-In, First-Out policy; a running thread holds the CPU until it finishes, blocks, or yields.
* **`SCHED_RR`:** Real-time Round-Robin policy; identical to `SCHED_FIFO` except threads of equal priority share CPU time using a fixed time quantum.
