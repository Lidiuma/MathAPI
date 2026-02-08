package org.lidiuma.math.api.tuple;

public interface UnaryTuple1<N> extends UnaryTuple<N> {

    N x();

    @Override
    default int dimension() {
        return 1;
    }

    @Override
    default N component(int index) {
        if (index != 0) throw new IndexOutOfBoundsException("Could not get component at index " + index + " since the dimension is " + dimension() + ".");
        return x();
    }
}
