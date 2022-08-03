package org.bsmm.functions;

@FunctionalInterface
public interface ArithmeticOperations {
    double operation(double x, double y);

    default double operation() {
        return 0;
    }
}