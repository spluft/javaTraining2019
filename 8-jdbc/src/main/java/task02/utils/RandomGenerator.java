package task02.utils;

import java.util.*;

public class RandomGenerator {
    static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    private static List<String> types = new ArrayList<>();

    private static Random random = new Random();

    static final Set<String> identifiers = new HashSet<String>();

    static {
        types.add("VARCHAR(50)");
        types.add("INT");
        types.add("BOOLEAN");
    }

    public static String getRandomString() {
        return randomString(10);
    }

    public static String getRandomType() {
        return types.get(random.nextInt(types.size()));
    }

    public static int getRandomInt() {
        return random.nextInt(1_000_000);
    }

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public static String getValue(String s) {
        switch (s) {
            case "VARCHAR(50)": {
                return "'" + getRandomString() + "'";
            }
            case "INT": {
                return getRandomInt() + "";
            }
            case "BOOLEAN": {
                return String.valueOf(getRandomBoolean());
            }
            default: {
                throw new RuntimeException("Incorrect type");
            }
        }

    }

    private static String randomString(int length) {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
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