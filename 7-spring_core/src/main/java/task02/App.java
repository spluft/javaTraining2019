package task02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task02.configs.Config;
import task02.services.EmulationService;

import java.io.IOException;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        EmulationService emulationService = context.getBean(EmulationService.class);

        emulationService.start(context);
    }
}