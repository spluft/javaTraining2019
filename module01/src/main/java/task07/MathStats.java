package task07;

import java.util.Objects;

public class MathStats {
    private Integer sum = 0;
    private Long count = 0L;
    private Double average = 0.0;
    private Integer min = 0;
    private Integer max = 0;

    public MathStats() {
    }

    public MathStats(Integer sum, Long count, Double average, Integer min, Integer max) {
        this.sum = sum;
        this.count = count;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MathStats mathStats = (MathStats) o;

        if (!Objects.equals(sum, mathStats.sum)) return false;
        if (!Objects.equals(count, mathStats.count)) return false;
        if (!Objects.equals(average, mathStats.average)) return false;
        if (!Objects.equals(min, mathStats.min)) return false;
        return Objects.equals(max, mathStats.max);
    }

    @Override
    public int hashCode() {
        int result = sum != null ? sum.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (average != null ? average.hashCode() : 0);
        result = 31 * result + (min != null ? min.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MathStats{" +
                "sum=" + sum +
                ", count=" + count +
                ", average=" + average +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
