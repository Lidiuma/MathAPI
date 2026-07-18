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

package org.lidiuma.math.api.color;

import org.lidiuma.math.api.tuple.UnaryTuple4;

/// Specialization of [UnaryTuple4] representing a Color.\
/// The internal representation does not have to be `N`, as long as it can be converted to `N`.
/// @param <N> The numeric type representing the color (e.g., [Float], [Double]), usually in the range `[0, 1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
public interface Color<N> extends UnaryTuple4<N> {

    /// @return The red channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N red();

    /// @return The green channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N green();

    /// @return The blue channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N blue();

    /// @return The alpha channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N alpha();

    /// The same as [#red()].
    @Override
    default N x() {
        return red();
    }

    /// The same as [#green()].
    @Override
    default N y() {
        return green();
    }

    /// The same as [#blue()].
    @Override
    default N z() {
        return blue();
    }

    /// The same as [#alpha()].
    @Override
    default N w() {
        return alpha();
    }
}
