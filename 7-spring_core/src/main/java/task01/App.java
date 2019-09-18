package task01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task01.services.EmulationService;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        EmulationService emulationService = context.getBean("emulationService", EmulationService.class);

        emulationService.start(context);
    }
}