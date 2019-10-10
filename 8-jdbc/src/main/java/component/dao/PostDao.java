package component.dao;

import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class PostDao {
    private final JdbcTemplate jdbcTemplate;


    private static final String INSERT_POSTS = "INSERT INTO posts (userid, text, timestamp) values (?, ?, ?)";

    @Autowired
    public PostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPosts(final List<Post> posts) {
        jdbcTemplate.batchUpdate(INSERT_POSTS, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Post post = posts.get(i);
                ps.setLong(1, post.getUserId());
                ps.setString(2, post.getText());
                ps.setTimestamp(3, Timestamp.valueOf(post.getTimestamp()));
            }

            @Override
            public int getBatchSize() {
                return 500;
            }
        });
    }

    public List<Long> getPostsIds() {
        return jdbcTemplate
                .query("SELECT id FROM posts", (resultSet, i) -> resultSet.getLong(1));
    }

}