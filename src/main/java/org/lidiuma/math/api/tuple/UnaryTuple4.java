package org.lidiuma.math.api.tuple;

public interface UnaryTuple4<N> extends UnaryTuple3<N> {

    N w();

    @Override
    default int dimension() {
        return 4;
    }

    @Override
    default N component(int index) {
        return switch (index) {
            case 0 -> x();
            case 1 -> y();
            case 2 -> z();
            case 3 -> w();
            default -> throw new IndexOutOfBoundsException("Could not get component at index " + index + " since the dimension is " + dimension() + ".");
        };
    }
}
