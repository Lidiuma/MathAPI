/*
 * Copyright (c) 2026 Xasmedy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lidiuma.math.api.traits.vector;

import java.util.function.UnaryOperator;
import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.vector.Vector;
import org.lidiuma.math.api.traits.FloatingNumerical;
import org.lidiuma.math.api.traits.Interpolatable;

public interface FloatingVectorOps<
        V extends Vector<N>,
        A extends Angle<N>,
        N> extends VectorOps<V, N>, Interpolatable<V, N>, FloatingNumerical<V> {

    /// @return the angle between the `v1` vector and the `v2` vector.
    A angle(V v1, V v2);

    boolean epsilonEquals(V v1, V v2, N epsilon);

    @Override
    FloatingNumerical<N> scalarOps();

    /// @return the Euclidean distance between `v1` and `v2`.
    default N distance(V v1, V v2) {
        return scalarOps().sqrt(distanceSquared(v1, v2));
    }

    /// @return the length of `vector`.
    default N length(V vector) {
        return scalarOps().sqrt(lengthSquared(vector));
    }

    /// @return a vector with the same direction as `vector` but scaled to the provided `length`.
    default V withLength(V vector, N length) {
        return withMagnitude(vector, length, length(vector));
    }

    /// @return a vector with its length limited to `limit`.
    default V withLimit(V vector, N limit) {
        final N current = length(vector);
        if (scalarOps().lessThanEqual(current, limit)) return vector;
        return withMagnitude(vector, limit, current);
    }

    /// @return a normalized vector with length 1 in the same direction as the provided `vector`.
    /// @param vector should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalizeOrElse] can be used.
    default V normalize(V vector) {
        return withLength(vector, scalarOps().one());
    }

    /// Similar to [#normalize] but when the length of `vector` is close to or is zero,
    /// the `fallback` vector is returned.
    /// @param fallback the value to use when the vector is close to zero.
    /// @return a normalized vector with length 1 in the same direction as `vector`, or the `fallback` vector.
    default V normalizeOrElse(V vector, N epsilon, V fallback) {
        if (epsilonEquals(vector, zero(), epsilon)) return fallback;
        return normalize(vector);
    }

    @Override
    default V abs(V vector) {
        return VectorOps.super.abs(vector);
    }

    @Override
    default V interpolate(V start, V end, N alpha, UnaryOperator<N> easing) {

        final var witness = scalarOps();
        final N eased = easing.apply(alpha);
        final N invAlpha = witness.subtract(witness.one(), eased);

        final V invStart = multiply(start, invAlpha);
        final V invEnd = multiply(end, eased);

        return add(invStart, invEnd);
    }

    private V withMagnitude(V vector, N wanted, N current) {
        final N scalar = scalarOps().divide(wanted, current);
        return multiply(vector, scalar);
    }
}
