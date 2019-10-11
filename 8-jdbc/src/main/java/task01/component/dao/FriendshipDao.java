package task01.component.dao;

import task01.entity.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class FriendshipDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_FRIENDSHIP = "INSERT INTO friendships (userid1, userid2, timestamp) values (?, ?, ?)";


    @Autowired
    public FriendshipDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addFriendships(List<Friendship> friendships) {
        jdbcTemplate.batchUpdate(INSERT_FRIENDSHIP, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Friendship friendship = friendships.get(i);
                ps.setLong(1, friendship.getUserId1());
                ps.setLong(2, friendship.getUserId2());
                ps.setTimestamp(3, Timestamp.valueOf(friendship.getTimestamp()));
            }

            @Override
            public int getBatchSize() {
                return 500;
            }
        });
    }
}