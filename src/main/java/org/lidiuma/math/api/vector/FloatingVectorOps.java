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

package org.lidiuma.math.api.vector;

import org.lidiuma.math.api.rotation.Angle;

public interface FloatingVectorOps<
        V extends Vector<N>,
        A extends Angle<N>,
        N> extends VectorOps<V, N> {

    /// @return the angle between the `v1` vector and the `v2` vector.
    A angle(V v1, V v2);

    /// @return the Euclidean distance between `v1` and `v2`.
    N distance(V v1, V v2);

    /// @return a vector with each component rounded up to the nearest integer.
    V ceil(V vector);

    /// @return a vector with each component rounded down to the nearest integer.
    V floor(V vector);

    /// @return the length of `vector`.
    N length(V vector);

    /// @return a vector with the same direction as `vector` but scaled to the provided `length`.
    default V withLength(V vector, N length) {
        return withMagnitude(vector, length, length(vector));
    }

    /// @return a vector with the same direction as `vector` but scaled to the provided `length` squared.
    V withLengthSquared(V vector, N lengthSquared);

    /// @return a vector with its length limited to `limit`.
    default V withLimit(V vector, N limit) {
        final N current = length(vector);
        if (scalarWitness().lessThanEqual(current, limit)) return vector;
        return withMagnitude(vector, limit, current);
    }

    /// @return a vector with its length squared limited to `limit` squared.
    V withLimitSquared(V vector, N limitSquared);

    /// @return a normalized vector with length 1 in the same direction as the provided `vector`.
    /// @param vector should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalizedOrElse] can be used.
    V normalized(V vector);

    /// Similar to [#normalized] but when the length of `vector` is close to or is zero,
    /// the `fallback` vector is returned.
    /// @param fallback the value to use when the vector is close to zero.
    /// @return a normalized vector with length 1 in the same direction as `vector`, or the `fallback` vector.
    V normalizedOrElse(V vector, V fallback);

    private V withMagnitude(V vector, N wanted, N current) {
        final N scalar = scalarWitness().divide(wanted, current);
        return multiply(vector, scalar);
    }
}
