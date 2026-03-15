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

import org.lidiuma.math.api.tuple.UnaryTuple4;
import org.lidiuma.math.api.vector.Vector3;

public interface Quaternion<N> extends UnaryTuple4<N> {

    /// Returns the Hamilton product of `this` quaternion and `other`.
    ///
    /// @param other the quaternion to multiply.
    /// @return a new quaternion equal to `this * other`
    /// @apiNote Order is important! `this * other != other * this`
    Quaternion<N> mul(Quaternion<N> other);

    /// Multiplies this quaternion with the given scalar.
    Quaternion<N> mul(N scalar);

    /// Returns the power of `quaternion^alpha`.
    /// @param alpha The exponent.
    Quaternion<N> pow(N alpha);

    /// @return the Euclidean length of this quaternion.
    N length();

    /// @return the Euclidean length squared of this quaternion.
    N length2();

    /// @return the normalized quaternion with length of 1.
    /// @apiNote This quaternion should be non-zero, otherwise division by zero occurs.
    Quaternion<N> normalized();

    /// @return the dot product of this and the other quaternion.
    /// @apiNote The operation is commutative.
    N dot(Quaternion<N> other);

    /// @return {@link GimbalPole#NORTH} or {@link GimbalPole#SOUTH} if the gimbal-lock is present, otherwise {@link GimbalPole#NONE}.
    GimbalPole gimbalPole();

    /// @return the roll (rotation around the z-axis) angle between -π and +π.
    /// @apiNote This quaternion should be normalized for correct results.
    Angle<N> roll();

    /// @return the pitch (rotation around the x-axis) angle between -(π/2) and +(π/2).
    /// @apiNote This quaternion should be normalized for correct results.
    Angle<N> pitch();

    /// @return the yaw (rotation around the y-axis) angle between -π and +π.
    /// When the quaternion is in a Gimbal-lock configuration, the yaw is set to zero by convention.
    /// @apiNote This quaternion should be normalized for correct results.
    Angle<N> yaw();

    /// @return The conjugated quaternion.
    Quaternion<N> conjugated();

    /// @return the inverse of this quaternion.
    /// @apiNote This quaternion should be non-zero, otherwise division by zero occurs.
    Quaternion<N> inverted();

    /// Rotates the given vector using this quaternion.
    ///
    /// @param v3 the vector to rotate.
    /// @return a new rotated vector.
    /// @apiNote This quaternion should be normalized for correct results.\
    /// If not normalized, the result will be rotated and uniformly
    /// scaled by the squared length of the quaternion.
    Vector3<N> rotate(Vector3<N> v3);

    /// Unrotates the given vector using this quaternion.
    ///
    /// @param v3 the vector to unrotate.
    /// @return a new unrotated vector.
    /// @apiNote This quaternion should be normalized for correct results.\
    /// If not normalized, the result will be unrotated and uniformly
    /// scaled by the squared length of the quaternion.
    Vector3<N> unrotate(Vector3<N> v3);

    /// Spherical interpolation between this normalized quaternion and the other normalized quaternion.
    /// @param end the other normalized quaternion.
    /// @param alpha value in the range of `[0,1]`.
    /// @param epsilon threshold to switch between lerp and full slerp at small angles.
    /// @return the interpolated normalized quaternion.
    Quaternion<N> slerp(Quaternion<N> end, N alpha, N epsilon);

    /// Returns the axis-angle representation of this normalized quaternion's rotation.
    /// @return {@link AxisAngle} containing both the normalized axis and the angle.
    AxisAngle<N> axisAngle();

    /// @return the rotation angle of this normalized quaternion.
    Angle<N> angle();

    /// Gets the swing rotation and twist rotation for the specified axis.
    /// - The twist rotation represents the rotation around the specified axis.
    /// - The swing rotation represents the rotation of the specified axis itself, which is the rotation around an axis perpendicular to the specified axis.
    ///
    ///  The swing and twist rotation can be used to reconstruct the original quaternion; `this = swing * twist`.
    ///
    /// @param axis of which to get the swing and twist rotation.
    /// @return the `swing` and `twist` pair.
    /// @apiNote The quaternion and the axis should be normalized for correct results.
    SwingTwist<N> swingTwist(Vector3<N> axis);

    /// @param axis the non-zero normalized rotation axis.
    /// @return the rotation angle around the given axis.
    /// @apiNote This quaternion should be normalized for correct results and the axis must be non-zero, otherwise a zero square root occurs.
    Angle<N> angleAround(Vector3<N> axis);
}
