# CS 5588 Homework 1

This project contains Java implementations and JUnit tests for two homework exercises:

- `AdjustedAverage`: calculates the average of a list of integers after removing one occurrence of the minimum and one occurrence of the maximum value.
- `SyllableCounter`: counts the syllables in an English word from its spelling alone, with no table of known words.

## Project Contents

Each problem is a separate Maven project in its own folder, and each builds and tests on its own.

- `Problem2_AdjustedAverage/`: problem 2 (sections 2.1 and 2.2)
  - `src/main/java/AdjustedAverage.java`: implementation of the adjusted-average calculation.
  - `src/test/java/AdjustedAverageTest.java`: 18 tests covering normal adjusted averages, duplicate extremes, negative values, invalid input, and five integer-overflow boundary cases.
- `Problem3_SyllableCounter/`: problem 3 (sections 3.1 and 3.2)
  - `src/main/java/SyllableCounter.java`: syllable-counting implementation.
  - `src/test/java/SyllableCounterTest.java`: 61 tests covering the 23 supplied sample words plus the spelling rules the counter relies on, capitalization and punctuation, and invalid input.
- `pom.xml`: runs both problems from the project root. Each problem folder has its own `pom.xml` targeting Java 25 with JUnit 5.
- `Screenshots cs5588 hw1/`: screenshots documenting the project and test results.

Generated Maven output is placed in each folder's `target/` and is not part of the source files.

## How the Syllable Counter Works

The count starts as the number of vowel groups in the word, treating `y` as a vowel. Two sets of adjustments correct the cases where that count is wrong:

1. Silent endings are removed before counting: the final `e` of *whale*, the `-ed` of *jumped*, and the `-es` of *makes*. A consonant plus `-le` keeps its syllable, as in *table*.
2. Spelling patterns add or remove one syllable: adjacent vowels that are spoken separately add one (*Cal-i-for-ni-a*, *vi-o-lin*, *cre-ate*, *po-em*, *be-ing*, *i-de-a*), and `-ely` removes one (*dis-pro-por-tion-ate-ly*).

The counter agrees with https://syllablecounter.net/count on all 23 sample words and on roughly 60 additional common words used during development.

### Known Limitations

English spelling does not determine pronunciation, so a rule-based counter cannot be exact. The cases below are documented rather than patched with word-specific exceptions, since a table of known words is the error this project set out to remove.

**Vowel pairs the counter does not split.** *science* returns 1 instead of 2, *situation* 3 instead of 4, and *creating* 2 instead of 3.

**The `-ia` rule over-applies to names ending in *-ya*.** The rule adds a syllable wherever those letters appear outside the `c`, `s` and `t` exceptions. That is correct for Ca-li-for-ni-a and me-di-a, but wrong where the ending is spoken as a single *-ya*: *Virginia* returns 4 instead of 3, *Georgia* 3 instead of 2, and *Australia* 4 instead of 3. Spelling alone cannot separate the two cases, because the difference is stress rather than letters.

**The `-io` rule has the same problem after `n`.** *million* and *onion* return 3 instead of 2, and *opinion* 4 instead of 3.

**A final *e* is always treated as silent.** *recipe* returns 2 instead of 3.

Words whose accepted count varies by speaker, such as *every* and *everything*, follow the letter-by-letter count.

## Requirements

- Java 25 or newer
- Maven 3.9 or newer

## Run the Tests

Both problems, from the project root:

```bash
mvn clean test
```

Maven reports each problem separately: 18 tests for Problem 2 and 61 for Problem 3, 79 in total, with zero failures and zero errors.

One problem on its own, from inside its folder:

```bash
cd Problem2_AdjustedAverage && mvn clean test    # 18 tests
cd Problem3_SyllableCounter && mvn clean test    # 61 tests
```

## Main Methods

```java
AdjustedAverage.adjustedAverage(List<Integer> values)
SyllableCounter.countSyllables(String word)
```

Both methods are static utility methods and do not require creating an object.
