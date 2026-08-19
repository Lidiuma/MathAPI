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
import org.lidiuma.math.api.traits.Interpolatable;
import org.lidiuma.math.api.traits.vector.FloatingVectorOps;
import org.lidiuma.math.api.vector.Vector;

/// Operations for [Point] specialized in floating numeric.
/// @param <P> the [Point] type for which operations are defined.
/// @param <V> the [Vector] type required for defining [Point] operations.
/// @param <N> the numeric type.
public interface FloatingPointOps<
        P extends Point<N>,
        V extends Vector<N>,
        N> extends PointOps<P, V, N>, Interpolatable<P, N> {

    /// @return the Euclidean distance between `first` and `second`.
    N distance(P first, P second);

    /// Returns the vector [V] implementation of [FloatingVectorOps].
    @Override
    FloatingVectorOps<V, N> vectorOps(); // This interface doesn't care about the angle.
}
