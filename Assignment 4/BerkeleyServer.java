import java.io.*;
import java.net.*;
import java.util.*;

public class BerkeleyServer {
    public static void main(String[] args) throws Exception {
        int port = 5000;
        int numberOfClients = 3;
        int[] clientTimes = new int[numberOfClients];
        Socket[] clients = new Socket[numberOfClients];

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started. Waiting for clients...");

        // Accept client connections
        for (int i = 0; i < numberOfClients; i++) {
            clients[i] = server.accept();
            System.out.println("Client " + (i + 1) + " connected.");

            DataInputStream dis = new DataInputStream(clients[i].getInputStream());
            clientTimes[i] = dis.readInt();
            System.out.println("Received time from Client " + (i + 1) + ": " + clientTimes[i]);
        }

        // Assume server time is also one of the clocks
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Server time: ");
        int serverTime = sc.nextInt();

        // Calculate average time
        int sum = serverTime;
        for (int time : clientTimes) {
            sum += time;
        }
        int avgTime = sum / (numberOfClients + 1);
        System.out.println("Average time: " + avgTime);

        // Send adjustments to clients
        for (int i = 0; i < numberOfClients; i++) {
            int offset = avgTime - clientTimes[i];
            DataOutputStream dos = new DataOutputStream(clients[i].getOutputStream());
            dos.writeInt(offset);
            System.out.println("Sent offset " + offset + " to Client " + (i + 1));
        }

        server.close();
    }
}
