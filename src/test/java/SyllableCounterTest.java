import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

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
}
