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

public interface Matrix2x3<N> extends Matrix2<N> {

    int SIZE = 6;

    @Override
    default int size() {
        return SIZE;
    }

    @Override
    default int columns() {
        return 3;
    }

    N m02();
    N m12();

    /// Transforms a 2D position vector using the affine part of this matrix.
    /// @return the transformed vector.
    Vector2<N> transform(Vector2<N> vector); // TODO Rename to mul()

    /// @return a new affine matrix with the translation applied.
    Matrix2x3<N> translate(Vector2<N> translation);

    /// @return a new affine matrix with the given rotation applied.
    Matrix2x3<N> rotate(Angle<N> angle);

    /// @return a new affine matrix with the given shearing applied.
    Matrix2x3<N> shear(Vector2<N> shear);

    /// @return a new affine matrix with the given scaling applied.
    Matrix2x3<N> scale(Vector2<N> scale);

    /// @return the translation component from this affine matrix.
    Vector2<N> translation();

    /// @return the rotation component from this affine matrix as an angle.
    Angle<N> rotation();

    /// @return the scaling component from this affine matrix.
    Vector2<N> scale();
}
