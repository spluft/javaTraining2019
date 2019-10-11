package task01.component.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class RandomGenerator {
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    private String dateStart = "01/14/1980 09:29:58";
    private String dateEnd = "01/14/1995 09:29:58";

    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    private Random random = new Random();

    final Set<String> identifiers = new HashSet<String>();

    @Autowired
    public RandomGenerator() {
    }

    public String getRandomName() {
        return randomIdentifier();
    }

    public String getRandomSurname() {
        return randomIdentifier();
    }

    public LocalDateTime getHardcodeDate() {
        return LocalDateTime.of(1990, Month.APRIL, 4, 10, 25);
    }

    public String getPostText() {
        return "STUB POST";
    }

    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = random.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(random.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}