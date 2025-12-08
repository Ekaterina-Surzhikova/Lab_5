import java.util.List;

public class MeowUtils {
    public static void makeAllMeow(List<Meowable> meowables) {
        for (Meowable meowable : meowables) {
            meowable.meow();
        }
    }
}