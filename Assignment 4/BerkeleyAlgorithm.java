import java.util.*;

public class BerkeleyAlgorithm {

    // Number of processes (including the master)
    static int numProcesses = 5;

    // List to store the clock values of all processes
    static int[] clocks = new int[numProcesses];
    static final int MASTER_ID = 0;  // Master process has ID 0

    public static void main(String[] args) {
        // Step 1: Initialize clocks with random values (for simulation purposes)
        Random rand = new Random();
        for (int i = 0; i < numProcesses; i++) {
            clocks[i] = rand.nextInt(100) + 1;  // Random clock value between 1 and 100
        }

        // Step 2: Start the master process (ID 0) in a separate thread
        Thread masterThread = new Thread(new MasterProcess());
        masterThread.start();
    }

    // Master process: Collects clock values, calculates average, and sends it back to slaves
    static class MasterProcess implements Runnable {
        public void run() {
            // Step 3: Collect clock values from all processes (slaves)
            List<Integer> clockDifferences = new ArrayList<>();
            for (int i = 1; i < numProcesses; i++) {
                clockDifferences.add(clocks[i]);  // Add the clock value of each slave
            }

            // Add master's own clock value
            clockDifferences.add(clocks[MASTER_ID]);

            // Step 4: Calculate the average time
            int sum = 0;
            for (int clock : clockDifferences) {
                sum += clock;
            }
            int avgTime = sum / clockDifferences.size();
            System.out.println("Master calculated average time: " + avgTime);

            // Step 5: Send the average time to all processes
            for (int i = 0; i < numProcesses; i++) {
                adjustClock(i, avgTime);  // Adjust each process's clock
            }

            // Step 6: Print final clock values of all processes after adjustment
            System.out.println("\nFinal clock values after synchronization:");
            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Process " + i + " clock: " + clocks[i]);
            }
        }

        // Function to adjust the clock of each process based on the average time
        private void adjustClock(int processId, int avgTime) {
            if (processId == MASTER_ID) {
                return;  // Master does not adjust its own clock
            }
            int clockDifference = avgTime - clocks[processId];  // Difference from average time
            clocks[processId] += clockDifference;  // Adjust the clock
            System.out.println("Process " + processId + " adjusted its clock to: " + clocks[processId]);
        }
    }
}
