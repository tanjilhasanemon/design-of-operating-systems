# CP4: Multi-Process Communication - Run Guide

## 1. UNIX Pipes (C)
```bash
cd src/pipes
gcc unix_pipe.c -o unix_pipe && ./unix_pipe
```

## 2. POSIX Shared Memory (C)
```bash
cd src/shared_memory
gcc shm-posix-producer.c -lrt -o producer
gcc shm-posix-consumer.c -lrt -o consumer
./producer
./consumer
```

## 3. Java Sockets
```bash
# Terminal 1:
cd src/sockets
javac DateServer.java DateClient.java
java DateServer

# Terminal 2:
cd src/sockets
java DateClient
```

## 4. Java RMI
```bash
# Terminal 1:
cd src/rmi
javac *.java
java DateTimeServer

# Terminal 2:
cd src/rmi
java RMIClient
```
