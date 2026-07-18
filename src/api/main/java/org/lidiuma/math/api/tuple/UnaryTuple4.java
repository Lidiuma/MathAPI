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

/// Unary Tuple interface providing 4 components.
/// @param <N> the type being held by the tuple (e.g., [String], [Double]).
public interface UnaryTuple4<N> extends UnaryTuple<N> {

    N x();

    N y();

    N z();

    N w();

    /// @return 4.
    @Override
    default int size() {
        return 4;
    }

    /// Gets the component of the tuple at the specified index.
    /// - Index `0` returns `x()`
    /// - Index `1` returns `y()`
    /// - Index `2` returns `z()`
    /// - Index `3` returns `w()`
    /// - While any other number throws {@link IndexOutOfBoundsException}.
    /// @return the component at the provided index.
    @Override
    default N at(int index) throws IndexOutOfBoundsException {
        return switch (index) {
            case 0 -> x();
            case 1 -> y();
            case 2 -> z();
            case 3 -> w();
            default -> throw new IndexOutOfBoundsException("Index " + index + " out of bounds, size is " + size() + ".");
        };
    }
}
