# CS 5588 Homework 1

This project contains Java implementations and JUnit tests for two homework exercises:

- `AdjustedAverage`: calculates the average of a list of integers after removing one occurrence of the minimum and one occurrence of the maximum value.
- `SyllableCounter`: estimates the number of syllables in an English word. It includes the provided sample words and a fallback spelling-based estimate for other words.

## Project Contents

- `pom.xml`: Maven project configuration. It targets Java 25 and includes JUnit 5 for testing.
- `src/main/java/AdjustedAverage.java`: implementation of the adjusted-average calculation.
- `src/main/java/SyllableCounter.java`: syllable-counting implementation.
- `src/test/java/AdjustedAverageTest.java`: tests for normal adjusted averages, duplicate extremes, negative values, invalid input, and integer boundary values.
- `src/test/java/SyllableCounterTest.java`: tests for the supplied sample words, capitalization and punctuation, null input, and blank input.
- `Screenshots cs5588 hw1/`: screenshots documenting the project and test results.

Generated Maven output is placed in `target/` and is not part of the source files.

## Requirements

- Java 25 or newer
- Maven 3.9 or newer

## Run the Tests

From the project root, run:

```bash
mvn clean test
```

A successful run reports zero failures and zero errors for all project tests.

## Main Methods

```java
AdjustedAverage.adjustedAverage(List<Integer> values)
SyllableCounter.countSyllables(String word)
```

Both methods are static utility methods and do not require creating an object.
