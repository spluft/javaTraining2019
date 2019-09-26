package task03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageBus {
    private static final Logger LOG = LogManager.getLogger(MessageBus.class);

    private final List<Message> messages = new ArrayList<>();
    private boolean lastMessageCreated;

    public List<Message> getMessages() {
        return messages;
    }

    public synchronized void addMessage(Message message) {
        notifyAll();
        messages.add(message);
        try {
            wait();
        } catch (InterruptedException e) {
            LOG.warn("Exception when added message", e);
        }
    }

    public synchronized Message getMessage(MessageTopic topic) {

        if (messages.size() > 0) {
            notifyAll();
            for (Message message : messages) {
                if (message.getTopic().equals(topic)) {
                    messages.remove(message);
                    return message;
                }
            }
        }
        try {
            wait();
        } catch (InterruptedException e) {
            LOG.warn("Exception when get message", e);
        }
        return null;
    }

    public boolean isLastMessageCreated() {
        return lastMessageCreated;
    }

    public void setLastMessageCreated(boolean lastMessageCreated) {
        this.lastMessageCreated = lastMessageCreated;
    }
}
