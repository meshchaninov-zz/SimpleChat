package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT;
    private final ServerSocket serverSocket;
    private Socket newUser;
    private DataInputStream input;
    private DataOutputStream output;

    public Server(int PORT) throws IOException {
        this.PORT = PORT;
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server is ready in port: " + PORT);
    }

    public void accept() throws IOException {
        System.out.println("Wait for user...");
        newUser = serverSocket.accept();
        System.out.println("Connection accepted");
        InputStream inputStream = newUser.getInputStream();
        OutputStream outputStream = newUser.getOutputStream();
        input = new DataInputStream(inputStream);
        output = new DataOutputStream(outputStream);
        loop();
    }

    private void loop() throws IOException {
        try {
            while (newUser.isConnected()) {
                String s = null;
                s = input.readUTF();
                System.out.println(s);
                output.writeUTF("Hey " + s);
                output.flush();
            }
        } catch (IOException e) {
            System.out.println("Connection refused");
        } finally {
            input.close();
            output.close();
            newUser.close();
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server(8888);
        s.accept();
    }



}
