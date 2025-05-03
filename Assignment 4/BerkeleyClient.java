import java.io.*;
import java.net.*;
import java.util.*;

public class BerkeleyClient {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 5000;

        Socket socket = new Socket(host, port);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Client time: ");
        int time = sc.nextInt();

        // Send time to server
        dos.writeInt(time);

        // Receive adjustment
        int offset = dis.readInt();
        int newTime = time + offset;
        System.out.println("Received offset: " + offset);
        System.out.println("New synchronized time: " + newTime);

        socket.close();
    }
}
