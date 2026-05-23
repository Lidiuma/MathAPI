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

import org.lidiuma.math.api.Clampable;
import org.lidiuma.math.api.Interpolatable;
import org.lidiuma.math.api.Numerical;
import org.lidiuma.math.api.Orderable;

public interface VectorOps<V extends Vector<N>, N> extends Numerical<V>, Orderable<V>, Interpolatable<V, N>, Clampable<V> {

    /// @return a vector containing the absolute value of each component of `vector`.
    V abs(V vector);

    /// Returns the signum function for each component; zero if the component is zero,
    /// +1 if the component is greater than zero, -1 if the component is less than zero.
    /// @see Math#signum(float)
    /// @return a vector with the signum function applied to each component.
    V signum(V vector);

    /// @return the Euclidean distance squared between `a` and `b`.
    N distanceSquared(V a, V b);

    /// @return the magnitude squared of `vector`.
    N lengthSquared(V vector);

    /// Returns the dot product between `left` and `right` vector.\
    /// The magnitude of the result is equal to `length(left) * length(right) * cos(theta)`, where theta is the angle between them.
    /// @return the dot product.
    N dot(V left, V right);

    /// @return a vector with each component multiplied by the provided scalar.
    V multiply(V vector, N scalar);

    /// @return a vector with each component clamped between `min` and `max`.
    V clamp(V point, N min, N max);

    /// @return the component-wise addition of `left` and `right`.
    @Override
    V add(V left, V right);

    /// @return the component-wise subtraction of `left` and `right`.
    @Override
    V subtract(V left, V right);

    /// @return the Hadamard (component-wise) multiplication of `left` and `right`.
    @Override
    V multiply(V left, V right);

    /// @return the component-wise division of `left` and `right`.
    @Override
    V divide(V left, V right);

    @Override
    V remainder(V left, V right);

    /// @return a vector with all its components negated.
    /// Equivalent to multiplying `vector` by the scalar `-1`.
    @Override
    V negated(V vector);

    /// @return true if all the components of `first` are less than the corresponding components of `second`.
    @Override
    boolean lessThan(V left, V right);

    /// @return true if all the components of `first` are less than or equal to the corresponding components of `second`.
    @Override
    boolean lessThanEqual(V left, V right);

    /// @return true if all the components of `first` are greater than the corresponding components of `second`.
    @Override
    boolean greaterThan(V left, V right);

    /// @return true if all the components of `first` are greater than or equal to the corresponding components of `second`.
    @Override
    boolean greaterThanEqual(V left, V right);

    /// @return a vector containing the component-wise minimum between `first` and `second`.
    /// ```java
    /// var x = min(left.x(), right.x());
    /// var y = min(left.y(), right.y());
    /// ...
    /// return vector(x, y, ...);
    /// ```
    @Override
    V min(V left, V right);

    /// @return a vector containing the component-wise maximum between `first` and `second`.
    /// ```java
    /// var x = max(left.x(), right.x());
    /// var y = max(left.y(), right.y());
    /// ...
    /// return vector(x, y, ...);
    /// ```
    @Override
    V max(V left, V right);

    /// Returns the scalar [N] implementation of [Numerical].\
    /// Java will eventually provide a mechanism in the language to get the Numerical witness of [N].\
    /// By providing it now, like this, I can implement most of the APIs.
    /// @return the [Numerical] witness for [N].
    Numerical<N> scalarWitness();
}
