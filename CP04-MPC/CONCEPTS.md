# CP4: Multi-Process Communication (MPC) - Concepts

## Overview
Once processes are created, they require Inter-Process Communication (IPC) to exchange data.

## 1. UNIX Pipes (`unix_pipe.c`)
Unidirectional data channel using file descriptors (`fd[0]` read, `fd[1]` write). Works between parent-child processes.

## 2. POSIX Shared Memory (`shm-posix-producer.c` / `consumer.c`)
Zero-copy IPC mechanism mapping a shared RAM region using `shm_open()` and `mmap()`.

## 3. Network Sockets (`DateServer.java` / `DateClient.java`)
Two-way TCP byte-streams enabling processes to communicate across network ports (port 6013).

## 4. Remote Method Invocation (RMI)
Object-oriented IPC enabling Java programs to execute methods on objects located in another JVM instance via RMI Registry.
