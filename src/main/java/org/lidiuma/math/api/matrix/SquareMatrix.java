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

import org.lidiuma.math.api.tuple.UnaryTuple;

/// Matrix specialization where the rows are equal to the columns.
public interface SquareMatrix<N, M extends SquareMatrix<N, M, T>, T extends UnaryTuple<N>> extends Matrix<N, M, T> {

    /// @return The determinant of this squared matrix.
    N determinant();

    /// Inverts this matrix given that the determinant is != 0.
    /// @return This matrix for the purpose of chaining operations.
    /// @throws ArithmeticException if the matrix cannot be inverted because it is singular.
    M inverted() throws ArithmeticException;

    /// @return a matrix with the translational part removed (set to 0) and transposed.
    M toNormalMatrix();

    /// @return true if the matrix is a singular squared matrix.
    boolean isSingular();
}
