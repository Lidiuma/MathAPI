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

import org.lidiuma.math.api.geometry.point.Point2;
import org.lidiuma.math.api.geometry.point.Point3;
import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.vector.Vector2;
import org.lidiuma.math.api.vector.Vector3;

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

    /// Multiplies `this` matrix with the provided [Point2] treated as a [Point3] with [Point3#z()] = 1.
    Point2<N> mul(Point2<N> point);

    /// Multiplies `this` matrix with the provided [Vector2] treated as a [Vector3] with [Vector3#z()] = 0.
    Vector2<N> mul(Vector2<N> vector);

    /// @return a new affine matrix with the translation applied.
    Affine2<N> translate(Vector2<N> translation);

    /// @return a new affine matrix with the given rotation applied.
    Affine2<N> rotate(Angle<N> angle);

    /// @return a new affine matrix with the given shearing applied.
    Affine2<N> shear(Vector2<N> shear);

    /// @return a new affine matrix with the given scaling applied.
    Affine2<N> scale(Vector2<N> scale);

    /// @return the translation component from this affine matrix.
    Vector2<N> translation();

    /// @return the rotation component from this affine matrix as an angle.
    Angle<N> rotation();

    /// @return the scaling component from this affine matrix.
    Vector2<N> scale();
}
