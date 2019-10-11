package task01.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableCreator {

    public static final Logger log = LogManager.getLogger();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTables() {
        log.info("Deleting tables");
        jdbcTemplate.execute("DROP TABLE if exists users CASCADE;");
        jdbcTemplate.execute("DROP TABLE if exists friendships CASCADE;");
        jdbcTemplate.execute("DROP TABLE if exists posts CASCADE;");
        jdbcTemplate.execute("DROP TABLE if exists likes CASCADE;");

        log.info("Creating sequences");
        jdbcTemplate.execute("CREATE SEQUENCE users_id_seq\n" +
                "INCREMENT 1\n" +
                "START 1\n" +
                "MINVALUE 1\n" +
                "MAXVALUE 9223372036854775806\n" +
                "CACHE 1;");

        jdbcTemplate.execute("CREATE SEQUENCE posts_id_seq\n" +
                "INCREMENT 1\n" +
                "START 1\n" +
                "MINVALUE 1\n" +
                "MAXVALUE 9223372036854775806\n" +
                "CACHE 1;");

        log.info("Creating users table...");
        jdbcTemplate.execute("CREATE TABLE users\n" +
                "(\n" +
                "name text NOT NULL,\n" +
                "surname text NOT NULL,\n" +
                "birthdate timestamp(4) NOT NULL,\n" +
                "id integer NOT NULL AUTO_INCREMENT,\n" +
                "CONSTRAINT users_pkey PRIMARY KEY (id)\n" +
                ");");

        log.info("Creating friendships table...");
        jdbcTemplate.execute("CREATE TABLE friendships\n" +
                "(\n" +
                "userid1 int,\n" +
                "userid2 int,\n" +
                "timestamp timestamp,\n" +
                "CONSTRAINT fk_1 FOREIGN KEY (userid1)\n" +
                "REFERENCES users (id)\n" +
                "ON UPDATE NO ACTION\n" +
                "ON DELETE NO ACTION,\n" +
                "CONSTRAINT fk_2 FOREIGN KEY (userid2)\n" +
                "REFERENCES users (id)\n" +
                "ON UPDATE NO ACTION\n" +
                "ON DELETE NO ACTION\n" +
                ");");

        log.info("Creating posts table...");
        jdbcTemplate.execute("CREATE TABLE posts\n" +
                "(\n" +
                "userid integer,\n" +
                "text text,\n" +
                "timestamp timestamp,\n" +
                "id integer AUTO_INCREMENT,\n" +
                "CONSTRAINT posts_pkey PRIMARY KEY (id),\n" +
                "CONSTRAINT fk_p_1 FOREIGN KEY (userid)\n" +
                "REFERENCES users (id)\n" +
                "ON UPDATE NO ACTION\n" +
                "ON DELETE NO ACTION\n" +
                ");");
        log.info("Creating likes table...");
        jdbcTemplate.execute(
                "CREATE TABLE likes\n" +
                        "(\n" +
                        "postid integer,\n" +
                        "userid integer,\n" +
                        "timestamp timestamp,\n" +
                        "CONSTRAINT fk_l_1 FOREIGN KEY (postid)\n" +
                        "REFERENCES posts (id)\n" +
                        "ON UPDATE NO ACTION\n" +
                        "ON DELETE NO ACTION,\n" +
                        "CONSTRAINT fk_l_2 FOREIGN KEY (userid)\n" +
                        "REFERENCES users (id)\n" +
                        "ON UPDATE NO ACTION\n" +
                        "ON DELETE NO ACTION\n" +
                        ");");

    }
}