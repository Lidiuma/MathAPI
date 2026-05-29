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

/// Generic Tuple interface with three components.
public interface UnaryTuple3<N> extends UnaryTuple<N> {

    N x();

    N y();

    N z();

    /// Returns all the components of this tuple without the z component.
    /// @return a lower dimension tuple without the z component.
    UnaryTuple2<N> withoutZ();

    /// @return 3.
    @Override
    default int dimension() {
        return 3;
    }

    /// Gets the component of the tuple at the specified index.
    /// - Index `0` returns `x()`
    /// - Index `1` returns `y()`
    /// - Index `2` returns `z()`
    /// - While any other number throws {@link IndexOutOfBoundsException}.
    /// @return the component at the provided index.
    @Override
    default N component(int index) throws IndexOutOfBoundsException {
        return switch (index) {
            case 0 -> x();
            case 1 -> y();
            case 2 -> z();
            default -> throw new IndexOutOfBoundsException("Could not get component at index " + index + " since the dimension is " + dimension() + ".");
        };
    }
}
