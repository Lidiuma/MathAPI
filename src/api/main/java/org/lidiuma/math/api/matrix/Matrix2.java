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

/// Square Matrix2x2 interface.
/// @param <N> the numeric type (e.g., [Integer], [Double]).
public interface Matrix2<N> extends SquareMatrix<N> {

    @Override
    default int rows() {
        return 2;
    }

    @Override
    default int columns() {
        return 2;
    }

    @Override
    default N at(int row, int column) {

        if (row < 0 || row >= rows()) throw new IndexOutOfBoundsException("Row " + row + " out of bounds, size is " + rows() + ".");
        if (column < 0 || column >= columns()) throw new IndexOutOfBoundsException("Column " + column + " out of bounds, size is " + columns() + ".");

        return switch (row * columns() + column) {
            case 0 -> m00(); case 1 -> m01();
            case 2 -> m10(); case 3 -> m11();
            default -> throw new AssertionError("Validation failed.");
        };
    }

    N m00();
    N m01();

    N m10();
    N m11();
}
