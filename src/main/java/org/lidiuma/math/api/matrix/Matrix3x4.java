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

import org.lidiuma.math.api.geometry.point.Point3;
import org.lidiuma.math.api.vector.Vector3;

public interface Matrix3x4<N> extends Matrix<N, Matrix3x4<N>, Vector3<N>> {

    int SIZE = 12;

    @Override
    default int size() {
        return SIZE;
    }

    @Override
    default int rows() {
        return 3;
    }

    @Override
    default int columns() {
        return 4;
    }

    N m00();
    N m01();
    N m02();
    N m03();

    N m10();
    N m11();
    N m12();
    N m13();

    N m20();
    N m21();
    N m22();
    N m23();

    /// Transforms a 3D vector using this matrix, ignoring perspective and translation (w = 0).
    /// Only rotation, scale, and shear affect the result.
    /// @return the transformed direction.
    Vector3<N> transform(Vector3<N> vector);

    /// Transforms a 3D point using this matrix, ignoring perspective (w = 1).
    /// Translation, rotation, and scale apply.
    /// @return the transformed point.
    Point3<N> transform(Point3<N> point);

    /// Applies the inverse affine transformation of this matrix to a 3D direction vector.
    /// Inverse of {@link #transform(Vector3)}.
    Vector3<N> untransform(Vector3<N> vector);

    /// Applies the inverse affine transformation of this matrix to a 3D point.
    /// Inverse of {@link #transform(Point3)}.
    Point3<N> untransform(Point3<N> vector);
}
