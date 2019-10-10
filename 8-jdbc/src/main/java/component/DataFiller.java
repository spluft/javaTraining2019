package component;

import component.dao.FriendshipDao;
import component.dao.LikeDao;
import component.dao.PostDao;
import component.dao.UserDao;
import entity.Friendship;
import entity.Like;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import component.utils.DataGenerator;

import java.util.List;

@Component
public class DataFiller {

    private final UserDao userDao;

    private final FriendshipDao friendshipDao;

    private final PostDao postDao;

    private final LikeDao likeDao;

    private final DataGenerator dataGenerator;

    @Autowired
    public DataFiller(UserDao userDao, FriendshipDao friendshipDao,
                      PostDao postDao, LikeDao likeDao, DataGenerator dataGenerator) {
        this.userDao = userDao;
        this.friendshipDao = friendshipDao;
        this.postDao = postDao;
        this.likeDao = likeDao;
        this.dataGenerator = dataGenerator;
    }

    public void addUsers() {
        List<User> users = dataGenerator.generateUsers();
        for (int i = 0; i < users.size(); i += 1_000) {
            userDao.addUsers(users.subList(i, i + 1_000));
        }
    }

    public void addFriendships() {
        List<Friendship> friendships = dataGenerator.generateFriendships(userDao.getUserIds());
        for (int i = 0; i < friendships.size(); i += 1_000) {
            friendshipDao.addFriendships(friendships.subList(i, i + 1_000));
        }
    }

    public void addPosts() {
        List<Post> posts = dataGenerator.generatePosts(userDao.getUserIds());
        for (int i = 0; i < posts.size(); i += 1_000) {
            postDao.addPosts(posts.subList(i, i + 1_000));
        }
    }

    public void addLikes() {
        List<Like> likes = dataGenerator
                .generateLikes(userDao.getUserIds(), postDao.getPostsIds());
        for (int i = 0; i < likes.size(); i += 1_000) {
            likeDao.addLikes(likes.subList(i, i + 1_000));
        }
    }


}