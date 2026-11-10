import multiprocessing

# Demonstrating separate memory space across processes in Python
shared_list = []

def append_data():
    global shared_list
    shared_list.append("Data added by child process")
    print(f"[Child Process] List contents: {shared_list}")

if __name__ == '__main__':
    p = multiprocessing.Process(target=append_data)
    p.start()
    p.join()

    print(f"[Parent Process] List contents: {shared_list} (Notice child modification is isolated!)")
