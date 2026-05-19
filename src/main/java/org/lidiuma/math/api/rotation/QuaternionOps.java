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
public interface QuaternionOps<
        Q extends Quaternion<N>,
        V extends Vector3<N>,
        A extends Angle<N>,
        N> extends Numerical<Q>, Interpolatable<Q, N> {

    Q identity();

    Q fromAxisAngle(V axis, A angle);

    Q fromEulerAngle(A yaw, A pitch, A roll);

    /// @return the component-wise addition of the `left` and `right`.
    @Override
    Q add(Q left, Q right);

    /// @return the component-wise subtraction of `left` and `right`.
    @Override
    Q subtract(Q left, Q right);

    /// Returns the Hamilton product of the `left` and `right`.\
    /// Can be used to compose the rotations of two quaternions.
    /// @return a new quaternion equal to `left * right`
    /// @apiNote Quaternion multiplication is **not** commutative; `left * right != right * left`.
    @Override
    Q multiply(Q left, Q right);

    /// Multiplies `quaternion` by the given `scalar`.
    /// @return the multiplied quaternion.
    Q multiply(Q quaternion, N scalar);

    /// Divides `left` quaternion by `right`.
    /// This is equivalent to `left * right⁻¹`.
    /// @return a new quaternion equal to `left / right`.
    /// @apiNote Quaternion division is **not** commutative; `left / right != right / left`.
    @Override
    Q divide(Q left, Q right);

    /// @return the exponential of `quaternion`.
    /// @apiNote If `quaternion` is not normalized,
    /// the resulting quaternion will include a scale but will no longer represent a pure rotation.
    Q exp(Q quaternion);

    /// @return the logarithm of `quaternion`.
    /// @apiNote If `quaternion` is not normalized,
    /// the resulting quaternion will include a scale but will no longer represent a pure rotation.
    Q log(Q quaternion);

    /// @param alpha The exponent.
    /// @return the `quaternion` raised to the power of `alpha`.
    /// @apiNote If `quaternion` is not normalized,
    /// the resulting quaternion will include a scale but will no longer represent a pure rotation.
    Q pow(Q quaternion, N alpha);

    /// @return a quaternion with all its components negated.
    /// Equivalent to multiplying `quaternion` by the scalar `-1`.
    /// @apiNote The quaternion and its negation represent the same rotation.
    @Override
    Q negated(Q quaternion);

    /// @return The conjugated quaternion.
    Q conjugated(Q quaternion);

    /// @return the inverse of `quaternion`.
    /// @apiNote The quaternion should be non-zero, otherwise division by zero occurs.
    Q inverted(Q quaternion);

    /// @return the Euclidean length of `quaternion`.
    N length(Q quaternion);

    /// @return the Euclidean length squared of `quaternion`.
    N lengthSquared(Q quaternion);

    /// @return the dot product of `first` and the `second` quaternion.
    /// @apiNote The operation is commutative.
    N dot(Q first, Q second);

    /// @return the normalized quaternion with length of 1.
    /// @apiNote The `quaternion` should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalized(Quaternion)] can be used.
    Q normalized(Q quaternion);

    /// Similar to [#normalized()] but when the length of `quaternion` is close to or is zero,
    /// the `orElse` quaternion is returned.
    /// @param orElse the value to use when the quaternion is close to zero.
    /// @return a normalized quaternion with length 1, or the `orElse` quaternion.
    Q normalized(Q quaternion, Q orElse);

    /// Spherical interpolation between the `start` and `end` normalized quaternions.
    ///
    /// At small angles, to avoid numerical instability, the slerp will switch to a [#nlerp].
    /// @param end the other normalized quaternion.
    /// @param alpha value in the range of `[0,1]`.
    /// @return the interpolated normalized quaternion.
    Q slerp(Q start, Q end, N alpha);

    /// Normalized linearly interpolation between `start` and `end`.
    ///
    /// Performs a linear interpolation followed by normalization.
    /// This is a faster approximation of slerp and does not produce constant angular velocity.
    ///
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @return the new linearly interpolated and normalized quaternion between `start` and `end`.
    Q nlerp(Q start, Q end, N alpha);

    /// Rotates the given vector using the provided `quaternion`.
    ///
    /// @param vector the vector to rotate.
    /// @return a new rotated vector.
    /// @apiNote The `quaternion` should be normalized for correct results.\
    /// If not normalized, the result will be rotated and uniformly
    /// scaled by the squared length of the quaternion.
    V rotate(Q quaternion, V vector);

    /// Unrotates the given vector using the provided `quaternion`.
    ///
    /// @param vector the vector to unrotate.
    /// @return a new unrotated vector.
    /// @apiNote The `quaternion` should be normalized for correct results.\
    /// If not normalized, the result will be unrotated and uniformly
    /// scaled by the squared length of the quaternion.
    V unrotate(Q quaternion, V vector);

    /// Returns the axis-angle representation of a normalized `quaternion`'s rotation.
    /// @return {@link AxisAngle} containing both the normalized axis and the angle.
    AxisAngle<V, A, N> axisAngle(Q quaternion);

    /// @return the rotation angle of the normalized `quaternion`.
    A angle(Q quaternion);

    /// Gets the swing rotation and twist rotation for the specified axis.
    /// - The twist rotation represents the rotation around the specified axis.
    /// - The swing rotation represents the rotation of the specified axis itself, which is the rotation around an axis perpendicular to the specified axis.
    ///
    ///  The swing and twist rotation can be used to reconstruct the original quaternion; `quaternion = swing * twist`.
    ///
    /// @param axis of which to get the swing and twist rotation.
    /// @return the `swing` and `twist` pair.
    /// @apiNote The quaternion and the axis should be normalized for correct results.
    SwingTwist<Q, N> swingTwist(Q quaternion, V axis);

    /// @param axis the non-zero normalized rotation axis.
    /// @return the rotation angle around the given axis.
    /// @apiNote The `quaternion` should be normalized for correct results and the axis must be non-zero,
    /// otherwise a zero square root occurs.
    A angleAround(Q quaternion, V axis);
}
