package todoList.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class User {

    private Integer id;
    @Size(min = 3, max = 10, message = "wrong size")
    @NotNull(message = "login can not be empty")
    private String login;
    @Size(min = 3, max = 10)
    private String password;

    public User() {
    }

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
