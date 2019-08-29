package task05.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import task05.enums.TypeMessage;
import task05.model.ComputerPart;

import static task05.enums.TypeMessage.*;

public class SellerActor extends AbstractLoggingActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props(String groupId) {
        return Props.create(SellerActor.class, groupId);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TypeMessage.class, type -> {
                    ComputerPart computerPart = ComputerPart.builder().build();
                    switch (type) {
                        case MOTHERBOARD_MODEl:
                            computerPart.setName("ASUS");
                            computerPart.setType(MOTHERBOARD_MODEl);
                            computerPart.setStatus(true);
                            break;
                        case CPU_MODEl:
                            computerPart.setName("Intel Core i9");
                            computerPart.setType(CPU_MODEl);
                            computerPart.setStatus(true);
                            break;
                        case HDD_MODEl:
                            computerPart.setName("500 Gb HDD");
                            computerPart.setType(HDD_MODEl);
                            computerPart.setStatus(true);
                            break;
                        case RAM_MODEl:
                            computerPart.setName("8 Gb DDR3");
                            computerPart.setType(RAM_MODEl);
                            computerPart.setStatus(true);
                            break;
                        default:
                            computerPart.setStatus(false);
                    }
                    this.getSender().tell(computerPart, this.self());
                    log.info("Computer part was sended: {}", computerPart.toString());
                })
                .matchAny(message -> log.info(this.self().toString() + " received unknown message"))
                .build();
    }
}
