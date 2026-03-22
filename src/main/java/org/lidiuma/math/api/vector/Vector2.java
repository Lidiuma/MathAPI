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
import org.lidiuma.math.api.tuple.UnaryTuple2;

/// Generic Vector 2D interface.
public interface Vector2<N> extends Vector<N, Vector2<N>>, UnaryTuple2<N> {

    /// @return a vector with a component-wise clamp between `min` and `max`.
    Vector2<N> clamp(UnaryTuple2<N> min, UnaryTuple2<N> max);

    /// Returns the 2D cross product of `this` vector and the `other` vector.\
    /// The result is equivalent to the Z component of the 3D cross product.
    /// @return the scalar result of the cross product.
    N cross(Vector2<N> other);

    /* ========== Decimal-Only Operations ========== */

    /// @return this vector rotated by the given angle.
    Vector2<N> rotate(Angle<N> angle);

    /// @return the angle between this vector and the positive X axis, the angle is measured counterclockwise.
    Angle<N> angle();
}
