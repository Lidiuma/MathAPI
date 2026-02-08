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

package org.lidiuma.math.api.tuple;

public interface UnaryTuple<N> {

    /// @return The amount of elements this tuple has.
    int dimension();

    /// Gets the component of the tuple at the specified index.\
    /// Taking `Tuple2` as an example:
    /// - Index `0` returns `x()`
    /// - Index `1` returns `y()`
    /// - While index `2` throws {@link IndexOutOfBoundsException}.
    /// @return the component at the provided index.
    N component(int index) throws IndexOutOfBoundsException;
}
