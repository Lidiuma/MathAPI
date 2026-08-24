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

package org.lidiuma.math.api.traits;

import java.util.function.UnaryOperator;

/// Indicates a type that supports interpolation.
/// @param <T> the type of the object being interpolated.
/// @param <N> the numeric type used for the interpolation.
@FunctionalInterface
public interface Interpolatable<T, N> {

    /// Interpolates between `start` and `end`.
    /// @param start the value to interpolate from.
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @param easing a function to adjust the interpolation curve ([identity][UnaryOperator#identity()] for linear).
    /// @return the new interpolated value between `start` and the `end`.
    T interpolate(T start, T end, N alpha, UnaryOperator<N> easing);

    /// Linearly interpolates between `start` and `end`.
    /// @param start the value to interpolate from.
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @return the new linearly interpolated value between `start` and the `end`.
    default T lerp(T start, T end, N alpha) {
        return interpolate(start, end, alpha, UnaryOperator.identity());
    }
}
