package priorityscheduler;

import java.util.Arrays;
import java.util.Scanner;

public class PriorityScheduler {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Processes: ");
        int numberOfProcess = sc.nextInt();

        // Assume processes arrive at time 0 for simplicity in this basic example
        // For more complex scenarios, include arrival time and sort by it as well.

        int[] burstTime = new int[numberOfProcess];
        int[] priority = new int[numberOfProcess];
        String[] processNames = new String[numberOfProcess];

        for (int i = 0; i < numberOfProcess; i++) {
            processNames[i] = "P" + (i + 1);
            System.out.print("Enter Burst Time for " + processNames[i] + ": ");
            burstTime[i] = sc.nextInt();
            System.out.print("Enter Priority for " + processNames[i] + ": ");
            priority[i] = sc.nextInt();
        }

        // Sort processes by priority (lower number = higher priority)
        for (int i = 0; i < numberOfProcess - 1; i++) {
            for (int j = 0; j < numberOfProcess - 1 - i; j++) {
                if (priority[j] > priority[j + 1]) {
                    // Swap priority
                    int tempP = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = tempP;

                    // Swap burst time
                    int tempB = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = tempB;

                    // Swap process name
                    String tempN = processNames[j];
                    processNames[j] = processNames[j + 1];
                    processNames[j + 1] = tempN;
                }
            }
        }

        int[] waitingTime = new int[numberOfProcess];
        int[] turnaroundTime = new int[numberOfProcess];
        int[] completionTime = new int[numberOfProcess];

        waitingTime[0] = 0; // First process waits 0 time
        completionTime[0] = burstTime[0];
        turnaroundTime[0] = completionTime[0];

        for (int i = 1; i < numberOfProcess; i++) {
            waitingTime[i] = completionTime[i - 1]; // Waits until previous process completes
            completionTime[i] = waitingTime[i] + burstTime[i];
            turnaroundTime[i] = completionTime[i];
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        System.out.println("\nProcess\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < numberOfProcess; i++) {
            System.out.println(processNames[i] + "\t\t" + burstTime[i] + "\t\t" + priority[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / numberOfProcess));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / numberOfProcess));

        sc.close();
    }
}