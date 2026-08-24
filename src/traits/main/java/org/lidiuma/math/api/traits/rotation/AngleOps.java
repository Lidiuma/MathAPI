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

package org.lidiuma.math.api.traits.rotation;

import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.traits.Interpolatable;
import org.lidiuma.math.api.vector.Vector;
import org.lidiuma.math.api.vector.Vector2;
import java.util.function.UnaryOperator;

/// Angle Operations type-class.
/// @param <A> The [Angle].
/// @param <V> the [Vector] type.
/// @param <N> the numeric type.
public interface AngleOps<
        A extends Angle<N>,
        V extends Vector2<N>,
        N> extends Interpolatable<A, N> {

    /// Constructs an angle from radians.
    /// @return the angle.
    A fromRadian(N radians);

    /// Constructs an angle from degrees.
    /// @return the angle.
    A fromDegree(N degrees);

    /// Constructs an angle from turns.
    /// @return the angle.
    A fromTurn(N turns);

    /// Constructs an angle from a unit vector.
    /// @return the angle represented by the vector's direction.
    A fromVector(V vector);

    /// Returns the cosine of an angle.
    /// @return the cosine.
    N cos(A angle);

    /// Returns the sine of an angle.
    /// @return the sine.
    N sin(A angle);

    /// Returns the tangent of an angle.
    /// @return the tangent.
    N tan(A angle);

    /// Normalizes the angle between 0 and 1 turn.
    /// @return the normalized angle.
    A normalize(A angle);

    /// Adds two angles.
    /// @return the sum of the angles.
    A add(A op1, A op2);

    /// Subtracts two angles.
    /// @return the difference of the angles.
    A subtract(A op1, A op2);

    /// Multiplies an angle by a scalar.
    /// @return the scaled angle.
    A multiply(A op1, N scalar);

    /// Divides an angle by a scalar.
    /// @return the scaled angle.
    A divide(A op1, N scalar);

    /// Returns the opposite direction of the angle.
    /// @return the negated angle.
    A negated(A operand);

    /// Spherically interpolates between `start` and `end` angles.
    /// @param start the value to interpolate from.
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @param easing a function to adjust the interpolation curve ([identity][UnaryOperator#identity()] for linear).
    /// @return the new interpolated value between `start` and `end`.
    @Override
    A interpolate(A start, A end, N alpha, UnaryOperator<N> easing);

    /// Spherically interpolates between `start` and `end` with linear easing.
    /// @param start the value to interpolate from.
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @return the new linearly interpolated value between `start` and `end`.
    @Override
    default A lerp(A start, A end, N alpha) {
        return Interpolatable.super.lerp(start, end, alpha);
    }
}
