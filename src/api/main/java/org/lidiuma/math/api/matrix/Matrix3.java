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

/// Square Matrix3x3 interface.
/// @param <N> the numeric type (e.g., [Integer], [Double]).
public interface Matrix3<N> extends SquareMatrix<N> {

    @Override
    default int rows() {
        return 3;
    }

    @Override
    default int columns() {
        return 3;
    }

    @Override
    default N at(int row, int column) {

        if (row < 0 || row >= rows()) throw new IndexOutOfBoundsException("Row " + row + " out of bounds, size is " + rows() + ".");
        if (column < 0 || column >= columns()) throw new IndexOutOfBoundsException("Column " + column + " out of bounds, size is " + columns() + ".");

        return switch (row * columns() + column) {
            case 0 -> m00(); case 1 -> m01(); case 2 -> m02();
            case 3 -> m10(); case 4 -> m11(); case 5 -> m12();
            case 6 -> m20(); case 7 -> m21(); case 8 -> m22();
            default -> throw new AssertionError("Validation failed.");
        };
    }

    /// Row 0, Column 0 accessor.
    N m00();
    /// Row 0, Column 1 accessor.
    N m01();
    /// Row 0, Column 2 accessor.
    N m02();

    /// Row 1, Column 0 accessor.
    N m10();
    /// Row 1, Column 1 accessor.
    N m11();
    /// Row 1, Column 2 accessor.
    N m12();

    /// Row 2, Column 0 accessor.
    N m20();
    /// Row 2, Column 1 accessor.
    N m21();
    /// Row 2, Column 2 accessor.
    N m22();
}
