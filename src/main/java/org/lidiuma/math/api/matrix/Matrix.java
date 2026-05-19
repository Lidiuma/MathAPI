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
public interface Matrix<M extends Matrix<M, T, N>, T extends UnaryTuple<N>, N> {

    /// @return the total number of components, equal to [#rows()]` * `[#columns()].
    int size();

    int rows();

    int columns();

    /// Multiplies `this` matrix by the provided tuple.
    T multiply(T tuple);

    /// Multiplies `this` matrix by the provided tuple, and maps the result to the wanted type.
    <O extends T> O multiply(T tuple, Function<T, O> mapper);
}
