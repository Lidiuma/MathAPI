package org.lidiuma.math.api.vector;

import java.util.function.UnaryOperator;

public interface RealVector<V extends RealVector<V, N>, N> extends Vector<V, N> {

    V ceil();

    V floor();

    N length();

    V withLength(N length);

    V withLength2(N length2);

    V limit(N limit);

    V limit2(N limit2);

    V normalize();

    N distance(V vector);

    V lerp(V target, N alpha);

    V interpolate(V target, N alpha, UnaryOperator<N> interpolator);

    boolean isUnit(N epsilon);

    boolean isCollinear(V vector, N epsilon);

    boolean isPerpendicular(V vector, N epsilon);

    boolean epsilonEquals(V vector, N epsilon);

    boolean isZero(N epsilon);
}
