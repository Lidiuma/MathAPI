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

import org.lidiuma.math.api.rotation.Quaternion;
import org.lidiuma.math.api.vector.Vector3;

/// Specialized [Matrix4] interface for 3D operations.
public interface Affine3<N> extends Matrix4<N> {

    /// @return Always returns 0.
    @Override
    N m30();

    /// @return Always returns 0.
    @Override
    N m31();

    /// @return Always returns 0.
    @Override
    N m32();

    /// @return Always returns 1.
    @Override
    N m33();

    /// Averages this matrix with another, using lerp for translation/scale and slerp for rotation.
    /// @param other The other matrix.
    /// @param weight Weight for this transform (other's weight is `1 - weight`)
    Affine3<N> average(Affine3<N> other, N weight);

    /// @return the translation part of this matrix.
    Vector3<N> translation();

    /// @return the rotation part of this matrix.
    Quaternion<N> rotation();

    /// @return the shearing component of this matrix.
    Vector3<N> shear();

    /// @return the scale components along each axis.
    Vector3<N> scale();
}
