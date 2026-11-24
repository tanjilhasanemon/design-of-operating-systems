package sjfsimulator;

   import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int waitingTime;
    int turnaroundTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}
public class SJFSimulator {

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 6));
        processes.add(new Process(2, 1, 8));
        processes.add(new Process(3, 2, 7));
        processes.add(new Process(4, 3, 3));

        scheduleSJF(processes);
    }

    public static void scheduleSJF(List<Process> processes) {
        // Sort processes by arrival time initially
        Collections.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        List<Process> completedProcesses = new ArrayList<>();
        List<Process> readyQueue = new ArrayList<>();
        int currentTime = 0;
        int n = processes.size();
        int completedCount = 0;

        while (completedCount < n) {
            // Add arrived processes to ready queue
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && !completedProcesses.contains(p) && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }

            // If ready queue is empty, increment time
            if (readyQueue.isEmpty()) {
                currentTime++;
                continue;
            }

            // Sort ready queue by burst time (SJF logic)
            Collections.sort(readyQueue, Comparator.comparingInt(p -> p.burstTime));

            Process currentProcess = readyQueue.remove(0); // Get the shortest job

            // Simulate execution
            currentProcess.waitingTime = currentTime - currentProcess.arrivalTime;
            currentTime += currentProcess.burstTime;
            currentProcess.completionTime = currentTime;
            currentProcess.turnaroundTime = currentProcess.completionTime - currentProcess.arrivalTime;

            completedProcesses.add(currentProcess);
            completedCount++;
        }

        // Print results
        System.out.println("Process\tArrival\tBurst\tCompletion\tWaiting\tTurnaround");
        for (Process p : processes) {
            System.out.printf("%d\t%d\t%d\t%d\t\t%d\t\t%d\n",
                    p.id, p.arrivalTime, p.burstTime, p.completionTime, p.waitingTime, p.turnaroundTime);
        }
    }
}