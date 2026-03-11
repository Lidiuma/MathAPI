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
import org.lidiuma.math.api.vector.Vector4;

public interface Quaternion<N> extends UnaryTuple4<N> {

    /// Returns a [Vector4<N>] representation of this quaternion.
    Vector4<N> v4();

    /// Adds the components of the two quaternion together.
    Quaternion<N> add(Quaternion<N> other);

    /// Returns the Hamilton product of `this` quaternion and `other`.
    ///
    /// @param other the quaternion to multiply.
    /// @return a new quaternion equal to `this * other`
    /// @apiNote Order is important! `this * other != other * this`
    Quaternion<N> mul(Quaternion<N> other);

    /**
     * Multiplies the components of this quaternion with the given scalar.
     *
     * @param scalar the scalar.
     * @return this quaternion for chaining.
     */
    Quaternion<N> mul(N scalar);

    /// Returns the power of `quaternion^alpha`.
    /// @param alpha The exponent.
    Quaternion<N> pow(N alpha);

    /**
     * @return the Euclidean length of this quaternion.
     */
    N length();

    /**
     * @return the length of this quaternion without square root
     */
    N length2();

    /**
     * Normalizes this quaternion to unit length
     *
     * @return the quaternion for chaining
     */
    Quaternion<N> normalized();

    /**
     * Get the dot product between this and the other quaternion (commutative).
     *
     * @param other the other quaternion.
     * @return the dot product of this and the other quaternion.
     */
    N dot(Quaternion<N> other);

    /**
     * Gets the pole of the gimbal lock, if any.
     *
     * @return {@link GimbalPole#NORTH}, {@link GimbalPole#SOUTH}, or {@link GimbalPole#NONE}
     */
    GimbalPole gimbalPole();

    /// Returns the roll (rotation around the z-axis) angle.
    ///
    /// @return the roll, between -π and +π.
    /// @apiNote The quaternion should be normalized for correct results.
    Angle<N> roll();

    /// Returns the pitch (rotation around the x-axis) angle.
    ///
    /// @return the pitch, between -(π/2) and +(π/2).
    /// @apiNote The quaternion should be normalized for correct results.
    Angle<N> pitch();

    /// Returns the yaw (rotation around the y-axis) angle.
    ///
    /// @return the yaw, between -π and +π.
    /// @apiNote The quaternion should be normalized for correct results.
    ///  When the quaternion is in a Gimbal-lock configuration, the yaw is set to zero by convention.
    Angle<N> yaw();

    /// @return The conjugated quaternion
    Quaternion<N> conjugated();

    /// Rotates the given vector using this quaternion.
    ///
    /// @param v3 the vector to rotate
    /// @return a new rotated vector.
    /// @apiNote The quaternion is normalized internally.
    Vector3<N> rotate(Vector3<N> v3);

    /// Spherical interpolation between this quaternion and the other quaternion.
    /// @param end the other quaternion.
    /// @param alpha value in the range of `[0,1]`.
    /// @param epsilon threshold to switch between lerp and full slerp at small angles.
    /// @return the interpolated quaternion.
    /// @apiNote The quaternions are normalized internally.
    Quaternion<N> slerp(Quaternion<N> end, N alpha, N epsilon);

    /// Returns the axis-angle representation of this quaternion's rotation.
    /// @return {@link AxisAngle} containing both the axis (as a unit vector) and the angle.
    /// @apiNote The quaternion is normalized internally.
    AxisAngle<N> axisAngle();

    /// @return the rotation angle of this quaternion.
    /// @apiNote The quaternion is normalized internally.
    Angle<N> angle();

    /// Gets the swing rotation and twist rotation for the specified axis.
    /// - The twist rotation represents the rotation around the specified axis.
    /// - The swing rotation represents the rotation of the specified axis itself, which is the rotation around an axis perpendicular to the specified axis.
    ///
    ///  The swing and twist rotation can be used to reconstruct the original quaternion; `this = swing * twist`.
    ///
    /// @param axis of which to get the swing and twist rotation.
    /// @return the `swing` and `twist` pair.
    /// @apiNote The axis is normalized internally.
    SwingTwist<N> swingTwist(Vector3<N> axis);

    /**
     * Get the angle of the rotation around the specified axis. The axis must be normalized.
     *
     * @param axis the normalized axis for which to get the angle
     * @return the angle of the rotation around the specified axis
     */
    Angle<N> angleAround(Vector3<N> axis);

    /**
     * @param epsilon allowed deviation from exact identity
     * @return true if this quaternion is approximately identity
     */
    boolean isIdentity(N epsilon);
}
