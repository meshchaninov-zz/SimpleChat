package net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageEngine {
    private List<Sender> senders = new ArrayList<>();
    private boolean stop = false;

    public boolean isStop() {
        return stop;
    }

    public void addSender(Sender sender) {
        System.out.println("sender addad");
        senders.add(sender);
    }

    public void remove(Sender sender) {
        System.out.println("sender removed");
        senders.remove(sender);
        if(senders.isEmpty()) stop = true;
    }

    public void sending(User exception, String message) {
        System.out.println("Sending message all");
        for(Sender s : senders) {
            try {
                s.send(exception.getUserID() + " " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
