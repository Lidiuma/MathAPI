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

import org.lidiuma.math.api.Numerical;
import org.lidiuma.math.api.tuple.UnaryTuple;

public interface VectorArithmetic<N, V extends UnaryTuple<N>> extends Numerical<V> {

    /// @return a vector with all it's components set to 0.
    V zero();

    /// @return a vector with all it's components set to 1.
    V one();

    /// @return the component-wise addition of `this` and `other`.
    @Override
    V add(V addend, V augend);

    /// @return the component-wise subtraction of `this` and `other`.
    @Override
    V subtract(V minuend, V subtrahend);

    /// @return the Hadamard (component-wise) multiplication of `this` and `other`.
    @Override
    V multiply(V multiplier, V multiplicand);

    /// @return a vector with each component multiplied by the provided scalar.
    V multiply(V multiplier, N scalar);

    /// @return the component-wise division of `this` and `other`.
    @Override
    V divide(V dividend, V divisor);

    @Override
    V remainder(V dividend, V divisor);

    /// @return a vector with all its components negated.
    /// Equivalent to multiplying this vector by the scalar `-1`.
    @Override
    V negated(V operand);

    // TODO Move to an orderable class the same way the Java Valhalla fork does it.
    /// @return true if all the components of `this` are less than the corresponding components of `other`.
    boolean lessThan(V first, V second);

    /// @return true if all the components of `this` are less than or equal to the corresponding components of `other`.
    boolean lessThanEqual(V first, V second);

    /// @return true if all the components of `this` are greater than the corresponding components of `other`.
    boolean greaterThan(V first, V second);

    /// @return true if all the components of `this` are greater than or equal to the corresponding components of `other`.
    boolean greaterThanEqual(V first, V second);

    /// @return a vector containing the absolute value of each component of `this` vector.
    V abs(V vector);

    /// @return a vector containing the component-wise maximum between `this` and `other`.
    /// ```java
    /// var x = max(this.x(), other.x());
    /// var y = max(this.y(), other.y());
    /// ...
    /// return vector(x, y, ...);
    /// ```
    V max(V first, V second);

    /// @return a vector containing the component-wise minimum between `this` and `other`.
    /// ```java
    /// var x = min(this.x(), other.x());
    /// var y = min(this.y(), other.y());
    /// ...
    /// return vector(x, y, ...);
    /// ```
    V min(V first, V second);

    /// @return a vector with each component clamped between `min` and `max`.
    V clamp(V vector, N min, N max);

    /// Returns the signum function for each component; zero if the component is zero,
    /// +1 if the component is greater than zero, -1 if the component is less than zero.
    /// @see Math#signum(float)
    /// @return a vector with the signum function applied to each component.
    V signum(V vector);

    /// @return the Euclidean distance squared between `this` and `other`.
    N distanceSquared(V first, V second);

    /// @return the magnitude squared of `this` vector.
    N lengthSquared(V vector);

    /// Returns the dot product of `this` vector and the `other` vector.\
    /// The magnitude of the result is equal to `length() * other.length() * cos(theta)`, where theta is the angle between them.
    /// @return the dot product.
    N dot(V first, V second);
}
