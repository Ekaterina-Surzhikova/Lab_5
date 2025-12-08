import java.util.*;

public class ListUtils {
    public static <T> List<T> symmetricDifference(List<T> list1, List<T> list2) {
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);

        Set<T> result = new HashSet<>();
        for (T item : set1) {
            if (!set2.contains(item)) {
                result.add(item);
            }
        }
        for (T item : set2) {
            if (!set1.contains(item)) {
                result.add(item);
            }
        }

        return new ArrayList<>(result);
    }
}