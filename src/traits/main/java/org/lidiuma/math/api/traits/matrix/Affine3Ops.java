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

import org.lidiuma.math.api.matrix.Affine3;
import org.lidiuma.math.api.vector.Vector3;
import org.lidiuma.math.api.traits.vector.Vector3Ops;

/// Operations for [Affine3], specialized for 3D transformation.
public interface Affine3Ops<
        M extends Affine3<N>,
        V extends Vector3<N>,
        N> extends AffineOps<M, V, N> {

    M of(N m00, N m01, N m02, N m03,
         N m10, N m11, N m12, N m13,
         N m20, N m21, N m22, N m23);

    /// Creates an affine matrix from three axes and a translation vector.
    /// @param xAxis The x-axis of the linear part (first column).
    /// @param yAxis The y-axis of the linear part (second column).
    /// @param zAxis The z-axis of the linear part (third column).
    /// @param translation The translation vector (last column).
    /// @return an affine matrix representing the given axes and translation.
    default M fromAxes(V xAxis, V yAxis, V zAxis, V translation) {
        return of(
                xAxis.x(), xAxis.y(), xAxis.z(), translation.x(),
                yAxis.x(), yAxis.y(), yAxis.z(), translation.y(),
                zAxis.x(), zAxis.y(), zAxis.z(), translation.z()
        );
    }

    @Override
    default M fromTranslation(V translation) {
        final var ops = vectorOps().scalarOps();
        final var zero = ops.zero();
        final var one = ops.one();
        return of(
                one, zero, zero, translation.x(),
                zero, one, zero, translation.y(),
                zero, zero, one, translation.z()
        );
    }

    @Override
    default M fromScale(V scale) {
        final var zero = vectorOps().scalarOps().zero();
        return of(
                scale.x(), zero, zero, zero,
                zero, scale.y(), zero, zero,
                zero, zero, scale.z(), zero
        );
    }

    @Override
    default M zero() {
        final var zero = vectorOps().scalarOps().zero();
        return of(
                zero, zero, zero, zero,
                zero, zero, zero, zero,
                zero, zero, zero, zero
        );
    }

    @Override
    default M one() {
        final var one = vectorOps().scalarOps().one();
        return of(
                one, one, one, one,
                one, one, one, one,
                one, one, one, one
        );
    }

    @Override
    default M identity() {
        final var ops = vectorOps().scalarOps();
        final var one = ops.one();
        final var zero = ops.zero();
        return of(
                one, zero, zero, zero,
                zero, one, zero, zero,
                zero, zero, one, zero
        );
    }

    /// Transposes the 3x3 sub-matrix (linear part) of this affine matrix, ignoring the translation part.
    /// @return the transposed affine matrix.
    @Override
    default M transpose(M affine) {
        return of(
                affine.m00(), affine.m10(), affine.m20(), affine.m03(),
                affine.m01(), affine.m11(), affine.m21(), affine.m13(),
                affine.m02(), affine.m12(), affine.m22(), affine.m23()
        );
    }

    @Override
    default N determinant(M matrix) {
        final var ops = vectorOps().scalarOps();
        final N m00 = ops.multiply(matrix.m00(), ops.subtract(ops.multiply(matrix.m11(), matrix.m22()), ops.multiply(matrix.m12(), matrix.m21())));
        final N m01 = ops.multiply(matrix.m01(), ops.subtract(ops.multiply(matrix.m10(), matrix.m22()), ops.multiply(matrix.m12(), matrix.m20())));
        final N m02 = ops.multiply(matrix.m02(), ops.subtract(ops.multiply(matrix.m10(), matrix.m21()), ops.multiply(matrix.m11(), matrix.m20())));
        return ops.add(m00, ops.subtract(m01, m02));
    }

    @Override
    default M inverse(M matrix) throws ArithmeticException {

        final var ops = vectorOps().scalarOps();
        final N det = determinant(matrix);
        if (det.equals(ops.zero())) throw new ArithmeticException("The matrix cannot be inverted since singular.");

        final N invDet = ops.divide(ops.one(), det);

        final N n00 = ops.multiply(ops.subtract(ops.multiply(matrix.m11(), matrix.m22()), ops.multiply(matrix.m12(), matrix.m21())), invDet);
        final N n01 = ops.multiply(ops.subtract(ops.multiply(matrix.m02(), matrix.m21()), ops.multiply(matrix.m01(), matrix.m22())), invDet);
        final N n02 = ops.multiply(ops.subtract(ops.multiply(matrix.m01(), matrix.m12()), ops.multiply(matrix.m02(), matrix.m11())), invDet);

        final N n10 = ops.multiply(ops.subtract(ops.multiply(matrix.m12(), matrix.m20()), ops.multiply(matrix.m10(), matrix.m22())), invDet);
        final N n11 = ops.multiply(ops.subtract(ops.multiply(matrix.m00(), matrix.m22()), ops.multiply(matrix.m02(), matrix.m20())), invDet);
        final N n12 = ops.multiply(ops.subtract(ops.multiply(matrix.m02(), matrix.m10()), ops.multiply(matrix.m00(), matrix.m12())), invDet);

        final N n20 = ops.multiply(ops.subtract(ops.multiply(matrix.m10(), matrix.m21()), ops.multiply(matrix.m11(), matrix.m20())), invDet);
        final N n21 = ops.multiply(ops.subtract(ops.multiply(matrix.m01(), matrix.m20()), ops.multiply(matrix.m00(), matrix.m21())), invDet);
        final N n22 = ops.multiply(ops.subtract(ops.multiply(matrix.m00(), matrix.m11()), ops.multiply(matrix.m01(), matrix.m10())), invDet);

        final N n03 = ops.add(ops.multiply(n00, matrix.m03()), ops.add(ops.multiply(n01, matrix.m13()), ops.multiply(n02, matrix.m23())));
        final N n13 = ops.add(ops.multiply(n10, matrix.m03()), ops.add(ops.multiply(n11, matrix.m13()), ops.multiply(n12, matrix.m23())));
        final N n23 = ops.add(ops.multiply(n20, matrix.m03()), ops.add(ops.multiply(n21, matrix.m13()), ops.multiply(n22, matrix.m23())));

        return of(
                n00, n01, n02, ops.negated(n03),
                n10, n11, n12, ops.negated(n13),
                n20, n21, n22, ops.negated(n23)
        );
    }

    @Override
    default V multiply(M matrix, V vector) {

        final var ops = vectorOps().scalarOps();

        final N m00 = ops.multiply(matrix.m00(), vector.x());
        final N m01 = ops.multiply(matrix.m01(), vector.y());
        final N m02 = ops.multiply(matrix.m02(), vector.z());

        final N m10 = ops.multiply(matrix.m10(), vector.x());
        final N m11 = ops.multiply(matrix.m11(), vector.y());
        final N m12 = ops.multiply(matrix.m12(), vector.z());

        final N m20 = ops.multiply(matrix.m20(), vector.x());
        final N m21 = ops.multiply(matrix.m21(), vector.y());
        final N m22 = ops.multiply(matrix.m22(), vector.z());

        final var x = ops.add(ops.add(m00, m01), ops.add(m02, matrix.m03()));
        final var y = ops.add(ops.add(m10, m11), ops.add(m12, matrix.m13()));
        final var z = ops.add(ops.add(m20, m21), ops.add(m22, matrix.m23()));

        return vectorOps().of(x, y, z);
    }

    @Override
    default M multiply(M matrix, N scalar) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.multiply(matrix.m00(), scalar);
        final N m01 = ops.multiply(matrix.m01(), scalar);
        final N m02 = ops.multiply(matrix.m02(), scalar);
        final N m03 = ops.multiply(matrix.m03(), scalar);

        final N m10 = ops.multiply(matrix.m10(), scalar);
        final N m11 = ops.multiply(matrix.m11(), scalar);
        final N m12 = ops.multiply(matrix.m12(), scalar);
        final N m13 = ops.multiply(matrix.m13(), scalar);

        final N m20 = ops.multiply(matrix.m20(), scalar);
        final N m21 = ops.multiply(matrix.m21(), scalar);
        final N m22 = ops.multiply(matrix.m22(), scalar);
        final N m23 = ops.multiply(matrix.m23(), scalar);
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23
        );
    }

    @Override
    default M add(M op1, M op2) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.add(op1.m00(), op2.m00());
        final N m01 = ops.add(op1.m01(), op2.m01());
        final N m02 = ops.add(op1.m02(), op2.m02());
        final N m03 = ops.add(op1.m03(), op2.m03());

        final N m10 = ops.add(op1.m10(), op2.m10());
        final N m20 = ops.add(op1.m20(), op2.m20());
        final N m11 = ops.add(op1.m11(), op2.m11());
        final N m21 = ops.add(op1.m21(), op2.m21());

        final N m12 = ops.add(op1.m12(), op2.m12());
        final N m22 = ops.add(op1.m22(), op2.m22());
        final N m13 = ops.add(op1.m13(), op2.m13());
        final N m23 = ops.add(op1.m23(), op2.m23());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23
        );
    }

    @Override
    default M subtract(M op1, M op2) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.subtract(op1.m00(), op2.m00());
        final N m01 = ops.subtract(op1.m01(), op2.m01());
        final N m02 = ops.subtract(op1.m02(), op2.m02());
        final N m03 = ops.subtract(op1.m03(), op2.m03());

        final N m10 = ops.subtract(op1.m10(), op2.m10());
        final N m11 = ops.subtract(op1.m11(), op2.m11());
        final N m12 = ops.subtract(op1.m12(), op2.m12());
        final N m13 = ops.subtract(op1.m13(), op2.m13());

        final N m20 = ops.subtract(op1.m20(), op2.m20());
        final N m21 = ops.subtract(op1.m21(), op2.m21());
        final N m22 = ops.subtract(op1.m22(), op2.m22());
        final N m23 = ops.subtract(op1.m23(), op2.m23());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23
        );
    }

    @Override
    default M multiply(M op1, M op2) {
        final var ops = vectorOps().scalarOps();
        final N n00 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m00()), ops.multiply(op1.m01(), op2.m10())), ops.multiply(op1.m02(), op2.m20()));
        final N n01 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m01()), ops.multiply(op1.m01(), op2.m11())), ops.multiply(op1.m02(), op2.m21()));
        final N n02 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m02()), ops.multiply(op1.m01(), op2.m12())), ops.multiply(op1.m02(), op2.m22()));
        final N n03 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m03()), ops.multiply(op1.m01(), op2.m13())), ops.multiply(op1.m02(), op2.m23()));
        final N n10 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m00()), ops.multiply(op1.m11(), op2.m10())), ops.multiply(op1.m12(), op2.m20()));
        final N n11 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m01()), ops.multiply(op1.m11(), op2.m11())), ops.multiply(op1.m12(), op2.m21()));
        final N n12 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m02()), ops.multiply(op1.m11(), op2.m12())), ops.multiply(op1.m12(), op2.m22()));
        final N n13 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m03()), ops.multiply(op1.m11(), op2.m13())), ops.multiply(op1.m12(), op2.m23()));
        final N n20 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m00()), ops.multiply(op1.m21(), op2.m10())), ops.multiply(op1.m22(), op2.m20()));
        final N n21 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m01()), ops.multiply(op1.m21(), op2.m11())), ops.multiply(op1.m22(), op2.m21()));
        final N n22 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m02()), ops.multiply(op1.m21(), op2.m12())), ops.multiply(op1.m22(), op2.m22()));
        final N n23 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m03()), ops.multiply(op1.m21(), op2.m13())), ops.multiply(op1.m22(), op2.m23()));
        return of(
                n00, n01, n02, ops.add(n03, op1.m03()),
                n10, n11, n12, ops.add(n13, op1.m13()),
                n20, n21, n22, ops.add(n23, op1.m23())
        );
    }

    @Override
    default M remainder(M op1, M op2) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.remainder(op1.m00(), op2.m00());
        final N m01 = ops.remainder(op1.m01(), op2.m01());
        final N m02 = ops.remainder(op1.m02(), op2.m02());
        final N m03 = ops.remainder(op1.m03(), op2.m03());

        final N m10 = ops.remainder(op1.m10(), op2.m10());
        final N m11 = ops.remainder(op1.m11(), op2.m11());
        final N m12 = ops.remainder(op1.m12(), op2.m12());
        final N m13 = ops.remainder(op1.m13(), op2.m13());

        final N m20 = ops.remainder(op1.m20(), op2.m20());
        final N m21 = ops.remainder(op1.m21(), op2.m21());
        final N m22 = ops.remainder(op1.m22(), op2.m22());
        final N m23 = ops.remainder(op1.m23(), op2.m23());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23
        );
    }

    @Override
    default M negated(M operand) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.negated(operand.m00());
        final N m01 = ops.negated(operand.m01());
        final N m02 = ops.negated(operand.m02());
        final N m03 = ops.negated(operand.m03());

        final N m10 = ops.negated(operand.m10());
        final N m11 = ops.negated(operand.m11());
        final N m12 = ops.negated(operand.m12());
        final N m13 = ops.negated(operand.m13());

        final N m20 = ops.negated(operand.m20());
        final N m21 = ops.negated(operand.m21());
        final N m22 = ops.negated(operand.m22());
        final N m23 = ops.negated(operand.m23());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23
        );
    }

    @Override
    Vector3Ops<V, N> vectorOps();
}
