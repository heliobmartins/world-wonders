package com.digitalday.dojounittest;

/**
 * Created by athila on 20/06/15.
 */

public class Calculator {

    public double sum(int a, int b) {
        validateOperators(a, b);
        return a + b;
    }

    public double substract(int a, int b) {
        validateOperators(a, b);
        return a - b;
    }

    public double divide(int a, int b) {
        validateOperators(a, b);
        return a / b;
    }

    public double multiply(int a, int b) {
        validateOperators(a, b);
        return a * b;
    }

    private void validateOperators(int a, int b) {
        if (a < -99 || a > 99 || b < -99 || b > 99) {
            throw new IllegalArgumentException("Arguments must have only two digits");
        }
    }
}
