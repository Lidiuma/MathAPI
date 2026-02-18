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
import org.lidiuma.math.api.rotation.Quaternion;
import org.lidiuma.math.api.vector.Vector3;
import org.lidiuma.math.api.vector.Vector4;

/// Immutable Matrix4x4 always using post-multiplication.
/// Internal indexing is row-major, while external raw output is column-major.
@SuppressWarnings("unused")
public interface Matrix4<N> extends Matrix<N, Matrix4<N>> {

    int SIZE = 16;
    int M00 = 0, M01 = 4, M02 =  8, M03 = 12;
    int M10 = 1, M11 = 5, M12 =  9, M13 = 13;
    int M20 = 2, M21 = 6, M22 = 10, M23 = 14;
    int M30 = 3, M31 = 7, M32 = 11, M33 = 15;

    @Override
    default int size() {
        return SIZE;
    }

    @Override
    default int rows() {
        return 4;
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

    N m30();
    N m31();
    N m32();
    N m33();

    /// Linearly interpolates between this matrix and the other matrix mixing by alpha.
    /// @param alpha the alpha value in the range `[0,1]`.
    Matrix4<N> lerp(Matrix4<N> other, N alpha);

    /// Averages this matrix with another, using lerp for translation/scale and slerp for rotation.
    /// @param other The other matrix.
    /// @param weight Weight for this transform (other's weight is `1 - weight`)
    Matrix4<N> average(Matrix4<N> other, N weight);

    /// Averages an array of matrices using the same weight.
    /// @return a new matrix representing the average transform of the input matrices.
    Matrix4<N> average(Matrix4<N>[] matrices);

    /// Averages an array of matrices using the provided weights.
    /// @return a new matrix representing the average transform of the input matrices.
    Matrix4<N> average(Matrix4<N>[] matrices, N[] weights);

    Vector4<N> transform(Vector4<N> vector);

    /// Transforms a 3D position vector ignoring prospective.
    /// @return the transformed vector.
    Vector3<N> transform(Vector3<N> vector);

    /// Applies the inverse affine transformation of this matrix to a 3D vector.
    Vector3<N> untransform(Vector3<N> vector);

    /// Projects a 3D position vector using this matrix and performs a perspective divide.
    /// @apiNote Includes rotation, translation, scale, and perspective; output is divided by W.
    Vector3<N> project(Vector3<N> vector);

    /// @return a new matrix with the rotation around the given axis applied.
    Matrix4<N> rotateAround(Vector3<N> axis, Angle<N> angle);

    /// @return a new matrix that rotates the direction of `v1` to align with `v2`.
    Matrix4<N> rotateBetween(Vector3<N> v1, Vector3<N> v2);

    /// @return a new matrix that rotates to align the forward direction with `direction` and up vector with `up`.
    Matrix4<N> rotateToDirection(Vector3<N> direction, Vector3<N> up);

    /// @return a new matrix with the given scale applied.
    Matrix4<N> scale(Vector3<N> scale);

    /// @return a new matrix with the translation applied.
    Matrix4<N> translate(Vector3<N> translation);

    /// @return a new matrix with the given rotation applied.
    Matrix4<N> rotate(Quaternion<N> rotation);

    /// @return the scale components along each axis.
    Vector3<N> scale();

    /// @return the translation part of this matrix.
    Vector3<N> translation();

    /// @return the rotation part of this matrix.
    Quaternion<N> rotation();
}
