package org.lidiuma.math.api.tuple;

public interface UnaryTuple3<N> extends UnaryTuple2<N> {

    N z();

    @Override
    default int dimension() {
        return 3;
    }

    @Override
    default N component(int index) {
        return switch (index) {
            case 0 -> x();
            case 1 -> y();
            case 2 -> z();
            default -> throw new IndexOutOfBoundsException("Could not get component at index " + index + " since the dimension is " + dimension() + ".");
        };
    }
}
