import multiprocessing
import os

def compute_square(number):
    result = number * number
    print(f"Process PID {os.getpid()}: Square of {number} = {result}")

if __name__ == '__main__':
    numbers = [1, 2, 3, 4, 5]
    processes = []

    for num in numbers:
        p = multiprocessing.Process(target=compute_square, args=(num,))
        processes.append(p)
        p.start()

    for p in processes:
        p.join()

    print("All parallel worker processes finished successfully!")
