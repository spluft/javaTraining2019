package task01;

import task01.component.dao.UserDao;
import task01.config.AppConfig;
import task01.component.DataFiller;
import task01.component.TableCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class App {
    public static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        TableCreator tableCreator = ctx.getBean(TableCreator.class);
        tableCreator.createTables();

        DataFiller dataFiller = ctx.getBean(DataFiller.class);
        dataFiller.addUsers();
        log.info("USERS have been initialized");

        dataFiller.addFriendships();
        log.info("Friendships have been initialized");

        dataFiller.addPosts();
        log.info("Posts have been initialized");

        dataFiller.addLikes();
        log.info("Likes have been initialized");

        UserDao userDao = ctx.getBean(UserDao.class);

        List<String> filteredUserNames = userDao.getFilteredUserNames();
        filteredUserNames.forEach(s -> log.info("Name of chosen Person {}", s));
    }
}