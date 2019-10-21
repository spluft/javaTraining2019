package task05.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import task05.model.Computer;
import task05.model.OrderMessage;

public class ManagerActor extends AbstractLoggingActor {
    private OrderMessage order;
    private ActorRef customer;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderMessage.class, order -> {
                    this.order = order;
                    this.customer = getSender();

                    ActorRef engineerRouter = context().actorOf(new RoundRobinPool(4).props(Props.create(EngineerRouter.class)), "engineerRouter");
                    engineerRouter.tell(order, getSelf());
                })
                .match(Computer.class, computer -> {
                    this.order.addComputer(computer);
                    if (order.getAmount() == order.getComputers().size()) {
                        this.customer.tell(this.order, this.getSelf());
                        log().info("Order {} is ready for {}", order.getId(), this.sender());
                    }
                })
                .build();
    }

}
