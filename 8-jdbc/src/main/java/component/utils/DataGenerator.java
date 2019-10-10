package component.utils;

import entity.Friendship;
import entity.Like;
import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataGenerator {

    private final RandomGenerator randomGenerator;
    private Random random = new Random();

    @Autowired
    public DataGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }


    public List<User> generateUsers() {
        return IntStream.range(0, 1000).mapToObj(i -> {
            User user = new User();
            user.setName(randomGenerator.getRandomName());
            user.setSurname(randomGenerator.getRandomSurname());
            user.setBirthDate(randomGenerator.getHardcodeDate());
            return user;
        }).collect(Collectors.toList());
    }

    public List<Friendship> generateFriendships(List<Long> userIds) {
        return IntStream.range(0, 70_000).mapToObj(i -> {
            Friendship friendship = new Friendship();
            friendship.setUserId1(userIds.get(random.nextInt(userIds.size())));
            friendship.setUserId2(userIds.get(random.nextInt(userIds.size())));
            friendship.setTimestamp(generateLocalDateTimeInRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31)));
            return friendship;
        }).collect(Collectors.toList());
    }

    public List<Post> generatePosts(List<Long> userIds) {
        return IntStream.range(0, 300_000).mapToObj(i -> {
            Post post = new Post();
            post.setText(randomGenerator.getPostText());
            post.setTimestamp(generateLocalDateTimeInRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31)));
            post.setUserId(userIds.get(random.nextInt(userIds.size())));
            return post;
        }).collect(Collectors.toList());
    }

    public List<Like> generateLikes(List<Long> userIds, List<Long> postIds) {
        return IntStream.range(0, 300_000).mapToObj(i -> {
            Like like = new Like();
            like.setPostId(postIds.get(random.nextInt(postIds.size())));
            like.setUserId(userIds.get(random.nextInt(userIds.size())));
            like.setTimestamp(generateLocalDateTimeInRange(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31)));
            return like;
        }).collect(Collectors.toList());

    }

    private LocalDateTime generateLocalDateTimeInRange(LocalDate from, LocalDate to) {
        long min = from.toEpochDay();
        long max = to.toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(min, max);

        return LocalDateTime.of(LocalDate.ofEpochDay(randomDay), LocalTime.now());
    }
}