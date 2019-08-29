package task05;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import task05.actors.MasterActor;
import task05.model.Computer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static akka.pattern.PatternsCS.ask;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ActorSystem system = ActorSystem.create("computer-factory");

        ActorRef masterActor = system.actorOf(Props.create(MasterActor.class));
        CompletableFuture<Object> userFuture = ask(masterActor, Computer.builder().order(1).build(), 1000).toCompletableFuture();
        System.out.println("Computer was build: " + userFuture.get().toString());
    }

}
