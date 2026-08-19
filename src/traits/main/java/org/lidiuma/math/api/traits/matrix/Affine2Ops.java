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

package org.lidiuma.math.api.traits.matrix;

import org.lidiuma.math.api.matrix.Affine2;
import org.lidiuma.math.api.vector.Vector2;
import org.lidiuma.math.api.traits.vector.Vector2Ops;

/// Operations for [Affine2], specialized for 2D transformation.
public interface Affine2Ops<
        M extends Affine2<N>,
        V extends Vector2<N>,
        N> extends AffineOps<M, V, N> {

    /// Constructs [M] using the provided scalars.
    M of(N m00, N m01, N m02,
         N m10, N m11, N m12);

    /// Creates an affine matrix from two axes and a translation vector.
    /// @param xAxis The x-axis of the linear part (first column).
    /// @param yAxis The y-axis of the linear part (second column).
    /// @param translation The translation vector (last column).
    /// @return an affine matrix representing the given axes and translation.
    default M fromAxes(V xAxis, V yAxis, V translation) {
        return of(
                xAxis.x(), xAxis.y(), translation.x(),
                yAxis.x(), yAxis.y(), translation.y()
        );
    }

    @Override
    default M fromTranslation(V translation) {
        final var ops = vectorOps().scalarOps();
        final var zero = ops.zero();
        final var one = ops.one();
        return of(
                one, zero, translation.x(),
                zero, one, translation.y()
        );
    }

    @Override
    default M fromScale(V scale) {
        final var zero = vectorOps().scalarOps().zero();
        return of(
                scale.x(), zero, zero,
                zero, scale.y(), zero
        );
    }

    @Override
    default M zero() {
        final var zero = vectorOps().scalarOps().zero();
        return of(
                zero, zero, zero,
                zero, zero, zero
        );
    }

    @Override
    default M one() {
        final var one = vectorOps().scalarOps().one();
        return of(
                one, one, one,
                one, one, one
        );
    }

    @Override
    default M identity() {
        final var ops = vectorOps().scalarOps();
        final var one = ops.one();
        final var zero = ops.zero();
        return of(
                one, zero, zero,
                zero, one, zero
        );
    }

    /// Transposes the 2x2 sub-matrix (linear part) of this affine matrix, ignoring the translation part.
    /// @return the transposed affine matrix.
    @Override
    default M transpose(M affine) {
        return of(
                affine.m00(), affine.m10(), affine.m02(),
                affine.m01(), affine.m11(), affine.m12()
        );
    }

    @Override
    default N determinant(M matrix) {
        final var ops = vectorOps().scalarOps();
        final N m0011 = ops.multiply(matrix.m00(), matrix.m11());
        final N m0110 = ops.multiply(matrix.m01(), matrix.m10());
        return ops.subtract(m0011, m0110);
    }

    @Override
    default M inverse(M matrix) throws ArithmeticException {

        final var ops = vectorOps().scalarOps();
        final N det = determinant(matrix);
        if (det.equals(ops.zero())) throw new ArithmeticException("The matrix cannot be inverted since singular.");

        final N invDet = ops.divide(ops.one(), det);

        final N m02 = ops.subtract(ops.multiply(matrix.m01(), matrix.m12()), ops.multiply(matrix.m11(), matrix.m02()));
        final N m12 = ops.subtract(ops.multiply(matrix.m10(), matrix.m02()), ops.multiply(matrix.m00(), matrix.m12()));

        final N n00 = ops.multiply(invDet, matrix.m11());
        final N n01 = ops.multiply(invDet, ops.negated(matrix.m01()));
        final N n02 = ops.multiply(invDet, m02);
        final N n10 = ops.multiply(invDet, ops.negated(matrix.m10()));
        final N n11 = ops.multiply(invDet, matrix.m00());
        final N n12 = ops.multiply(invDet, m12);
        return of(
                n00, n01, n02,
                n10, n11, n12
        );
    }

    @Override
    default V multiply(M matrix, V vector) {

        final var ops = vectorOps().scalarOps();

        final N m00 = ops.multiply(matrix.m00(), vector.x());
        final N m01 = ops.multiply(matrix.m01(), vector.y());

        final N m10 = ops.multiply(matrix.m10(), vector.x());
        final N m11 = ops.multiply(matrix.m11(), vector.y());

        final N x = ops.add(ops.add(m00, m01), matrix.m02());
        final N y = ops.add(ops.add(m10, m11), matrix.m12());

        return vectorOps().of(x, y);
    }

    @Override
    default M multiply(M matrix, N scalar) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.multiply(matrix.m00(), scalar);
        final N m10 = ops.multiply(matrix.m10(), scalar);
        final N m01 = ops.multiply(matrix.m01(), scalar);
        final N m11 = ops.multiply(matrix.m11(), scalar);
        final N m02 = ops.multiply(matrix.m02(), scalar);
        final N m12 = ops.multiply(matrix.m12(), scalar);
        return of(
                m00, m01, m02,
                m10, m11, m12
        );
    }

    @Override
    default M add(M op1, M op2) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.add(op1.m00(), op2.m00());
        final N m10 = ops.add(op1.m10(), op2.m10());
        final N m01 = ops.add(op1.m01(), op2.m01());
        final N m11 = ops.add(op1.m11(), op2.m11());
        final N m02 = ops.add(op1.m02(), op2.m02());
        final N m12 = ops.add(op1.m12(), op2.m12());
        return of(
                m00, m01, m02,
                m10, m11, m12
        );
    }

    @Override
    default M subtract(M op1, M op2) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.subtract(op1.m00(), op2.m00());
        final N m10 = ops.subtract(op1.m10(), op2.m10());
        final N m01 = ops.subtract(op1.m01(), op2.m01());
        final N m11 = ops.subtract(op1.m11(), op2.m11());
        final N m02 = ops.subtract(op1.m02(), op2.m02());
        final N m12 = ops.subtract(op1.m12(), op2.m12());
        return of(
                m00, m01, m02,
                m10, m11, m12
        );
    }

    @Override
    default M multiply(M op1, M op2) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.add(ops.multiply(op1.m00(), op2.m00()), ops.multiply(op1.m01(), op2.m10()));
        final N m01 = ops.add(ops.multiply(op1.m00(), op2.m01()), ops.multiply(op1.m01(), op2.m11()));
        final N m02 = ops.add(ops.multiply(op1.m00(), op2.m02()), ops.multiply(op1.m01(), op2.m12()));
        final N m10 = ops.add(ops.multiply(op1.m10(), op2.m00()), ops.multiply(op1.m11(), op2.m10()));
        final N m11 = ops.add(ops.multiply(op1.m10(), op2.m01()), ops.multiply(op1.m11(), op2.m11()));
        final N m12 = ops.add(ops.multiply(op1.m10(), op2.m02()), ops.multiply(op1.m11(), op2.m12()));
        return of(
                m00, m01, ops.add(m02, op1.m02()),
                m10, m11, ops.add(m12, op1.m12())
        );
    }

    @Override
    default M remainder(M op1, M op2) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.remainder(op1.m00(), op2.m00());
        final N m10 = ops.remainder(op1.m10(), op2.m10());
        final N m01 = ops.remainder(op1.m01(), op2.m01());
        final N m11 = ops.remainder(op1.m11(), op2.m11());
        final N m02 = ops.remainder(op1.m02(), op2.m02());
        final N m12 = ops.remainder(op1.m12(), op2.m12());
        return of(
                m00, m01, m02,
                m10, m11, m12
        );
    }

    @Override
    default M negated(M operand) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.negated(operand.m00());
        final N m10 = ops.negated(operand.m10());
        final N m01 = ops.negated(operand.m01());
        final N m11 = ops.negated(operand.m11());
        final N m02 = ops.negated(operand.m02());
        final N m12 = ops.negated(operand.m12());
        return of(
                m00, m01, m02,
                m10, m11, m12
        );
    }

    @Override
    Vector2Ops<V, N> vectorOps();
}
