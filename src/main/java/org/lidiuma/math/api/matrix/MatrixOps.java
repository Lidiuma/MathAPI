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

import org.lidiuma.math.api.Interpolatable;
import org.lidiuma.math.api.Numerical;
import org.lidiuma.math.api.vector.Vector;

public interface MatrixOps<
        M extends Matrix<N>,
        V extends Vector<N>,
        N> extends Numerical<M>, Interpolatable<M, N> {

    /// @return the transposed version of `matrix`.
    M transposed(M matrix);

    /// @return a new matrix with each component multiplied by the provided scalar.
    M multiply(M matrix, N scalar);

    /// Multiplies `matrix` by the provided `vector`.
    V multiply(M matrix, V vector);

    /// @return the component-wise addition of `left` and `right`.
    @Override
    M add(M left, M right);

    /// @return the component-wise subtraction of `left` and `right`.
    @Override
    M subtract(M left, M right);

    /// Multiplies the `left` matrix by `right`.
    /// @return a new matrix equal to `left * right`.
    /// @apiNote Matrix multiplication is **not** commutative; `left * right != right * left`.
    @Override
    M multiply(M left, M right);

    /// Divides the `left` matrix by `right`.
    /// This is equivalent to `left * right⁻¹`.
    /// @return a new matrix equal to `left / right`.
    /// @apiNote Matrix division is **not** commutative; `left / right != right / left`.
    @Override
    M divide(M left, M right);
}
