import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SyllableCounterTest {
    @ParameterizedTest
    @CsvSource({
            "Sky, 1",
            "Rhythm, 1",
            "Book, 1",
            "Apple, 2",
            "Happy, 2",
            "Open, 2",
            "Animal, 3",
            "Chocolate, 3",
            "Computer, 3",
            "Celebration, 4",
            "Voluntary, 4",
            "California, 5",
            "Incomparable, 5",
            "Opportunity, 5",
            "Misunderstanding, 5",
            "Unintentionally, 6",
            "Misinterpretation, 6",
            "Incomprehensible, 6",
            "Disproportionately, 6",
            "Unconstitutionalities, 8",
            "Incomprehensibility, 8",
            "Internationalization, 8",
            "Antidisestablishmentarianism, 11"})
    void matchesReferenceCounter(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"book, 1", "strength, 1", "the, 1"})
    void countsOneSyllableWords(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"water, 2", "banana, 3", "elephant, 3"})
    void countsOneSyllablePerVowelGroup(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"whale, 1", "smile, 1", "machine, 2"})
    void ignoresSilentFinalE(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"table, 2", "purple, 2", "people, 2"})
    void countsConsonantPlusLeEndings(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"sky, 1", "style, 1", "happy, 2", "yellow, 2", "family, 3"})
    void treatsYAsAVowel(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"jumped, 1", "makes, 1", "clothes, 1"})
    void ignoresSilentEdAndEsEndings(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"wanted, 2", "needed, 2", "boxes, 2", "watches, 2"})
    void countsSpokenEdAndEsEndings(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"create, 2", "poem, 2", "being, 2", "violin, 3", "idea, 3"})
    void countsAdjacentVowelsThatAreSpokenSeparately(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @ParameterizedTest
    @CsvSource({"nation, 2", "region, 2", "special, 2", "during, 2", "goes, 1"})
    void countsAdjacentVowelsThatAreSpokenTogether(String word, int expectedSyllables) {
        assertEquals(expectedSyllables, SyllableCounter.countSyllables(word));
    }

    @Test
    void ignoresCapitalizationAndPunctuation() {
        assertEquals(2, SyllableCounter.countSyllables("WIN-DOW"));
    }

    @Test
    void rejectsNullWord() {
        assertThrows(IllegalArgumentException.class, () -> SyllableCounter.countSyllables(null));
    }

    @Test
    void rejectsBlankWord() {
        assertThrows(IllegalArgumentException.class, () -> SyllableCounter.countSyllables("   "));
    }

    @Test
    void rejectsWordWithoutLetters() {
        assertThrows(IllegalArgumentException.class, () -> SyllableCounter.countSyllables("123!"));
    }
}
