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

public interface FloatingVectorOps<N, V> extends VectorOps<N, V> {

    /// @return the angle between the `first` vector and the `second` vector.
    Angle<N> angle(V first, V second);

    /// @return the Euclidean distance between `this` and `other`.
    N distance(V start, V end);

    /// @return a vector with each component rounded up to the nearest integer.
    V ceil(V vector);

    /// @return a vector with each component rounded down to the nearest integer.
    V floor(V vector);

    /// @return the length of `this` vector.
    N length(V vector);

    /// @return a vector with the same direction as `this` vector but scaled to the provided `length`.
    V withLength(V vector, N length);

    /// @return a vector with the same direction as `this` vector but scaled to the provided `length` squared.
    V withLengthSquared(V vector, N lengthSquared);

    /// @return a vector with its length limited to `limit`.
    V withLimit(V vector, N limit);

    /// @return a vector with its length squared limited to `limit` squared.
    V withLimitSquared(V vector, N limitSquared);

    /// @return a normalized vector with length 1 in the same direction as `this`.
    /// @apiNote This vector should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalized(Vector)] can be used.
    V normalized(V vector);

    /// Similar to [#normalized()] but when the length of `this` vector is close to or is zero,
    /// the `orElse` vector is returned.
    /// @param orElse the value to use when the vector is close to zero.
    /// @return a normalized vector with length 1 in the same direction as `this`, or the `orElse` vector.
    V normalized(V vector, V orElse);
}
