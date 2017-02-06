package ru.academit.tortochakov.range;

public class Range {
    private double from;
    private double to;

    public Range(double a, double b) {
        this.from = a;
        this.to = b;
        if (a > b) {
            this.from = b;
            this.to = a;
        }
    }

    public double length() {
        return to - from;
    }

    public boolean isInside(double userNumber) {
        return userNumber >= from && userNumber <= to;
    }

    public Range intersection(Range range) {
        if (to < range.from || range.to < from) {
            return null;
        } else if (from < range.from && to < range.to) {
            return new Range(range.from, to);
        } else if (range.from <= from && range.to < to) {
            return new Range(from, range.to);
        } else if (from < range.from && to >= range.to) {
            return new Range(range.from, range.to);
        }
        return new Range(from, to);
    }

    public String toString() {
        return Double.toString(from) + ", " + Double.toString(to);
    }

    public Range[] union(Range range) {
        if (to < range.from && to < range.to) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        } else if (range.to < from && range.to < to) {
            return new Range[]{new Range(range.from, range.to), new Range(from, to)};
        } else if (from < range.from && to <= range.to) {
            return new Range[]{new Range(from, range.to)};
        } else if (range.from < from && range.to <= to) {
            return new Range[]{new Range(range.from, to)};
        } else if (from == range.from && range.to < to) {
            return new Range[]{new Range(from, to)};
        } else if (from == range.from && to < range.to) {
            return new Range[]{new Range(range.from, range.to)};
        } else if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, to)};
        }
        return new Range[]{new Range(range.from, range.to)};
    }

    public Range[] subtraction(Range range) {
        if (from == range.from && to == range.to) {
            return new Range[]{};
        } else if (to <= range.from && to < range.to) {
            return new Range[]{new Range(from, to)};
        } else if (range.to <= from && range.to < to) {
            return new Range[]{new Range(from, to)};
        } else if (from < range.from && to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        } else if (range.from < from && range.to < to) {
            return new Range[]{new Range(range.to, to)};
        } else if (from == range.from && range.to < to) {
            return new Range[]{new Range(range.to, to)};
        } else if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        } else if (from == range.from && to < range.to) {
            return new Range[]{};
        } else if (range.from < from && range.to == to) {
            return new Range[]{};
        }
        return new Range[]{};
    }
}