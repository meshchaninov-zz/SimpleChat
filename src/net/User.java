package net;

import javafx.util.Pair;

import java.io.*;
import java.net.Socket;
import java.util.Queue;

public class User extends Thread implements Sender {
    static int usersCount = 0;
    static int ID = 0;
    private final int userID;
    private final Socket socket;
    private String userName;
    private MessageEngine engine;
    private final DataInputStream input;
    private final DataOutputStream output;

    public User(Socket socket, MessageEngine engine) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.userID = ID++;
        this.engine = engine;
        engine.addSender(this);
        usersCount++;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void send(String message) throws IOException{
        output.writeUTF(message);
        output.flush();
    }

    public String read() throws IOException {
        return input.readUTF();
    }

    private void logic() {
        engine.sending(this, getUserID() + " Hello!");
    }

    private void firstHandShake() throws IOException {

    }

    private void exit() {
        System.out.println("Exit user");
        engine.remove(this);
        try {
            input.close();
            output.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        usersCount--;
    }

    @Override
    public void run() {
        try {
            firstHandShake();
            logic();
            exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
