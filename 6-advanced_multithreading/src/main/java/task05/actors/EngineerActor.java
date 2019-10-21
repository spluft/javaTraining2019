package task05.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import task05.enums.TypeMessage;
import task05.model.Computer;
import task05.model.ComputerPart;

import java.util.concurrent.CompletableFuture;

import static akka.pattern.PatternsCS.ask;

public class EngineerActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    final ActorRef sellerActor = getContext().actorOf(Props.create(SellerActor.class), "seller-actor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Computer.class, computer -> {
                    CompletableFuture<Object> mbFuture = ask(sellerActor,
                            TypeMessage.MOTHERBOARD_MODEl, 100000).toCompletableFuture();
                    CompletableFuture<Object> cpuFuture = ask(sellerActor,
                            TypeMessage.CPU_MODEl, 100000).toCompletableFuture();
                    CompletableFuture<Object> ramFuture = ask(sellerActor,
                            TypeMessage.RAM_MODEl, 100000).toCompletableFuture();
                    CompletableFuture<Object> hddFuture = ask(sellerActor,
                            TypeMessage.HDD_MODEl, 100000).toCompletableFuture();

                    CompletableFuture<Computer> pcFuture =
                            CompletableFuture.allOf(mbFuture, cpuFuture, ramFuture, hddFuture).thenApply(v -> {
                                computer.setMotherboard((ComputerPart) mbFuture.join());
                                computer.setCpu((ComputerPart) cpuFuture.join());
                                computer.setRam((ComputerPart) ramFuture.join());
                                computer.setHdd((ComputerPart) hddFuture.join());
                                return computer;
                            });
                    Computer pc = pcFuture.join();
                    getSender().tell(pc, this.self());
                    log.info("Computer was built: {} for {}", computer.toString(), this.sender());
                })
                .build();
    }

}