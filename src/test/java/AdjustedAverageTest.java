import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class AdjustedAverageTest {
    @Test
    void removesOneMinimumAndMaximum() {
        assertEquals(5.0, AdjustedAverage.adjustedAverage(List.of(1, 5, 9)), 0.000001);
    }

    @Test
    void calculatesFractionalAverage() {
        assertEquals(2.5, AdjustedAverage.adjustedAverage(List.of(1, 2, 3, 4)), 0.000001);
    }

    @Test
    void handlesDuplicateMinimums() {
        assertEquals(2.5, AdjustedAverage.adjustedAverage(List.of(1, 1, 4, 7)), 0.000001);
    }

    @Test
    void handlesDuplicateMaximums() {
        assertEquals(5.0, AdjustedAverage.adjustedAverage(List.of(2, 3, 7, 7)), 0.000001);
    }

    @Test
    void handlesAllEqualValues() {
        assertEquals(6.0, AdjustedAverage.adjustedAverage(List.of(6, 6, 6, 6, 6)), 0.000001);
    }

    @Test
    void handlesNegativeValues() {
        assertEquals(-5.0, AdjustedAverage.adjustedAverage(List.of(-10, -5, 0)), 0.000001);
    }

    @Test
    void handlesMixedSigns() {
        assertEquals(1.0 / 3.0, AdjustedAverage.adjustedAverage(List.of(-10, -2, 0, 3, 10)), 0.000001);
    }

    @Test
    void handlesZeros() {
        assertEquals(0.0, AdjustedAverage.adjustedAverage(List.of(0, 0, 0, 5)), 0.000001);
    }

    @Test
    void doesNotDependOnInputOrder() {
        assertEquals(
                AdjustedAverage.adjustedAverage(List.of(2, 8, 4, 10, 6)),
                AdjustedAverage.adjustedAverage(Arrays.asList(10, 6, 2, 8, 4)),
                0.000001);
    }

    @Test
    void supportsAListLargerThanThreeValues() {
        assertEquals(4.0, AdjustedAverage.adjustedAverage(List.of(1, 2, 3, 4, 5, 6, 100)), 0.000001);
    }

    @Test
    void rejectsNullList() {
        assertThrows(IllegalArgumentException.class, () -> AdjustedAverage.adjustedAverage(null));
    }

    @Test
    void rejectsListsWithFewerThanThreeValues() {
        assertThrows(IllegalArgumentException.class, () -> AdjustedAverage.adjustedAverage(List.of(1, 2)));
    }

    @Test
    void rejectsNullElements() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AdjustedAverage.adjustedAverage(Arrays.asList(1, null, 3)));
    }
}
