package com.company;

public class Tuple<X, Y> {
    public X x;
    public Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[ %s, %s ]", this.x, this.y);
    }
}