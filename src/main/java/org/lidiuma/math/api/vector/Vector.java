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

import org.lidiuma.math.api.Interpolatable;
import org.lidiuma.math.api.tuple.UnaryTuple;
import java.util.function.Supplier;

/// Generic Vector interface.
public interface Vector<N, V extends Vector<N, V>> extends Interpolatable<V, N>, UnaryTuple<N> {

    /// @return the sum of the vector's components.
    N sum();

    /// @return the component-wise addition of `this` and `other`.
    V add(V other);

    /// @return the component-wise subtraction of `this` and `other`.
    V sub(V other);

    /// @return the component-wise multiplication of `this` and `other`.
    V mul(V other);

    /// @return a vector with each component multiplied by the provided scalar.
    V mul(N scalar);

    /// @return the component-wise division of `this` and `other`.
    V div(V other);

    /// @return a vector with all its components negated.
    /// Equivalent to multiplying this vector by the scalar `-1`.
    V negated();

    /// @return true if all the components of `this` are less than the corresponding components of `other`.
    boolean lt(V other);

    /// @return true if all the components of `this` are less than or equal to the corresponding components of `other`.
    boolean ltEq(V other);

    /// @return true if all the components of `this` are greater than the corresponding components of `other`.
    boolean gt(V other);

    /// @return true if all the components of `this` are greater than or equal to the corresponding components of `other`.
    boolean gtEq(V other);

    /// @return a vector containing the absolute value of each component of `this` vector.
    V abs();

    /// @return a vector containing the component-wise maximum between `this` and `other`.
    /// ```java
    /// var x = max(this.x(), other.x());
    /// var y = max(this.y(), other.y());
    /// ...
    /// return vector(x, y, ...);
    /// ```
    V max(V other);

    /// @return a vector containing the component-wise minimum between `this` and `other`.
    /// ```java
    /// var x = min(this.x(), other.x());
    /// var y = min(this.y(), other.y());
    /// ...
    /// return vector(x, y, ...);
    /// ```
    V min(V other);

    /// @return a vector with each component clamped between `min` and `max`.
    V clamp(N min, N max);

    /// @return a vector with a component-wise clamp between `min` and `max`.
    V clamp(V min, V max);

    /// @return the Euclidean distance squared between `this` and `other`.
    N distance2(V other);

    /// @return the magnitude squared of `this` vector.
    N length2();

    /// Returns the dot product of `this` vector and the `other` vector.\
    /// The magnitude of the result is equal to `length() * other.length() * cos(theta)`, where theta is the angle between them.
    /// @return the dot product.
    N dot(V other);

    /* ========== Decimal-Only Operations ========== */

    /// @return the Euclidean distance between `this` and `other`.
    N distance(V other);

    /// @return a vector with each component rounded up to the nearest integer.
    V ceil();

    /// @return a vector with each component rounded down to the nearest integer.
    V floor();

    /// @return the length of `this` vector.
    N length();

    /// @return a vector with the same direction as `this` vector but scaled to the provided `length`.
    V withLength(N length);

    /// @return a vector with the same direction as `this` vector but scaled to the provided `length` squared.
    V withLength2(N length2);

    /// @return a vector with its length limited to `limit`.
    V withLimit(N limit);

    /// @return a vector with its length squared limited to `limit` squared.
    V withLimit2(N limit2);

    /// @return a normalized vector with length 1 in the same direction as `this`.
    /// @apiNote This vector should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalized(java.lang.Object, java.util.function.Supplier)] can be used.
    V normalized();

    /// Similar to [#normalized()] but when the length of `this` vector is close to or zero,
    /// the value provided by the supplier is returned.
    /// @param epsilon threshold below which the vector is considered zero-length.
    /// @param supplier the factory for the fallback vector.
    /// @return a normalized vector with length 1 in the same direction as `this`,
    /// or the fallback vector if below epsilon.
    <T extends V> T normalized(N epsilon, Supplier<T> supplier);
}
