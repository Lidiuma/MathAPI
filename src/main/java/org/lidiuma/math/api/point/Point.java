package org.lidiuma.math.api.point;

import org.lidiuma.math.api.tuple.UnaryTuple;
import org.lidiuma.math.api.vector.Vector;

public interface Point<
        N,
        P extends Point<N, P, V>,
        V extends Vector<N, V>> extends UnaryTuple<N> {

    P translate(V vector);

    V subtract(P point);

    N distance2(P point);

    P clamp(N min, N max);
}
