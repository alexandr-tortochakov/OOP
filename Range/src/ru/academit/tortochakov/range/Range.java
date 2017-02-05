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
        return Math.abs(to - from);
    }

    public boolean isInside(double userNumber) {
        return userNumber > from && userNumber < to;
    }

    public Range intersection(Range range) {
        if (to < range.from || range.to < from) {
            return null;
        } else if (from < range.from && to < range.to) {
            return new Range(range.from, to);
        } else if (range.from < from && range.to < to) {
            return new Range(from, range.to);
        } else if (from < range.from && to > range.to) {
            return new Range(range.from, range.to);
        }
        return new Range(from, to);
    }

    public String toString() {
        return Double.toString(from) + ", " + Double.toString(to);
    }

    public Range[] merge(Range range) {
        if (to < range.from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        } else if (range.to < from) {
            return new Range[]{new Range(range.from, range.to), new Range(from, to)};
        } else if (from < range.from && to < range.to) {
            return new Range[]{new Range(from, range.to)};
        } else if (range.from < from && range.to < to) {
            return new Range[]{new Range(range.from, to)};
        } else if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, to)};
        }
        return new Range[]{new Range(range.from, range.to)};
    }

    public Range[] subtraction(Range range) {
        if (to < range.from || range.to < from) {
            return null;
        } else if (from > range.from && to < range.to) {
            return null;
        } else if (from < range.from && to < range.to) {
            return new Range[]{new Range(from, range.from)};
        } else if (range.from < from && range.to < to) {
            return new Range[]{new Range(range.from, from)};
        } else if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }
        return new Range[]{new Range(range.from, from), new Range(to, range.to)};
    }
}