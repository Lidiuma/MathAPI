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

package org.lidiuma.math.api.traits.numeric;

/// Numerical operations for floating points.
public interface FloatingNumerical<N> extends Numerical<N> {

    /// Returns the sign of the provided operand.
    /// @param operand the operand to get the signum from.
    /// @return `+1` if `operand > 0`, `-1` if `operand < 0`, or `0` if `operand == 0`.
    N signum(N operand);

    /// Returns the absolute value of the provided operand.
    /// @param operand the operand to get the absolute value from.
    /// @return the non-negative value of `operand`.
    default N abs(N operand) {
        return multiply(operand, signum(operand));
    }

    /// Returns the square root of the provided operand.
    /// @param operand the operand to compute the square root from.
    /// @return the square root of `operand`.
    N sqrt(N operand);

    /// Returns the smallest integer value that is greater than or equal to the provided operand.
    /// This operation rounds the operand up to the nearest integer.
    /// @param operand the operand to round up.
    /// @return the ceiling of `operand`.
    N ceil(N operand);

    /// Returns the largest integer value that is less than or equal to the provided operand.
    /// This operation rounds the operand down to the nearest integer.
    /// @param operand the operand to round down.
    /// @return the floor of `operand`.
    N floor(N operand);
}
