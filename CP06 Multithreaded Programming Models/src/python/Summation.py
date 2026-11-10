import threading
import sys

shared_sum = 0

def calculate_sum(n):
    global shared_sum
    total = sum(range(1, n + 1))
    shared_sum = total
    print(f"[Thread] Sum from 1 to {n} is {total}")

if __name__ == '__main__':
    n = int(sys.argv[1]) if len(sys.argv) > 1 else 10
    t = threading.Thread(target=calculate_sum, args=(n,))
    t.start()
    t.join()
    print(f"Final shared sum = {shared_sum}")
