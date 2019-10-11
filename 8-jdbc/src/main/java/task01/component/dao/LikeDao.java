package task01.component.dao;

import task01.entity.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class LikeDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String INSERT_LIKE = "INSERT INTO likes (postid, userid, timestamp) values (?, ?, ?)";

    @Autowired
    public LikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addLikes(final List<Like> likes) {
        jdbcTemplate.batchUpdate(INSERT_LIKE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Like like = likes.get(i);
                ps.setLong(1, like.getPostId());
                ps.setLong(2, like.getUserId());
                ps.setTimestamp(3, Timestamp.valueOf(like.getTimestamp()));
            }

            @Override
            public int getBatchSize() {
                return 500;
            }
        });
    }


}