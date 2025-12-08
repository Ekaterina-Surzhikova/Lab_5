import java.util.*;
import java.util.stream.Collectors;

public class TextUtils {
    public static Set<Character> findVoicedConsonants(String text) {
        String voicedConsonants = "бвгджзлмнр";
        Map<Character, Integer> consonantCount = new HashMap<>();

        String[] words = text.toLowerCase().split("[^а-яё]+");

        for (String word : words) {
            if (!word.isEmpty()) {
                Set<Character> consonantsInWord = new HashSet<>();
                for (char c : word.toCharArray()) {
                    if (voicedConsonants.indexOf(c) != -1) {
                        consonantsInWord.add(c);
                    }
                }
                for (char c : consonantsInWord) {
                    consonantCount.put(c, consonantCount.getOrDefault(c, 0) + 1);
                }
            }
        }

        return consonantCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}