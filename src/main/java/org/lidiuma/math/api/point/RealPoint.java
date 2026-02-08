package org.lidiuma.math.api.point;

import org.lidiuma.math.api.vector.Vector;
import java.util.function.UnaryOperator;

public interface RealPoint<
        N,
        P extends RealPoint<N, P, V>,
        V extends Vector<V, N>> extends Point<N, P, V> {

    N distance(P point);

    N lerp(P target, N alpha);

    N interpolate(P target, N alpha, UnaryOperator<N> interpolator);
}
