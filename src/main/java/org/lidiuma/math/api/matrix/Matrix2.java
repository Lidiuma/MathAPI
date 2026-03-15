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

import org.lidiuma.math.api.geometry.point.Point1;
import org.lidiuma.math.api.geometry.point.Point2;
import org.lidiuma.math.api.tuple.UnaryTuple2;
import org.lidiuma.math.api.vector.Vector1;
import org.lidiuma.math.api.vector.Vector2;

/// Generic Matrix2 interface.
public interface Matrix2<N> extends SquareMatrix<N, Matrix2<N>, UnaryTuple2<N>> {

    int SIZE = 4;

    @Override
    default int size() {
        return SIZE;
    }

    @Override
    default int rows() {
        return 2;
    }

    @Override
    default int columns() {
        return 2;
    }

    N m00();
    N m01();

    N m10();
    N m11();

    /// Multiplies `this` matrix by the provided [Point1] treated as a [Point2] with [Point2#y()]` = 1`.
    Point1<N> mul(Point1<N> point);

    /// Multiplies `this` matrix by the provided [Vector1] treated as a [Vector2] with [Vector2#y()]` = 0`.
    Vector1<N> mul(Vector1<N> vector);
}
