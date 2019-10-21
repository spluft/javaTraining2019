package task05;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import task05.actors.ManagerActor;
import task05.model.OrderMessage;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static akka.pattern.PatternsCS.ask;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ActorSystem system = ActorSystem.create("computer-factory");

        ActorRef managerActor = system.actorOf(Props.create(ManagerActor.class));
        LocalTime now = LocalTime.now();
        CompletableFuture<Object> userFuture = ask(managerActor, new OrderMessage(1, 1_000_000), 10000).toCompletableFuture();
        OrderMessage order = (OrderMessage) userFuture.join();
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(now, end);
        System.out.println(duration.getSeconds());
        System.out.println("Order was built: " + order.getId());
        system.terminate();
    }

}
