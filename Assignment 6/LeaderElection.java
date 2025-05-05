import java.util.*;

class Process {
    int id;
    boolean isActive;

    public Process(int id) {
        this.id = id;
        this.isActive = true;
    }
}

public class LeaderElection {
    static Process[] processes;
    static int coordinatorId = -1;

    // ----------- Bully Algorithm -----------
    public static void bullyElection(int initiatorId) {
        System.out.println("\n--- Bully Algorithm Started by Process " + initiatorId + " ---");
        int maxId = -1;

        for (int i = initiatorId + 1; i < processes.length; i++) {
            if (processes[i].isActive) {
                System.out.println("Process " + initiatorId + " sends election message to Process " + i);
            }
        }

        // Find highest active process
        for (int i = initiatorId + 1; i < processes.length; i++) {
            if (processes[i].isActive) {
                maxId = i;
            }
        }

        if (maxId == -1) {
            coordinatorId = initiatorId;
        } else {
            bullyElection(maxId);
            return;
        }

        System.out.println("Process " + coordinatorId + " becomes coordinator.");
    }

    // ----------- Ring Algorithm -----------
    public static void ringElection(int initiatorId) {
        System.out.println("\n--- Ring Algorithm Started by Process " + initiatorId + " ---");
        List<Integer> electionIds = new ArrayList<>();
        int current = initiatorId;

        do {
            if (processes[current].isActive) {
                System.out.println("Process " + current + " passes message.");
                electionIds.add(processes[current].id);
            }
            current = (current + 1) % processes.length;
        } while (current != initiatorId);

        int newCoordinator = Collections.max(electionIds);
        coordinatorId = newCoordinator;
        System.out.println("Process " + coordinatorId + " becomes coordinator.");
    }

    public static void printProcesses() {
        System.out.println("\n--- Active Processes ---");
        for (int i = 0; i < processes.length; i++) {
            System.out.println("Process " + i + " - Active: " + processes[i].isActive);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5; // number of processes
        processes = new Process[n];

        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }

        printProcesses();

        System.out.println("\nEnter ID of process that will initiate election:");
        int initiatorId = sc.nextInt();

        System.out.println("Choose Algorithm:\n1. Bully Algorithm\n2. Ring Algorithm");
        int choice = sc.nextInt();

        // Simulate leader failure (optional)
        System.out.println("Do you want to deactivate a process? (Enter ID or -1 to skip):");
        int kill = sc.nextInt();
        if (kill >= 0 && kill < n) {
            processes[kill].isActive = false;
            System.out.println("Process " + kill + " is deactivated.");
        }

        if (choice == 1) {
            bullyElection(initiatorId);
        } else if (choice == 2) {
            ringElection(initiatorId);
        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
