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
import org.lidiuma.math.api.geometry.point.Point4;
import org.lidiuma.math.api.tuple.UnaryTuple4;
import org.lidiuma.math.api.vector.Vector3;
import org.lidiuma.math.api.vector.Vector4;

/// Immutable Matrix4x4 always using post-multiplication.
/// Internal indexing is row-major, while external raw output is column-major.
@SuppressWarnings("unused")
public interface Matrix4<N> extends Matrix<N, Matrix4<N>, UnaryTuple4<N>> {

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

    /// Multiplies `this` matrix with the provided [Point3] treated as a [Point4] with [Point4#w()] = 1.
    Point3<N> mul(Point3<N> point);

    /// Multiplies `this` matrix with the provided [Vector3] treated as a [Vector4] with [Vector4#w()] = 0.
    Vector3<N> mul(Vector3<N> vector);

    /// Multiplies a 3D point using this matrix and performs a perspective divide using the `w` component.
    /// @return the projected point.
    Point3<N> project(Point3<N> point); // TODO Remove and let a Camera class manage this?
}
