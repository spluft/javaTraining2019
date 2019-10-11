package task01.component;

import task01.component.dao.FriendshipDao;
import task01.component.dao.LikeDao;
import task01.component.dao.PostDao;
import task01.component.dao.UserDao;
import task01.entity.Friendship;
import task01.entity.Like;
import task01.entity.Post;
import task01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task01.component.utils.DataGenerator;

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