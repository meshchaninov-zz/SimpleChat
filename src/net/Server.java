package net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
        Thread acceptThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //TODO: реализовать норм выход из цикла
                    while(true) {
                        System.out.println("Wait user");
                        Socket newSocket = serverSocket.accept();
                        System.out.println("socket accept");
                        (new User(newSocket, messageEngine)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        acceptThread.start();
    }

    private void loop() throws IOException {
        accept();

    }

    public static void main(String[] args) throws IOException {
        Server s = new Server(8888);
        s.loop();
    }



}
