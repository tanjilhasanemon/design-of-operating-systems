package fcfs;

/**
 *
 * @author User
 */

import java.util.*;

public class FCFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n]; // Process IDs
        int[] arrivalTime = new int[n]; // Arrival times
        int[] burstTime = new int[n]; // Burst times
        int[] completionTime = new int[n]; // Completion times
        int[] turnAroundTime = new int[n]; // Turnaround times
        int[] waitingTime = new int[n]; // Waiting times

        // Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.println("\\nEnter details for Process " + pid[i]);
            System.out.print("Arrival Time: ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Burst Time: ");
            burstTime[i] = sc.nextInt();
        }

        // Sort processes by arrival time (essential for FCFS with different arrival times)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivalTime[i] > arrivalTime[j]) {
                    // Swap arrival time, burst time, and process ID
                    int temp = arrivalTime[i];
                    arrivalTime[i] = arrivalTime[j];
                    arrivalTime[j] = temp;

                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        // Calculate completion, turnaround, and waiting times
        completionTime[0] = arrivalTime[0] + burstTime[0];
        turnAroundTime[0] = completionTime[0] - arrivalTime[0];
        waitingTime[0] = turnAroundTime[0] - burstTime[0];

        for (int i = 1; i < n; i++) {
            // Ensure the CPU waits if the next process hasn't arrived yet
            if (arrivalTime[i] > completionTime[i - 1]) {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            } else {
                completionTime[i] = completionTime[i - 1] + burstTime[i];
            }
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
        }

        // Calculate and print average metrics
        float totalWT = 0, totalTAT = 0;
        System.out.println("\\n*** FCFS Scheduling Results ***");
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnAroundTime[i];
            System.out.printf("P%d\t%dms\t\t%dms\t\t%dms\t\t%dms\t\t%dms%n", pid[i], arrivalTime[i], burstTime[i], completionTime[i], turnAroundTime[i], waitingTime[i]);
        }

        System.out.printf("\\nAverage Turnaround Time: %.2fms%n", (totalTAT / n));
        System.out.printf("Average Waiting Time: %.2fms%n", (totalWT / n));

        sc.close();
    }
}
