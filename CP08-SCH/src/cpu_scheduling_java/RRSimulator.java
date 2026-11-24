import java.util.*;

class Process {
    int id, arrivalTime, burstTime, remainingTime, completionTime;
    int turnAroundTime, waitingTime, responseTime;
    boolean isStarted = false;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RRSimulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        
        Process[] proc = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + " Arrival & Burst: ");
            proc[i] = new Process(i + 1, sc.nextInt(), sc.nextInt());
        }

        // Sort by arrival time initially
        Arrays.sort(proc, Comparator.comparingInt(p -> p.arrivalTime));

        Queue<Process> readyQueue = new LinkedList<>();
        int currentTime = 0, completed = 0, procIdx = 0;
        float totalWT = 0, totalTAT = 0, totalRT = 0;

        while (completed < n) {
            // Add all processes that have arrived by now to the queue
            while (procIdx < n && proc[procIdx].arrivalTime <= currentTime) {
                readyQueue.add(proc[procIdx++]);
            }

            if (!readyQueue.isEmpty()) {
                Process p = readyQueue.poll();

                // Response Time: Recorded only on first execution
                if (!p.isStarted) {
                    p.responseTime = currentTime - p.arrivalTime;
                    p.isStarted = true;
                    totalRT += p.responseTime;
                }

                int executeTime = Math.min(p.remainingTime, quantum);
                p.remainingTime -= executeTime;
                currentTime += executeTime;

                // Check for new arrivals during the time slice
                while (procIdx < n && proc[procIdx].arrivalTime <= currentTime) {
                    readyQueue.add(proc[procIdx++]);
                }

                if (p.remainingTime > 0) {
                    readyQueue.add(p); // Re-queue if unfinished
                } else {
                    p.completionTime = currentTime;
                    p.turnAroundTime = p.completionTime - p.arrivalTime;
                    p.waitingTime = p.turnAroundTime - p.burstTime;
                    totalWT += p.waitingTime;
                    totalTAT += p.turnAroundTime;
                    completed++;
                }
            } else {
                currentTime++; // CPU idle
            }
        }

        System.out.println("\nPID\tArr\tBurst\tCT\tTAT\tWT\tRT");
        for (Process p : proc) {
            System.out.println(p.id + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" + 
                               p.completionTime + "\t" + p.turnAroundTime + "\t" + 
                               p.waitingTime + "\t" + p.responseTime);
        }
        System.out.printf("\nAvg WT: %.2f | Avg TAT: %.2f | Avg RT: %.2f\n", 
                          totalWT/n, totalTAT/n, totalRT/n);
    }
}
