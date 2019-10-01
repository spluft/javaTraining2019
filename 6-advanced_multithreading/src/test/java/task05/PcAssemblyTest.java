package task05;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task05.actors.MasterActor;
import task05.model.Computer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static akka.pattern.PatternsCS.ask;

public class PcAssemblyTest {

    @Test
    public void factorialFJPTest() throws ExecutionException, InterruptedException {
        String comp = "Computer(order=1, " +
                "cpu=ComputerPart(status=true, " +
                "type=CPU, name=Intel Core i9), " +
                "motherboard=ComputerPart(status=true, " +
                "type=MB, name=ASUS), " +
                "hdd=ComputerPart(status=true, " +
                "type=HDD, name=500 Gb HDD), " +
                "ram=ComputerPart(status=true, " +
                "type=RAM, name=8 Gb DDR3))";
        ActorSystem system = ActorSystem.create("computer-factory");

        ActorRef masterActor = system.actorOf(Props.create(MasterActor.class));
        CompletableFuture<Object> userFuture = ask(masterActor, Computer.builder().order(1).build(), 1000).toCompletableFuture();

        Assertions.assertEquals(comp, userFuture.get().toString());
    }
}
