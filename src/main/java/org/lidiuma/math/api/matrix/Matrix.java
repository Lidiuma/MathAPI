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
import org.lidiuma.math.api.tuple.UnaryTuple;
import java.util.function.Function;

/// Generic Matrix interface.
/// @apiNote All operations use post-multiplication.
/// @param <M> is the matrix implementation.
/// @param <N> is the numerical type used for the matrix. (e.g., {@link Float}, {@link Double})
/// @param <T> The UnaryTuple representing Vector-like classes to allow Vector multiplication with this Matrix.
public interface Matrix<N, M extends Matrix<N, M, T>, T extends UnaryTuple<N>> extends Interpolatable<M, N> {

    /// @return the total number of components, equal to [#rows()]` * `[#columns()].
    int size();

    int rows();

    int columns();

    /// @return the component-wise addition of `this` and `other`.
    M add(M other);

    /// @return the component-wise subtraction of `this` and `other`.
    M sub(M other);

    /// Multiplies `this` matrix by `other`.
    /// @return a new matrix equal to `this * other`.
    /// @apiNote Matrix multiplication is **not** commutative; `this * other != other * this`.
    M mul(M other);

    /// @return a new matrix with each component multiplied by the provided scalar.
    M mul(N scalar);

    /// Divides `this` matrix by `other`.
    /// This is equivalent to `this * other⁻¹`.
    /// @return a new matrix equal to `this / other`.
    /// @apiNote Matrix division is **not** commutative; `this / other != other / this`.
    M div(M other);

    /// Multiplies `this` matrix by the provided tuple.
    T mul(T tuple);

    /// Multiplies `this` matrix by the provided tuple, and maps the result to the wanted type.
    <O extends T> O mul(T tuple, Function<T, O> mapper);

    /// @return the transposed version of this matrix.
    M transposed();
}
