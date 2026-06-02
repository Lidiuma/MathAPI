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

import org.lidiuma.math.api.FloatingNumerical;
import org.lidiuma.math.api.Interpolatable;
import org.lidiuma.math.api.vector.Vector3;

/// Quaternion Operations type-class.\
/// Most operations assume the quaternion [Q] is normalized.
/// @param <N> The quaternion component type.
/// @param <Q> The quaternion.
public interface QuaternionOps<
        Q extends Quaternion<N>,
        V extends Vector3<N>,
        A extends Angle<N>,
        N> extends FloatingNumerical<Q>, Interpolatable<Q, N> {

    Q of(N x, N y, N z, N w);

    @Override
    default Q zero() {
        final N zero = scalarWitness().zero();
        return of(zero, zero, zero, zero);
    }

    @Override
    default Q one() {
        final N one = scalarWitness().one();
        return of(one, one, one, one);
    }

    default Q identity() {
        final N zero = scalarWitness().zero();
        final N one = scalarWitness().one();
        return of(zero, zero, zero, one);
    }

    Q fromAxisAngle(V axis, A angle);

    Q fromEulerAngle(A yaw, A pitch, A roll);

    /// @return the sum of all components of this quaternion.
    default N sum(Q quaternion) {
        final var witness = scalarWitness();
        final N xy = witness.add(quaternion.x(), quaternion.y());
        final N zw = witness.add(quaternion.z(), quaternion.w());
        return witness.add(xy, zw);
    }

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

    /// @return The conjugated quaternion.
    default Q conjugate(Q quaternion) {
        final var witness = scalarWitness();
        return of(
                witness.negated(quaternion.x()),
                witness.negated(quaternion.y()),
                witness.negated(quaternion.z()),
                quaternion.w()
        );
    }

    /// @return the inverse of `quaternion`.
    /// @apiNote The quaternion should be non-zero, otherwise division by zero occurs.
    default Q invert(Q quaternion) {
        final var witness = scalarWitness();
        final var length = lengthSquared(quaternion);
        final var scalar = witness.divide(witness.one(), length);
        return multiply(conjugate(quaternion), scalar);
    }

    /// @return the Euclidean length of `quaternion`.
    default N length(Q quaternion) {
        return scalarWitness().sqrt(lengthSquared(quaternion));
    }

    /// @return the Euclidean length squared of `quaternion`.
    default N lengthSquared(Q quaternion) {
        return dot(quaternion, quaternion);
    }

    /// @return a quaternion scaled to the provided `length`.
    default Q withLength(Q quaternion, N length) {
        return withMagnitude(quaternion, length, length(quaternion));
    }

    /// @return a quaternion with its length limited to `limit`.
    default Q withLimit(Q quaternion, N limit) {
        final N current = length(quaternion);
        if (scalarWitness().lessThanEqual(current, limit)) return quaternion;
        return withMagnitude(quaternion, limit, current);
    }

    /// @return the dot product of `first` and the `second` quaternion.
    /// @apiNote The operation is commutative.
    default N dot(Q q1, Q q2) {
        return sum(multiplyHadamard(q1, q2));
    }

    /// @return the normalized quaternion with length of 1.
    /// @apiNote The `quaternion` should be non-zero, otherwise division by zero occurs.
    /// To handle this case [#normalize(Quaternion)] can be used.
    default Q normalize(Q quaternion) {
        return withLength(quaternion, scalarWitness().one());
    }

    /// Similar to [#normalize] but when the length of `quaternion` is close to or is zero,
    /// the `fallback` quaternion is returned.
    /// @param fallback the value to use when the quaternion is close to zero.
    /// @return a normalized quaternion with length 1, or the `fallback` quaternion.
    default Q normalize(Q quaternion, N epsilon, Q fallback) {
        if (epsilonEquals(quaternion, zero(), epsilon)) return fallback;
        return normalize(quaternion);
    }

    /// Spherical interpolation between the `start` and `end` normalized quaternions.
    ///
    /// At small angles, to avoid numerical instability, the slerp will switch to a [#nlerp].
    /// @param end the other normalized quaternion.
    /// @param alpha value in the range of `[0,1]`.
    /// @return the interpolated normalized quaternion.
    Q slerp(Q start, Q end, N alpha);

    /// Normalized linearly interpolation between `start` and `end`.
    ///
    /// Performs a linear interpolation followed by normalization, keeping the shortest path.
    /// This is a faster approximation of slerp and does not produce constant angular velocity.
    ///
    /// @param end the value to interpolate towards.
    /// @param alpha the interpolation factor, typically in the range `[0, 1]`.
    /// @return the new linearly interpolated and normalized quaternion between `start` and `end`.
    default Q nlerp(Q start, Q end, N alpha) {
        final var ws = scalarWitness();
        final Q correctEnd = ws.lessThan(dot(start, end), ws.zero()) ? negated(end) : end;
        return normalize(lerp(start, correctEnd, alpha));
    }

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
    AxisAngle<V, A, N> axisAngle(Q quaternion, N epsilon);

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

    default boolean epsilonEquals(Q q1, Q q2, N epsilon) {
        final var abs = abs(subtract(q1, q2));
        return lessThanEqual(abs, of(epsilon, epsilon, epsilon, epsilon));
    }

    /// Multiplies `quaternion` by the given `scalar`.
    /// @return the multiplied quaternion.
    default Q multiply(Q quaternion, N scalar) {
        return multiplyHadamard(quaternion, of(scalar, scalar, scalar, scalar));
    }

    /// @return the component-wise multiplication of the `op1` and `op2`.
    default Q multiplyHadamard(Q op1, Q op2) {
        final var witness = scalarWitness();
        return of(
                witness.multiply(op1.x(), op2.x()),
                witness.multiply(op1.y(), op2.y()),
                witness.multiply(op1.z(), op2.z()),
                witness.multiply(op1.w(), op2.w())
        );
    }

    /// @return the component-wise addition of the `op1` and `op2`.
    @Override
    default Q add(Q op1, Q op2) {
        final var witness = scalarWitness();
        return of(
                witness.add(op1.x(), op2.x()),
                witness.add(op1.y(), op2.y()),
                witness.add(op1.z(), op2.z()),
                witness.add(op1.w(), op2.w())
        );
    }

    /// @return the component-wise subtraction of `op1` and `op2`.
    @Override
    default Q subtract(Q op1, Q op2) {
        final var witness = scalarWitness();
        return of(
                witness.subtract(op1.x(), op2.x()),
                witness.subtract(op1.y(), op2.y()),
                witness.subtract(op1.z(), op2.z()),
                witness.subtract(op1.w(), op2.w())
        );
    }

    /// Returns the Hamilton product of the `op1` and `op2`.\
    /// Can be used to compose the rotations of two quaternions.
    /// @return a new quaternion equal to `op1 * op2`
    /// @apiNote Quaternion multiplication is **not** commutative; `op1 * op2 != op2 * op1`.
    @Override
    default Q multiply(Q op1, Q op2) {

        final var ws = scalarWitness();
        final N wx = ws.multiply(op1.w(), op2.x());
        final N wy = ws.multiply(op1.w(), op2.y());
        final N wz = ws.multiply(op1.w(), op2.z());
        final N ww = ws.multiply(op1.w(), op2.w());

        final N xw = ws.multiply(op1.x(), op2.w());
        final N yw = ws.multiply(op1.y(), op2.w());
        final N zw = ws.multiply(op1.z(), op2.w());
        final N xx = ws.multiply(op1.x(), op2.x());

        final N yz = ws.multiply(op1.y(), op2.z());
        final N zx = ws.multiply(op1.z(), op2.x());
        final N xy = ws.multiply(op1.x(), op2.y());
        final N yy = ws.multiply(op1.y(), op2.y());

        final N zy = ws.multiply(op1.z(), op2.y());
        final N xz = ws.multiply(op1.x(), op2.z());
        final N yx = ws.multiply(op1.y(), op2.x());
        final N zz = ws.multiply(op1.z(), op2.z());

        final N newX = ws.add(ws.add(wx, xw), ws.subtract(yz, zy));
        final N newY = ws.add(ws.add(wy, yw), ws.subtract(zx, xz));
        final N newZ = ws.add(ws.add(wz, zw), ws.subtract(xy, yx));
        final N newW = ws.subtract(ws.subtract(ww, xx), ws.add(yy, zz));
        return of(newX, newY, newZ, newW);
    }

    /// Divides `op1` quaternion by `op2`.
    /// This is equivalent to `op1 * op2⁻¹`.
    /// @return a new quaternion equal to `op1 / op2`.
    /// @apiNote Quaternion division is **not** commutative; `op1 / op2 != op2 / op1`.
    @Override
    default Q divide(Q op1, Q op2) {
        return multiply(op1, invert(op2));
    }

    /// @return a quaternion with all its components negated.
    /// Equivalent to multiplying `quaternion` by the scalar `-1`.
    /// @apiNote The quaternion and its negation represent the same rotation.
    @Override
    default Q negated(Q operand) {
        final var witness = scalarWitness();
        return of(
                witness.negated(operand.x()),
                witness.negated(operand.y()),
                witness.negated(operand.z()),
                witness.negated(operand.w())
        );
    }

    @Override
    default Q signum(Q operand) {
        final var witness = scalarWitness();
        return of(
                witness.signum(operand.x()),
                witness.signum(operand.y()),
                witness.signum(operand.z()),
                witness.signum(operand.w())
        );
    }

    @Override
    default Q sqrt(Q operand) {
        final var witness = scalarWitness();
        return of(
                witness.sqrt(operand.x()),
                witness.sqrt(operand.y()),
                witness.sqrt(operand.z()),
                witness.sqrt(operand.w())
        );
    }

    @Override
    default Q ceil(Q operand) {
        final var witness = scalarWitness();
        return of(
                witness.ceil(operand.x()),
                witness.ceil(operand.y()),
                witness.ceil(operand.z()),
                witness.ceil(operand.w())
        );
    }

    @Override
    default Q floor(Q operand) {
        final var witness = scalarWitness();
        return of(
                witness.floor(operand.x()),
                witness.floor(operand.y()),
                witness.floor(operand.z()),
                witness.floor(operand.w())
        );
    }

    @Override
    default boolean lessThan(Q op1, Q op2) {
        final var witness = scalarWitness();
        return witness.lessThan(op1.x(), op2.x()) &&
               witness.lessThan(op1.y(), op2.y()) &&
               witness.lessThan(op1.z(), op2.z()) &&
               witness.lessThan(op1.w(), op2.w());
    }

    /// Returns the scalar [N] implementation of [FloatingNumerical].\
    /// Java will eventually provide a mechanism in the language to get the [FloatingNumerical] witness of [N].\
    /// By providing it now, like this, I can implement most of the APIs.
    /// @return the [FloatingNumerical] witness for [N].
    FloatingNumerical<N> scalarWitness();

    private Q withMagnitude(Q quaternion, N wanted, N current) {
        final N scalar = scalarWitness().divide(wanted, current);
        return multiply(quaternion, scalar);
    }
}
