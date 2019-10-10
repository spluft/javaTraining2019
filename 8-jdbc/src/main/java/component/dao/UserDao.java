package component.dao;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;


    private static final String INSERT_USER = "INSERT INTO users (name, surname, birthdate) values (?, ?, ?)";

    private static final String FILTER_USERS = "select name\n" +
            "from users\n" +
            "where id in (\n" +
            "select userid1\n" +
            "FROM friendships\n" +
            "group by userid1\n" +
            "having count(userid2) > 100)\n" +
            "and id in (select userid\n" +
            "from likes\n" +
            "group by userid, timestamp\n" +
            "having count(*) > 100 and timestamp BETWEEN '2025-03-01 00:00:00'\n" +
            "AND '2025-03-31 23:59:59');";

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUsers(final List<User> users) {
        jdbcTemplate.batchUpdate(INSERT_USER, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = users.get(i);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setTimestamp(3, Timestamp.valueOf(user.getBirthDate()));
            }

            @Override
            public int getBatchSize() {
                return 500;
            }
        });
    }

    public List<Long> getUserIds() {
        return jdbcTemplate
                .query("SELECT id FROM users", (resultSet, i) -> resultSet.getLong(1));
    }

    public List<String> getFilteredUserNames() {
        return jdbcTemplate.query(FILTER_USERS, (resultSet, i) -> resultSet.getString(1));
    }
}