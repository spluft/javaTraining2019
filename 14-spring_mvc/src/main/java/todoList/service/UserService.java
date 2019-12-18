package todoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import todoList.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserService {

    @Autowired
    private Connection connection;

    public User save(User user) {
        User checkedUser = getUser(user);
        if (checkedUser == null) {
            try {
                PreparedStatement newUser = connection.prepareStatement("insert into users (login, password) values (?,?)");
                newUser.setObject(1, user.getLogin());
                newUser.setObject(2, user.getPassword());
                newUser.execute();
                return user;
            } catch (SQLException e) {
                return null;
            }
        } else {
            return checkedUser;
        }

    }

    public User getUser(User user) {
        User nullUser = null;

        try {
            PreparedStatement getUserStatement = connection.prepareStatement("select * from users where login = ? and password = ?");
            getUserStatement.setObject(1, user.getLogin());
            getUserStatement.setObject(2, user.getPassword());
            ResultSet rowUserFromDB = getUserStatement.executeQuery();

            if (rowUserFromDB.next()) {
                return user;
            }
        } catch (SQLException e) {
            return nullUser;
        }

        return nullUser;
    }

}
