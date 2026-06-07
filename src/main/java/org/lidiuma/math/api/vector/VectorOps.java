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

import org.lidiuma.math.api.*;

/// Standard Vector Operations.
///
/// All methods implemented from [OrderableNumerical] are implemented component-wise:
/// ```
/// Vector2 a, b;
/// a.multiply(b) -> Vector2.of(a.x() * b.x(), a.y() * b.y());
/// ```
public interface VectorOps<V extends Vector<N>, N> extends OrderableNumerical<V>, Interpolatable<V, N>, Clampable<V> {

    /// @return the sum of all components of this vector.
    N sum(V vector);

    /// Returns the signum function for each component; zero if the component is zero,
    /// +1 if the component is greater than zero, -1 if the component is less than zero.
    /// @see Math#signum(float)
    /// @return a vector with the signum function applied to each component.
    V signum(V vector);

    /// @return a vector containing the absolute value of each component of `vector`.
    default V abs(V vector) {
        return multiply(vector, signum(vector));
    }

    /// @return the squared Euclidean distance between `a` and `b`.
    default N distanceSquared(V a, V b) {
        final V delta = subtract(a, b);
        final V squared = multiply(delta, delta);
        return sum(squared);
    }

    /// @return the magnitude squared of `vector`.
    default N lengthSquared(V vector) {
        return dot(vector, vector);
    }

    /// Returns the dot product between `v1` and `v2` vector.\
    /// The magnitude of the result is equal to `length(v1) * length(v2) * cos(theta)`, where theta is the angle between them.
    /// @return the dot product.
    default N dot(V v1, V v2) {
        return sum(multiply(v1, v2));
    }

    /// @return a vector with each component multiplied by the provided scalar.
    V multiply(V vector, N scalar);

    @Override
    default V clamp(V value, V min, V max) {
        return max(min, min(value, max));
    }

    /// @return a vector with each component clamped between `min` and `max`.
    V clamp(V vector, N min, N max);

    /// Returns the scalar [N] implementation of [OrderableNumerical].\
    /// Java will eventually provide a mechanism in the language to get the [OrderableNumerical] witness of [N].\
    /// By providing it now, like this, I can implement most of the APIs.
    /// @return the [OrderableNumerical] witness for [N].
    OrderableNumerical<N> scalarWitness(); // TODO Rename to scalarOps
}
