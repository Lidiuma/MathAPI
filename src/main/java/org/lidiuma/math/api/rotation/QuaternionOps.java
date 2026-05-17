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

package org.lidiuma.math.api.rotation;

import org.lidiuma.math.api.Interpolatable;
import org.lidiuma.math.api.Numerical;
import org.lidiuma.math.api.vector.Vector3;

/// Quaternion Operations type-class.\
/// Most operations assume the quaternion [Q] is normalized.
/// @param <N> The quaternion component type.
/// @param <Q> The quaternion.
public interface QuaternionOps<N, Q> extends Numerical<Q>, Interpolatable<Q, N> {

    /// @return the component-wise addition of the `addend` and `augend`.
    @Override
    Q add(Q addend, Q augend);

    /// @return the component-wise subtraction of `this` and `other`.
    @Override
    Q subtract(Q minuend, Q subtrahend);

    /// Returns the Hamilton product of the `multiplier` and `multiplicand`.\
    /// Can be used to compose the rotations of two quaternions.
    /// @return a new quaternion equal to `multiplier * multiplicand`
    /// @apiNote Quaternion multiplication is **not** commutative; `multiplier * multiplicand != multiplicand * multiplier`.
    @Override
    Q multiply(Q multiplier, Q multiplicand);

    /// Multiplies this quaternion by the given scalar.
    /// @return the multiplied quaternion.
    Q multiplyScalar(N scalar);

    /// Divides `dividend` quaternion by `divisor`.
    /// This is equivalent to `dividend * divisor⁻¹`.
    /// @return a new quaternion equal to `dividend / divisor`.
    /// @apiNote Quaternion division is **not** commutative; `dividend / divisor != divisor / dividend`.
    @Override
    Q divide(Q dividend, Q divisor);

    /// @return the exponential of this quaternion.
    /// @apiNote If this quaternion is not normalized,
    /// the resulting quaternion will include a scale but will no longer represent a pure rotation.
    Q exp(Q quaternion);

    /// @return the logarithm of this quaternion.
    /// @apiNote If this quaternion is not normalized,
    /// the resulting quaternion will include a scale but will no longer represent a pure rotation.
    Q log(Q quaternion);

    /// @param alpha The exponent.
    /// @return this quaternion raised to the power of `alpha`.
    /// @apiNote If this quaternion is not normalized,
    /// the resulting quaternion will include a scale but will no longer represent a pure rotation.
    Q pow(Q quaternion, N alpha);

    /// @return a quaternion with all its components negated.
    /// Equivalent to multiplying this quaternion by the scalar `-1`.
    /// @apiNote This quaternion and its negation represent the same rotation.
    @Override
    Q negated(Q quaternion);

    /// @return The conjugated quaternion.
    Q conjugated(Q quaternion);

    /// @return the inverse of this quaternion.
    /// @apiNote This quaternion should be non-zero, otherwise division by zero occurs.
    Q inverted(Q quaternion);

    /// @return the Euclidean length of this quaternion.
    N length(Q quaternion);

    /// @return the Euclidean length squared of this quaternion.
    N lengthSquared(Q quaternion);

    /// @return the dot product of `first` and the `second` quaternion.
    /// @apiNote The operation is commutative.
    N dot(Q first, Q second);

    /// @return the normalized quaternion with length of 1.
    /// @apiNote This quaternion should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalized(Quaternion)] can be used.
    Q normalized(Q quaternion);

    /// Similar to [#normalized()] but when the length of `this` quaternion is close to or is zero,
    /// the `orElse` quaternion is returned.
    /// @param orElse the value to use when the quaternion is close to zero.
    /// @return a normalized quaternion with length 1, or the `orElse` quaternion.
    Q normalized(Q quaternion, Q orElse);

    /// Spherical interpolation between this normalized quaternion and the other normalized quaternion.
    ///
    /// At small angles, to avoid numerical instability, the slerp will switch to a [#nlerp].
    /// @param end the other normalized quaternion.
    /// @param alpha value in the range of `[0,1]`.
    /// @return the interpolated normalized quaternion.
    Q slerp(Q start, Q end, N alpha);

    /// Normalized linearly interpolation between `this` and `target`.
    ///
    /// Performs a linear interpolation followed by normalization.
    /// This is a faster approximation of slerp and does not produce constant angular velocity.
    ///
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @return the new linearly interpolated and normalized quaternion between `this` and the `target`.
    Q nlerp(Q start, Q end, N alpha);

    /// Rotates the given vector using this quaternion.
    ///
    /// @param v3 the vector to rotate.
    /// @return a new rotated vector.
    /// @apiNote This quaternion should be normalized for correct results.\
    /// If not normalized, the result will be rotated and uniformly
    /// scaled by the squared length of the quaternion.
    Vector3<N> rotate(Q quaternion, Vector3<N> v3);

    /// Unrotates the given vector using this quaternion.
    ///
    /// @param v3 the vector to unrotate.
    /// @return a new unrotated vector.
    /// @apiNote This quaternion should be normalized for correct results.\
    /// If not normalized, the result will be unrotated and uniformly
    /// scaled by the squared length of the quaternion.
    Vector3<N> unrotate(Q quaternion, Vector3<N> v3);

    /// @return the roll (rotation around the z-axis) angle between -π and +π.
    /// @apiNote This quaternion should be normalized for correct results.
    Angle<N> roll(Q quaternion);

    /// @return the pitch (rotation around the x-axis) angle between -(π/2) and +(π/2).
    /// @apiNote This quaternion should be normalized for correct results.
    Angle<N> pitch(Q quaternion);

    /// When the quaternion is in a Gimbal-lock configuration, the yaw is set to zero by convention.
    /// @return the yaw (rotation around the y-axis) angle between -π and +π.
    /// @apiNote This quaternion should be normalized for correct results.
    Angle<N> yaw(Q quaternion);

    /// @return {@link GimbalPole#NORTH} or {@link GimbalPole#SOUTH} if the gimbal-lock is present, otherwise {@link GimbalPole#NONE}.
    GimbalPole gimbalPole(Q quaternion);

    /// Returns the axis-angle representation of this normalized quaternion's rotation.
    /// @return {@link AxisAngle} containing both the normalized axis and the angle.
    AxisAngle<N> axisAngle(Q quaternion);

    /// @return the rotation angle of this normalized quaternion.
    Angle<N> angle(Q quaternion);

    /// Gets the swing rotation and twist rotation for the specified axis.
    /// - The twist rotation represents the rotation around the specified axis.
    /// - The swing rotation represents the rotation of the specified axis itself, which is the rotation around an axis perpendicular to the specified axis.
    ///
    ///  The swing and twist rotation can be used to reconstruct the original quaternion; `this = swing * twist`.
    ///
    /// @param axis of which to get the swing and twist rotation.
    /// @return the `swing` and `twist` pair.
    /// @apiNote The quaternion and the axis should be normalized for correct results.
    SwingTwist<N> swingTwist(Q quaternion, Vector3<N> axis);

    /// @param axis the non-zero normalized rotation axis.
    /// @return the rotation angle around the given axis.
    /// @apiNote This quaternion should be normalized for correct results and the axis must be non-zero, otherwise a zero square root occurs.
    Angle<N> angleAround(Q quaternion, Vector3<N> axis);
}
