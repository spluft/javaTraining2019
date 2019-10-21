package task05;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task05.actors.ManagerActor;
import task05.model.Computer;
import task05.model.OrderMessage;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static akka.pattern.PatternsCS.ask;

public class PcAssemblyTest {

    @Test
    public void factorialFJPTest() throws ExecutionException, InterruptedException {
        String comp = "cpu=ComputerPart(status=true, " +
                "type=CPU, name=Intel Core i9), " +
                "motherboard=ComputerPart(status=true, " +
                "type=MB, name=ASUS), " +
                "hdd=ComputerPart(status=true, " +
                "type=HDD, name=500 Gb HDD), " +
                "ram=ComputerPart(status=true, " +
                "type=RAM, name=8 Gb DDR3))";

        ActorSystem system = ActorSystem.create("computer-factory");

        ActorRef managerActor = system.actorOf(Props.create(ManagerActor.class));
        LocalTime now = LocalTime.now();
        CompletableFuture<Object> userFuture = ask(managerActor, new OrderMessage(1, 2), 10000).toCompletableFuture();
        OrderMessage order = (OrderMessage) userFuture.join();
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(now, end);
        System.out.println(duration.getSeconds());
        System.out.println("Order was built: " + order.getId());
        system.terminate();

        for (Computer pc:order.getComputers()) {
            Assertions.assertTrue(pc.toString().contains(comp));
        }
    }
}
