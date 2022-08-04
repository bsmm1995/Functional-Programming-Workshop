package org.bsmm.functions;

@FunctionalInterface
public interface FunctionalOperation {
    double operation(double x, double y);

    default double defaultMethod() {
        return 0;
    }
}