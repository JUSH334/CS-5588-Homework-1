import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SyllableCounter {
    private static final String VOWELS = "aeiouy";
    private static final Pattern VOWEL_GROUP = Pattern.compile("[aeiouy]+");

    /**
     * Spellings where two adjacent vowels are pronounced as separate syllables,
     * so the vowel-group count is one too low.
     */
    private static final List<Pattern> EXTRA_SYLLABLES = List.of(
            Pattern.compile("(?<![cst])ia"),      // cal-i-for-ni-a, but not spe-cial or Rus-sia
            Pattern.compile("(?<![cgt])io"),      // vi-o-lin, but not na-tion or re-gion
            Pattern.compile("eate"),              // cre-ate
            Pattern.compile("[aeiouy]ing$"),      // be-ing, do-ing, fly-ing, but not dur-ing
            Pattern.compile("(?<![aeiou])oe(?!s?$)"), // po-em, but not toe or goes
            Pattern.compile("[aeiouy][^aeiouy]{1,2}ea$")); // i-de-a and ar-e-a, but not sea

    /**
     * Spellings where the vowel-group count is one too high.
     */
    private static final List<Pattern> FEWER_SYLLABLES = List.of(
            Pattern.compile("[^aeiou]ely$"));     // late-ly, dis-pro-por-tion-ate-ly

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

        int syllables = countVowelGroups(dropSilentEnding(letters))
                + countMatches(EXTRA_SYLLABLES, letters)
                - countMatches(FEWER_SYLLABLES, letters);

        return Math.max(1, syllables);
    }

    private static int countVowelGroups(String word) {
        Matcher matcher = VOWEL_GROUP.matcher(word);
        int groups = 0;
        while (matcher.find()) {
            groups++;
        }
        return groups;
    }

    /**
     * Removes endings that add letters but no syllable: the silent final e of
     * "whale", and the silent -ed and -es of "jumped" and "makes".
     */
    private static String dropSilentEnding(String word) {
        if (word.endsWith("le") && word.length() > 2 && !isVowel(word.charAt(word.length() - 3))) {
            return word; // ta-ble and ap-ple keep a syllable for the -le
        }
        if (word.endsWith("e")) {
            return word.substring(0, word.length() - 1);
        }
        if (word.endsWith("ed") && !endsWithSpokenEd(word)) {
            return word.substring(0, word.length() - 2);
        }
        if (word.endsWith("es") && !endsWithSpokenEs(word)) {
            return word.substring(0, word.length() - 2);
        }
        return word;
    }

    /** The -ed is spoken only after t or d, as in "wanted" and "needed". */
    private static boolean endsWithSpokenEd(String word) {
        if (word.length() < 3) {
            return true;
        }
        char previous = word.charAt(word.length() - 3);
        return previous == 't' || previous == 'd';
    }

    /** The -es is spoken after a hissing sound, as in "buses", "boxes" and "watches". */
    private static boolean endsWithSpokenEs(String word) {
        if (word.length() < 3) {
            return true;
        }
        char previous = word.charAt(word.length() - 3);
        if ("szxcgj".indexOf(previous) >= 0) {
            return true;
        }
        return previous == 'h' && word.length() > 3 && "cs".indexOf(word.charAt(word.length() - 4)) >= 0;
    }

    private static boolean isVowel(char letter) {
        return VOWELS.indexOf(letter) >= 0;
    }

    private static int countMatches(List<Pattern> patterns, String word) {
        int matches = 0;
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(word);
            while (matcher.find()) {
                matches++;
            }
        }
        return matches;
    }
}
