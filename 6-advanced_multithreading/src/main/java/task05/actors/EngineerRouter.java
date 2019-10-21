package task05.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.dispatch.BoundedMessageQueueSemantics;
import akka.dispatch.RequiresMessageQueue;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import task05.model.Computer;
import task05.model.OrderMessage;

import java.util.ArrayList;
import java.util.List;

public class EngineerRouter extends AbstractLoggingActor implements RequiresMessageQueue<BoundedMessageQueueSemantics> {
    private Router router;

    {
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActorRef ref = getContext().actorOf(Props.create(EngineerActor.class).withDispatcher("my-dispatcher"));
            getContext().watch(ref);
            routees.add(new ActorRefRoutee(ref));
        }

        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderMessage.class, order -> {
                    for (int i = 0; i < order.getAmount(); i++) {
                        router.route(Computer.builder().order(i).build(), getSender());
                    }
                })
                .build();
    }
}
