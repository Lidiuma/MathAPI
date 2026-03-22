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

package org.lidiuma.math.api.geometry.point;

import org.lidiuma.math.api.Interpolatable;
import org.lidiuma.math.api.tuple.UnaryTuple;
import org.lidiuma.math.api.vector.Vector;

/// Generic Point interface representing a position.
/// @param <N> the numeric type.
/// @param <P> the type of the specialized Point dimension.
/// @param <V> the associated Vector type used for arithmetic operations.
public interface Point<N, P extends Point<N, P, V>, V extends Vector<N, V>> extends Interpolatable<P, N>, UnaryTuple<N> {

    /// Translates the point by an offset vector.
    /// @return the translated point by `this + vector`.
    P add(V vector);

    /// Calculates the vector offset between `other` and `this` point.
    /// @return a vector representing the offset `this - other`.
    V subtract(P other);

    /// @return a point with each component clamped between `min` and `max`.
    P clamp(N min, N max);

    /// @return the Euclidean distance squared between `this` and `other`.
    N distanceSquared(P other);

    /* ========== Decimal-Only Operations ========== */

    /// @return the Euclidean distance between `this` and `other`.
    N distance(P other);
}
