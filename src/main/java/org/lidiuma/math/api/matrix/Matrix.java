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
import java.util.function.Function;

/// Generic Matrix interface.
/// @apiNote All operations use post-multiplication.
/// @param <M> is the matrix implementation.
/// @param <N> is the numerical type used for the matrix. (e.g., {@link Float}, {@link Double})
/// @param <T> The UnaryTuple representing Vector-like classes to allow Vector multiplication with this Matrix.
public interface Matrix<N, M extends Matrix<N, M, T>, T extends UnaryTuple<N>> {

    /// @return [Matrix#rows()] multiplied by [Matrix#columns()].
    int size();

    int rows();

    int columns();

    /// @return this matrix with each element added by the other matrix.
    M add(M other);

    /// @return this matrix with each element subtracted by the other matrix.
    M sub(M other);

    /// Multiples `this` matrix with the `other` matrix.\
    /// Results in `A := AB`.
    /// @return the multiplied matrix.
    /// @apiNote Order is important! `this * other != other * this`
    M mul(M other);

    /// Scalar Matrix Multiplication.
    /// @return this matrix with each element multiplied by the scalar.
    M mul(N scalar);

    /// Multiples `this` matrix with the provided tuple.
    T mul(T tuple);

    /// Multiples `this` matrix with the provided tuple, and maps the result to the wanted type.
    <O extends T> O mul(T tuple, Function<T, O> mapper);

    /// @return the transposed version of this matrix.
    M transpose();
}
