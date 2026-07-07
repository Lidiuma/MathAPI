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

import org.lidiuma.math.api.Numerical;
import org.lidiuma.math.api.vector.Vector;
import org.lidiuma.math.api.vector.VectorOps;

public interface MatrixOps<
        M extends Matrix<N>,
        V extends Vector<N>,
        N> extends Numerical<M> {

    /// @return the transposed version of `matrix`.
    M transpose(M matrix);

    /// @return a new matrix with each component multiplied by the provided scalar.
    M multiply(M matrix, N scalar);

    /// Multiplies `matrix` by the provided `vector`.
    V multiply(M matrix, V vector);

    /// @return the component-wise addition of `op1` and `op2`.
    @Override
    M add(M op1, M op2);

    /// @return the component-wise subtraction of `op1` and `op2`.
    @Override
    M subtract(M op1, M op2);

    /// Multiplies the `op1` matrix by `op2`.
    /// @return a new matrix equal to `op1 * op2`.
    /// @apiNote Matrix multiplication is **not** commutative; `op1 * op2 != op2 * op1`.
    @Override
    M multiply(M op1, M op2);

    /// @throws ArithmeticException cannot divide a matrix by another without the inverse.
    @Override
    default M divide(M op1, M op2) throws ArithmeticException {
        throw new ArithmeticException("Division by non-square matrices is not possible.");
    }

    /// Returns the scalar [N] implementation of [Numerical].\
    /// Java will eventually provide a mechanism in the language to get the [Numerical] witness of [N].\
    /// By providing it now, like this, I can implement most of the APIs.
    /// @return the [Numerical] witness for [N].
    Numerical<N> scalarOps();

    /// Returns the vector [V] implementation of [VectorOps].
    VectorOps<V, N> vectorOps();
}
