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

/// Matrix interface.
/// @param <N> is the numerical type used for the matrix. (e.g., [Integer], [Double])
public interface Matrix<N> {

    /// @return the total number of components, equal to [#rows()]` * `[#columns()].
    default int size() {
        return rows() * columns();
    }

    /// @return the total number of rows for this matrix.
    int rows();

    /// @return the total number of columns for this matrix.
    int columns();

    /// Gets the component of the matrix at the specified row and column.\
    /// @throws IndexOutOfBoundsException when `row` is outside `[0, `[#rows()]`]` or when `column` is outside `[0, `[#columns()]`]`.
    /// @return the component at the provided row and column.
    N at(int row, int column);
}
