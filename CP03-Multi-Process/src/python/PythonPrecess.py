import subprocess
import sys

def run_os_command():
    print("Running system command using Python subprocess module...")
    if sys.platform.startswith('win'):
        cmd = ['cmd', '/c', 'dir']
    else:
        cmd = ['ls', '-l']
        
    result = subprocess.run(cmd, capture_output=True, text=True)
    print("Command Output:")
    print(result.stdout)

if __name__ == '__main__':
    run_os_command()
