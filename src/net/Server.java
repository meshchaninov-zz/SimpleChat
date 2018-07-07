package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int PORT;
    private final ServerSocket serverSocket;
    private MessageEngine messageEngine = new MessageEngine();

    public Server(int PORT) throws IOException {
        this.PORT = PORT;
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server is ready in port: " + PORT);
    }

    public void accept() throws IOException {
        System.out.println("Wait for user...");
        User user = new User(serverSocket.accept(), messageEngine);
        User user1 = new User(serverSocket.accept(), messageEngine);
        System.out.println("Connection accepted");
        user.run();
        user1.run();
        loop();
    }

    private void loop() throws IOException {

    }

    public static void main(String[] args) throws IOException {
        Server s = new Server(8888);
        s.accept();
    }



}
