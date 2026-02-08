package org.lidiuma.math.api.tuple;

public interface UnaryTuple<N> {

    /// @return The amount of elements this tuple has.
    int dimension();

    /// Gets the component of the tuple at the specified index.\
    /// Taking `Tuple2` as an example:
    /// - Index `0` returns `x()`
    /// - Index `1` returns `y()`
    /// - While index `2` throws {@link IndexOutOfBoundsException}.
    /// @return the component at the provided index.
    N component(int index) throws IndexOutOfBoundsException;
}
