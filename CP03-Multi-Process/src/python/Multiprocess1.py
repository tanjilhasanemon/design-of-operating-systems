import multiprocessing
import os
import time

def worker(name):
    print(f"[Worker {name}] PID: {os.getpid()}, Parent PID: {os.getppid()}")
    time.sleep(1)

if __name__ == '__main__':
    print(f"[Main Process] PID: {os.getpid()}")
    p = multiprocessing.Process(target=worker, args=('A',))
    p.start()
    p.join()
    print("[Main Process] Worker process completed.")
