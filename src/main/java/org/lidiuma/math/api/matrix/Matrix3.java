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
import org.lidiuma.math.api.tuple.UnaryTuple3;
import org.lidiuma.math.api.vector.Vector3;

/// Immutable Matrix3x3 always using post-multiplication.
/// Internal indexing is row-major, while external raw output is column-major.
public interface Matrix3<N> extends Matrix<N, Matrix3<N>, UnaryTuple3<N>> {

    int SIZE = 9;
    int M00 = 0, M01 = 3, M02 = 6;
    int M10 = 1, M11 = 4, M12 = 7;
    int M20 = 2, M21 = 5, M22 = 8;

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
        return 3;
    }

    N m00();
    N m01();
    N m02();

    N m10();
    N m11();
    N m12();

    N m20();
    N m21();
    N m22();

    /// Applies a 3D rotation to this matrix using a quaternion.
    Matrix3<N> rotate(Quaternion<N> quaternion);

    /// Transforms a 3D vector using this matrix.
    /// @return the transformed vector.
    Vector3<N> transform(Vector3<N> vector);

    // TODO Add untransform()

    /// Rotates a 3D vector.
    default Vector3<N> rotate(Vector3<N> vector) {
        return transform(vector);
    }

    /// Applies the inverse rotation of this matrix to a 3D vector, undoing {@link #rotate(Vector3)}.
    Vector3<N> unrotate(Vector3<N> vector);
}
