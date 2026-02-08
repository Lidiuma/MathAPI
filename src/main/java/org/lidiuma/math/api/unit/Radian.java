package org.lidiuma.math.api.unit;

public interface Radian<N> {

    /*
    Implementations should implement factory methods to create the Radiant instance from at least ALL units present here.
    For example, I have the instance toDegree(), I must implement the static fromDegree().
     */

    N value();

    N toDegree();

    N toTurn();

    interface F32 extends Radian<Float> {}

    interface F64 extends Radian<Double> {}
}
