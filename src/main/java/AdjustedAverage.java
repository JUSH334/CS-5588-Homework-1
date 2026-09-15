import java.util.List;

public final class AdjustedAverage {
    private AdjustedAverage() {
    }

    public static double adjustedAverage(List<Integer> values) {
        if (values == null || values.size() < 3) {
            throw new IllegalArgumentException("At least three values are required");
        }

        int minimumIndex = 0;
        int maximumIndex = 0;
        long sum = 0;

        for (int index = 0; index < values.size(); index++) {
            Integer value = values.get(index);
            if (value == null) {
                throw new IllegalArgumentException("Values cannot contain null");
            }

            sum += value;
            if (value < values.get(minimumIndex)) {
                minimumIndex = index;
            }
            if (value > values.get(maximumIndex)) {
                maximumIndex = index;
            }
        }

        return (double) (sum - values.get(minimumIndex) - values.get(maximumIndex))
                / (values.size() - 2);
    }
}
