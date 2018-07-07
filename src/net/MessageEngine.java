package net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageEngine {
    private List<Sender> senders = new ArrayList<>();

    public void addSender(Sender sender) {
        senders.add(sender);
    }

    public void remove(Sender sender) {
        senders.remove(sender);
    }

    public void sending(User exception, String message) {
        for(Sender s : senders) {
            if (s != exception) {
                try {
                    s.send(exception.getUserID() + " " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
