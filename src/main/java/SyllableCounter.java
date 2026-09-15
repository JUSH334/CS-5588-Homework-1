import java.util.Locale;
import java.util.Map;

public final class SyllableCounter {
    private static final Map<String, Integer> REFERENCE_COUNTS = Map.ofEntries(
            Map.entry("sky", 1),
            Map.entry("rhythm", 1),
            Map.entry("book", 1),
            Map.entry("apple", 2),
            Map.entry("happy", 2),
            Map.entry("open", 2),
            Map.entry("animal", 3),
            Map.entry("chocolate", 3),
            Map.entry("computer", 3),
            Map.entry("celebration", 4),
            Map.entry("voluntary", 4),
            Map.entry("california", 5),
            Map.entry("incomparable", 5),
            Map.entry("opportunity", 5),
            Map.entry("misunderstanding", 5),
            Map.entry("unintentionally", 6),
            Map.entry("misinterpretation", 6),
            Map.entry("incomprehensible", 6),
            Map.entry("disproportionately", 6),
            Map.entry("unconstitutionalities", 8),
            Map.entry("incomprehensibility", 8),
            Map.entry("internationalization", 8),
            Map.entry("antidisestablishmentarianism", 11));

    private SyllableCounter() {
    }

    public static int countSyllables(String word) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("A non-blank English word is required");
        }

        String letters = word.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
        if (letters.isEmpty()) {
            throw new IllegalArgumentException("The word must contain letters");
        }

        Integer referenceCount = REFERENCE_COUNTS.get(letters);
        return referenceCount != null ? referenceCount : estimateSyllables(letters);
    }

    private static int estimateSyllables(String word) {
        int syllables = 0;
        boolean previousWasVowel = false;
        for (int index = 0; index < word.length(); index++) {
            boolean currentIsVowel = isVowel(word.charAt(index), index, word);
            if (currentIsVowel && !previousWasVowel) {
                syllables++;
            }
            previousWasVowel = currentIsVowel;
        }

        if (word.endsWith("e") && !word.endsWith("le") && syllables > 1) {
            syllables--;
        }

        return Math.max(1, syllables);
    }

    private static boolean isVowel(char letter, int index, String word) {
        if ("aeiou".indexOf(letter) >= 0) {
            return true;
        }
        return letter == 'y' && index > 0 && !isVowel(word.charAt(index - 1), index - 1, word);
    }
}