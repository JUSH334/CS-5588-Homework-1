# CS 5588 HW1 — Report Fixes

Notes for updating the homework report after the code fixes of September 17, 2026.

## What changed in the code

Three fixes were applied to the repository, and all 79 tests pass. Nothing in `AdjustedAverage.java` needed to change, since it was already correct.

**The five overflow tests are back.** `AdjustedAverageTest.java` had dropped to 13 tests, even though the 2.2 screenshot in the report showed 18. The five overflow cases now in the file are: all `Integer.MAX_VALUE`, all `Integer.MIN_VALUE`, opposite extremes, a 1000-element list of `Integer.MAX_VALUE`, and extreme values surrounding moderate ones.

**The hardcoded word table is gone.** `SyllableCounter.java` held a map of all 23 sample words to their answers, so the 3.2 tests were checking the table rather than the algorithm. With the table removed, the original algorithm was wrong on three sample words: California (4), Disproportionately (7) and Antidisestablishmentarianism (10). The counter now works from spelling alone and gets all 23 right on its own.

**The 3.1 tests are back.** `SyllableCounterTest.java` had lost every test of the algorithm itself. It now covers one-syllable words, one syllable per vowel group, silent final e, consonant plus -le, y as a vowel, silent and spoken -ed and -es endings, and vowel pairs spoken separately (create, poem, being) versus together (nation, region, special).

| Test class | Tests | Failures | Errors |
| --- | --- | --- | --- |
| AdjustedAverageTest | 18 | 0 | 0 |
| SyllableCounterTest | 61 | 0 | 0 |
| Total | 79 | 0 | 0 |

## Section 2.1 — Adjusted Average

The "13 JUnit 5 tests" figure stays correct here, because the five overflow tests belong to 2.2.

The only change needed is the discussion of AI-generated errors. Installing Java is not an error the AI made, so replace that bullet with something the grader can score:

> The generated implementation was correct, but the generated tests only covered ordinary lists. I added cases for duplicate minimums and maximums, all-equal values, mixed signs, order independence and null elements, since an off-by-one in the removal logic would show up in exactly those cases.

If you would rather not claim an error that did not happen, say so plainly instead: the AI produced correct code on the first attempt, and name the tests you added to confirm it.

## Section 2.2 — Overflow

The AI's conclusion was right: overflow is impossible. The justification is stronger with the arithmetic shown, so replace the response paragraph with this:

> A Java list holds at most `Integer.MAX_VALUE` elements, and each element's magnitude is at most 2^31. The largest possible total is therefore below 2^31 x 2^31 = 2^62, which fits in a `long` (maximum 2^63 - 1). The sum is accumulated in a `long`, and the minimum and maximum are subtracted in `long` arithmetic, so no intermediate value can overflow. The result converts to `double` only at the final division.

Discussion of AI-generated errors and fixes:

> The AI's answer was correct but untested, so I asked it to prove the claim in code. The five overflow tests all pass against the unchanged implementation: all `Integer.MAX_VALUE`, all `Integer.MIN_VALUE`, opposite extremes, a 1000-element list of `Integer.MAX_VALUE`, and extreme values surrounding moderate ones. A later version of the file had lost these tests, which I restored and reran.

That last sentence is worth keeping. The tests were missing from the submitted code while the report showed 18 tests, and naming the slip is better than leaving the grader to find it.

## Section 3.1 — Syllable Counter

Two numbers need correcting here. The response says "28 tests passed"; the run now reports 61 tests in `SyllableCounterTest` and 79 overall.

Replace the list of eight test categories with what the file actually covers: one-syllable words, one syllable per vowel group, silent final e, consonant plus -le endings, y as a vowel, silent -ed and -es endings, spoken -ed and -es endings, adjacent vowels spoken separately, adjacent vowels spoken together, capitalization and punctuation, null input, blank input, and input with no letters.

Describe the counter itself in a sentence or two, since the grader will look for an algorithm rather than a word list:

> The count starts as the number of vowel groups in the word, treating y as a vowel. Silent endings are removed first: the final e of whale, the -ed of jumped, and the -es of makes, while a consonant plus -le keeps its syllable as in table. Spelling patterns then adjust the count, adding a syllable for adjacent vowels that are spoken separately and removing one for -ely.

Discussion of AI-generated errors and fixes:

> The first implementation counted vowel groups and subtracted a silent final e, and it was wrong on common words: whale and smile came back as 2, jumped and makes as 2, and create, poem and being as 1. I added rules for silent -ed and -es endings, restricted the -le exception to a consonant plus -le, and added patterns for adjacent vowels that are spoken separately. Each of those rules has its own test.

## Section 3.2 — Sample words

This section needs the most work. The current corrections list calls five values corrections, but four of them were given as ranges, and picking a number inside a range corrects nothing.

| Word | Assignment | Used | Is it a correction? |
| --- | --- | --- | --- |
| California | 4 | 5 | Yes — the assignment's value is wrong |
| Unconstitutionalities | 7 or more | 8 | No — an exact value inside the range |
| Incomprehensibility | 7 or more | 8 | No — an exact value inside the range |
| Internationalization | 7 or more | 8 | No — an exact value inside the range |
| Antidisestablishmentarianism | 10 or more | 11 | No — an exact value inside the range |

Rewrite the list with the syllable breaks, which is the justification the assignment asks for:

> California: the assignment lists 4, but the word divides as Ca-li-for-ni-a, so I corrected it to 5.
> Unconstitutionalities (un-con-sti-tu-tion-al-i-ties), Incomprehensibility (in-com-pre-hen-si-bil-i-ty) and Internationalization (in-ter-na-tion-al-i-za-tion) were given as "7 or more"; each is exactly 8.
> Antidisestablishmentarianism (an-ti-dis-es-tab-lish-men-tar-i-an-ism) was given as "10 or more"; it is exactly 11.
> Unintentionally, Misinterpretation, Incomprehensible and Disproportionately were given as "6 or more"; each is exactly 6.

The discussion currently says there were no errors to fix. The real error belongs here, and it is the strongest item in the writeup now that you have found and fixed it:

> To make these tests pass, the AI had hardcoded all 23 sample words and their answers in a lookup table inside `SyllableCounter`, so the tests were checking the table rather than the algorithm. With the table removed, the algorithm was wrong on California (4), Disproportionately (7) and Antidisestablishmentarianism (10). I replaced the table with spelling rules: adjacent vowels spoken separately add a syllable, which covers the "ia" in Ca-li-for-ni-a and an-ti-dis-es-tab-lish-men-tar-i-an-ism, and "-ely" removes one, which covers dis-pro-por-tion-ate-ly. The counter now matches all 23 reference counts from spelling alone, and agrees with the reference on roughly 60 additional common words used while testing.

A short honesty note is worth adding, since no rule-based counter is exact:

> English spelling does not fully determine pronunciation, so the counter is still wrong on words like recipe (2 instead of 3), science (1 instead of 2) and creating (2 instead of 3). These are documented in the README.

## Screenshots to retake

Every screenshot in the report predates these fixes, and the test counts in them no longer match the code. Run `mvn clean test` from the project root and capture the output. One run produces the numbers for all four sections.

| Report section | What the screenshot must show |
| --- | --- |
| 2.1 | The `AdjustedAverageTest` line and BUILD SUCCESS. The current run reports 18 tests for that class. If you want it to show 13 as the report text says, capture it before adding the overflow tests, or note that 18 includes the five from 2.2. |
| 2.2 | `Tests run: 18, Failures: 0, Errors: 0` for `AdjustedAverageTest`, with the five overflow test names visible if your terminal shows them. |
| 3.1 | `Tests run: 61` for `SyllableCounterTest`, plus the totals line. |
| 3.2 | The full run: `Tests run: 79, Failures: 0, Errors: 0` and BUILD SUCCESS. |

One screenshot is missing rather than stale. Section 3.2 asks you to justify any corrected syllable count, and California is the one value you corrected. Capture syllablecounter.net showing California as 5 and place it beside the corrections list. The site's result for Antidisestablishmentarianism is worth capturing too, since 11 is the value your tests assert.

Make sure the command and the working directory are visible in each terminal capture, so the grader can tell the runs are real and current.

## Before you submit

**Package the code as the assignment asks.** It calls for a single zip file with each problem's source in its own sub-folder. The repository is one flat Maven project, and the report links to GitHub instead. Either split the sources into `problem2/` and `problem3/` folders inside the zip, or include the Maven project and explain the layout in one line. The GitHub link can stay as an extra.

**Paste the real prompts and responses.** The current writeup reads as your summary of what the AI did. The checklist asks for prompts and responses, so paste the actual text or add screenshots of the conversation.

**Rename the file.** The PDF is named "cs 5558 hw1"; the course is CS 5588.

**Check the repository is pushed.** The report points graders at the GitHub link, so the fixed code needs to be there, not only on your machine.
