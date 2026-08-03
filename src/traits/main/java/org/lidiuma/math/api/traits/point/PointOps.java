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

package org.lidiuma.math.api.traits.point;

import org.lidiuma.math.api.point.Point;
import org.lidiuma.math.api.traits.vector.VectorOps;
import org.lidiuma.math.api.vector.Vector;
import org.lidiuma.math.api.traits.Interpolatable;

/// Point operations type-class.
/// @param <P> the [Point] type for which operations are defined.
/// @param <V> the [Vector] type required for defining [Point] operations.
/// @param <N> the numeric type.
public interface PointOps<
        P extends Point<N>,
        V extends Vector<N>,
        N> extends Interpolatable<P, N> {

    /// Translates the point by an offset vector.
    /// @return the translated point by `point + vector`.
    P add(P point, V vector);

    /// Calculates the vector offset between the `minuend` and the `subtrahend` point.
    /// @return a vector representing the offset `minuend - subtrahend`.
    V subtract(P minuend, P subtrahend);

    /// @return the Euclidean distance squared between `first` and `second`.
    N distanceSquared(P first, P second);

    /// @return a point with each component clamped between `min` and `max`.
    P clamp(P point, N min, N max);

    /// Returns the vector [V] implementation of [VectorOps].
    VectorOps<V, N> vectorOps();
}
