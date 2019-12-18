package todoList.configuration;

import org.mariadb.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DSConfiguration {

    @Bean
    Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/todoDb", "root", "123456");
        return connection;
    }

}
