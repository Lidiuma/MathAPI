package org.lidiuma.math.api;

public interface Numeric<N extends Numeric<N>> {

    N add(N other);

    N sub(N other);

    N mul(N other);

    N div(N other);

    boolean lt(N other);

    boolean ltEq(N other);

    boolean gt(N other);

    boolean gtEq(N other);
}
