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

package org.lidiuma.math.api;

import org.lidiuma.math.api.tuple.UnaryTuple4;

/// Generic Color interface.
/// @param <N> The floating type representing the color, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
/// A different internal representation can be used, as long as it is converted to `N`.
public interface Color<N> extends Interpolatable<Color<N>, N>, UnaryTuple4<N> {

    /// @return The red channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N red();

    /// @return The green channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N green();

    /// @return The blue channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N blue();

    /// @return The alpha channel, usually in the range `[0,1]`, can be more for [HDR](https://en.wikipedia.org/wiki/High_dynamic_range).
    N alpha();

    // TODO Use new clampable and make a ColorOps type-class.
    /// @return a color with each component clamped between `min` and `max`.
    Color<N> clamp(N min, N max);

    /// @return a color with a component-wise clamp between `min` and `max`.
    Color<N> clamp(UnaryTuple4<N> min, UnaryTuple4<N> max);

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
