package org.lidiuma.math.api.tuple;

public interface UnaryTuple2<N> extends UnaryTuple1<N> {

    N y();

    @Override
    default int dimension() {
        return 2;
    }

    @Override
    default N component(int index) {
        return switch (index) {
            case 0 -> x();
            case 1 -> y();
            default -> throw new IndexOutOfBoundsException("Could not get component at index " + index + " since the dimension is " + dimension() + ".");
        };
    }
}
