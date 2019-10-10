package task03;

public class Message {
    private final MessageTopic topic;
    private final String report;

    public Message(MessageTopic topic, String report) {
        this.topic = topic;
        this.report = report;
    }

    public String getReport() {
        return report;
    }

    public MessageTopic getTopic() {
        return topic;
    }
}
