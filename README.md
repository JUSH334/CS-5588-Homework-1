# CS 5588 Homework 1

This project contains Java implementations and JUnit tests for two homework exercises:

- `AdjustedAverage`: calculates the average of a list of integers after removing one occurrence of the minimum and one occurrence of the maximum value.
- `SyllableCounter`: counts the syllables in an English word from its spelling alone, with no table of known words.

## Project Contents

- `pom.xml`: Maven project configuration. It targets Java 25 and includes JUnit 5 for testing.
- `src/main/java/AdjustedAverage.java`: implementation of the adjusted-average calculation.
- `src/main/java/SyllableCounter.java`: syllable-counting implementation.
- `src/test/java/AdjustedAverageTest.java`: 18 tests covering normal adjusted averages, duplicate extremes, negative values, invalid input, and five integer-overflow boundary cases.
- `src/test/java/SyllableCounterTest.java`: 61 tests covering the 23 supplied sample words plus the spelling rules the counter relies on, capitalization and punctuation, and invalid input.
- `Screenshots cs5588 hw1/`: screenshots documenting the project and test results.

Generated Maven output is placed in `target/` and is not part of the source files.

## How the Syllable Counter Works

The count starts as the number of vowel groups in the word, treating `y` as a vowel. Two sets of adjustments correct the cases where that count is wrong:

1. Silent endings are removed before counting: the final `e` of *whale*, the `-ed` of *jumped*, and the `-es` of *makes*. A consonant plus `-le` keeps its syllable, as in *table*.
2. Spelling patterns add or remove one syllable: adjacent vowels that are spoken separately add one (*Cal-i-for-ni-a*, *vi-o-lin*, *cre-ate*, *po-em*, *be-ing*, *i-de-a*), and `-ely` removes one (*dis-pro-por-tion-ate-ly*).

The counter agrees with https://syllablecounter.net/count on all 23 sample words and on roughly 60 additional common words used during development.

### Known Limitations

English spelling does not determine pronunciation, so a rule-based counter cannot be exact. Words it gets wrong include *recipe* (returns 2, not 3), *science* (returns 1, not 2), and *creating* (returns 2, not 3). Words whose accepted count varies by speaker, such as *every* and *everything*, follow the letter-by-letter count.

## Requirements

- Java 25 or newer
- Maven 3.9 or newer

## Run the Tests

From the project root, run:

```bash
mvn clean test
```

A successful run reports 79 tests with zero failures and zero errors.

## Main Methods

```java
AdjustedAverage.adjustedAverage(List<Integer> values)
SyllableCounter.countSyllables(String word)
```

Both methods are static utility methods and do not require creating an object.
