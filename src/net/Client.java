package net;

import java.io.*;
import java.net.Socket;

//TODO: Клиент тестовый, ничего особого
public class Client {
    private final int port;
    private final String address;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private BufferedReader bin;

    Client(String address, int port) throws Exception {
        this.port = port;
        this.address = address;
        socket = new Socket(address, port);
        System.out.println("Get ready!");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        bin = new BufferedReader(new InputStreamReader(System.in));
        loop();
    }

    private void loop() throws Exception {
        Thread pull = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Server write: " + in.readUTF());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pull.start();
        Thread push = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        out.writeUTF(bin.readLine());
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        push.start();

    }

    private void exit() {
        try {
            bin.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Client("127.0.0.1", 8888);
    }

}
