package task01;

public class Utils {
    public static String concatenateWords(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    public static Long computeFactorial(int number) {
        if (number < 0){
            throw new IllegalArgumentException("Number shouldn't be less than zero!");
        }
        return number == 0 ? 1 : number * computeFactorial(number - 1);
    }

    public static void normalizeWord(String foo) {

    }
}
