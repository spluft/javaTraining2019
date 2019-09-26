package task03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageBusTest {

    @Test
    public void testmessageBus() throws InterruptedException {
        MessageBus messageBus = new MessageBus();

        Producer producer = new Producer(messageBus, 1000);
        Consumer consumerOne = new Consumer(messageBus, MessageTopic.ONE);
        Consumer consumerTwo = new Consumer(messageBus, MessageTopic.TWO);
        Consumer consumerThree = new Consumer(messageBus, MessageTopic.THREE);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumerOne = new Thread(consumerOne);
        Thread threadConsumerTwo = new Thread(consumerTwo);
        Thread threadConsumerThree = new Thread(consumerThree);

        threadProducer.start();
        threadConsumerOne.start();
        threadConsumerTwo.start();
        threadConsumerThree.start();

        assertTrue(threadProducer.isAlive());
        assertTrue(threadConsumerOne.isAlive());
        assertTrue(threadConsumerTwo.isAlive());
        assertTrue(threadConsumerThree.isAlive());

        threadProducer.join();
        threadConsumerOne.join();
        threadConsumerTwo.join();
        threadConsumerThree.join();
    }
}
