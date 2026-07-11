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

/// Matrix interface representing matrices having the numbers of [#rows()] and [#columns()] be the same.
public interface SquareMatrix<N> extends Matrix<N> {

    /// @return the dimension of the Squared Matrix.
    default int dimension() {
        return rows(); // rows == column, so it doesn't matter which.
    }
}
