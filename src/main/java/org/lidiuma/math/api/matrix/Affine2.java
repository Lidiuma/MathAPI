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

package org.lidiuma.math.api.matrix;

import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.vector.Vector2;

/// Specialized [Matrix3] interface for 2D operations.
public interface Affine2<N> extends Matrix3<N> {

    /// @return Always returns 0.
    @Override
    N m20();

    /// @return Always returns 0.
    @Override
    N m21();

    /// @return Always returns 1.
    @Override
    N m22();

    /// Averages this matrix with another, using lerp for translation/scale and slerp for rotation.
    /// @param other The other matrix.
    /// @param weight Weight for this transform (other's weight is `1 - weight`)
    Affine2<N> average(Affine2<N> other, N weight);

    /// @return the translation component of this matrix.
    Vector2<N> translation();

    /// @return the angle calculated from the rotation component of this matrix.
    Angle<N> rotation();

    /// @return the shearing component of this matrix.
    Vector2<N> shear();

    /// @return the scaling component of this matrix.
    Vector2<N> scale();
}
