package task03;

import java.util.Random;

import static task03.utils.Utils.randomAlphaNumeric;

public class Producer implements Runnable{
    private final MessageBus messageBus;
    private final int messageCount;

    public Producer(MessageBus messageBus, int messageCount) {
        this.messageBus = messageBus;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < messageCount) {
            count++;
            MessageTopic topic = MessageTopic.values()[new Random().nextInt(MessageTopic.values().length)];
            String generatedString = randomAlphaNumeric(10);
            messageBus.addMessage(new Message(topic, generatedString));
        }
        messageBus.setLastMessageCreated(true);
    }

}
