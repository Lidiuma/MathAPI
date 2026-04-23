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

import java.util.function.UnaryOperator;

/// Generic interface representing an object that can be interpolated.
/// @param <T> the type of the object being interpolated.
/// @param <N> the numeric type used for interpolation.
@FunctionalInterface
public interface Interpolatable<T, N> { // TODO Make this a type-class.

    /// Interpolates between `this` and `target`.
    /// @param target the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @param easing a function to adjust the interpolation curve ([identity][UnaryOperator#identity()] for linear).
    /// @return the new interpolated value between `this` and the `target`.
    T interpolate(T target, N alpha, UnaryOperator<N> easing);

    /// Linearly interpolates between `this` and `target`.
    /// @param target the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @return the new linearly interpolated value between `this` and the `target`.
    default T lerp(T target, N alpha) {
        return interpolate(target, alpha, UnaryOperator.identity());
    }
}
