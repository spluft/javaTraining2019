package task05.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import task05.enums.TypeMessage;
import task05.model.Computer;
import task05.model.ComputerPart;
import task05.service.AssemblyService;

import java.util.concurrent.CompletableFuture;

import static akka.pattern.PatternsCS.ask;

public class EngineerActor extends AbstractLoggingActor {
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
                                AssemblyService.addMotherboard(computer, (ComputerPart) mbFuture.join());
                                AssemblyService.addCpu(computer, (ComputerPart) cpuFuture.join());
                                AssemblyService.addRam(computer, (ComputerPart) ramFuture.join());
                                AssemblyService.addHdd(computer, (ComputerPart) hddFuture.join());
                                return computer;
                            });
                    Computer pc = pcFuture.join();
                    getSender().tell(pc, this.self());
                    log().info("Computer was built: {} for {}", computer.toString(), this.sender());
                })
                .build();
    }

}