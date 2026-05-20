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

import org.lidiuma.math.api.vector.Vector;

/// Matrix specialization where rows and columns are equal.
public interface SquareMatrixOps<
        M extends SquareMatrix<N>,
        V extends Vector<N>,
        N> extends MatrixOps<M, V, N> {

    /// @return the identity matrix.
    M identity();

    /// @return The determinant of the squared `matrix`.
    N determinant(M matrix);

    /// Inverts `matrix`, given that the determinant is != 0.
    /// @return The inverted matrix.
    /// @throws ArithmeticException if the matrix cannot be inverted because it is singular.
    M inverse(M matrix) throws ArithmeticException;

    /// Computes the normal matrix of the provided `matrix`.
    /// The normal matrix is defined as the inverse transpose of the input matrix,
    /// and is used to correctly transform normal vectors under non-uniform scaling and shear.
    ///
    /// @return the inverse-transpose of the input matrix, (M⁻¹)ᵀ.
    M normalMatrix(M matrix);

    /// @return true if the matrix is a singular squared matrix.
    boolean isSingular(M matrix);
}
