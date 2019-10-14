package task03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Runnable {
    private static final Logger LOG = LogManager.getLogger(Consumer.class);

    private final MessageBus messageBus;
    private final MessageTopic topic;

    public Consumer(MessageBus messageBus, MessageTopic topic) {
        this.messageBus = messageBus;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (!messageBus.isLastMessageCreated() || messageBus.getMessages().size() > 0) {
            Message message = messageBus.getMessage(topic);
            if (message != null) {
                LOG.debug("{} with topic {}", message.getReport(), message.getTopic());
            }
        }
    }

}
