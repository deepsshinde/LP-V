import java.util.Random;

class Process extends Thread {
    private int id;
    private boolean hasToken;
    private boolean requestCS;
    private Process[] ring;

    public Process(int id, Process[] ring) {
        this.id = id;
        this.ring = ring;
        this.hasToken = false;
        this.requestCS = false;
    }

    public void receiveToken() {
        hasToken = true;
    }

    @Override
    public void run() {
        Random rand = new Random();

        while (true) {
            try {
                Thread.sleep(rand.nextInt(3000) + 1000); // Simulate work
                requestCS = rand.nextBoolean(); // Randomly decide to request CS

                if (hasToken) {
                    if (requestCS) {
                        enterCriticalSection();
                        requestCS = false;
                    }

                    // Pass token to next process
                    int nextId = (id + 1) % ring.length;
                    ring[nextId].receiveToken();
                    hasToken = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void enterCriticalSection() throws InterruptedException {
        System.out.println("Process " + id + " entering Critical Section.");
        Thread.sleep(2000); // Simulate work in CS
        System.out.println("Process " + id + " exiting Critical Section.");
    }
}

public class TokenRingSimulation {
    public static void main(String[] args) {
        int n = 5; // Number of processes
        Process[] ring = new Process[n];

        // Create processes
        for (int i = 0; i < n; i++) {
            ring[i] = new Process(i, ring);
        }

        // Give the token to the first process
        ring[0].receiveToken();

        // Start all processes
        for (int i = 0; i < n; i++) {
            ring[i].start();
        }
    }
}
