import threading
import time

def worker(id, delay):
    print(f"Worker {id} starting...")
    time.sleep(delay)
    print(f"Worker {id} completed after {delay} seconds.")

if __name__ == '__main__':
    threads = []
    for i in range(3):
        t = threading.Thread(target=worker, args=(i+1, i+1))
        threads.append(t)
        t.start()

    for t in threads:
        t.join()

    print("All worker threads joined successfully.")
