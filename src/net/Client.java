package net;

import java.io.*;
import java.net.Socket;

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
        String cl, ser;
        do {
            cl = bin.readLine();
            System.out.println("Send: " + cl);
            out.writeUTF(cl);
            out.flush();
            ser = in.readUTF();
            System.out.println("Server send me: " + ser);
        } while (cl != "exit");
        exit();

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
