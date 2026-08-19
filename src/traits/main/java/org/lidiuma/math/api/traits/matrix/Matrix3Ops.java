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

import org.lidiuma.math.api.matrix.Matrix3;
import org.lidiuma.math.api.vector.Vector3;
import org.lidiuma.math.api.traits.vector.Vector3Ops;

/// Operations for [Matrix3].
public interface Matrix3Ops<
        M extends Matrix3<N>,
        V extends Vector3<N>,
        N> extends SquareMatrixOps<M, V, N> {

    M of(N m00, N m01, N m02,
         N m10, N m11, N m12,
         N m20, N m21, N m22);

    /// Constructs the matrix from four basis vectors, treating them as the columns of the matrix.
    /// @param x The first column of the matrix.
    /// @param y The second column of the matrix.
    /// @param z The third column of the matrix.
    /// @return A new matrix with the given vectors as its columns.
    default M fromBasis(V x, V y, V z) {
        return of(
                x.x(), x.y(), x.z(),
                y.x(), y.y(), y.z(),
                z.x(), z.y(), z.z()
        );
    }

    @Override
    default M zero() {
        final var zero = vectorOps().scalarOps().zero();
        return of(
                zero, zero, zero,
                zero, zero, zero,
                zero, zero, zero
        );
    }

    @Override
    default M one() {
        final var one = vectorOps().scalarOps().one();
        return of(
                one, one, one,
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
                zero, one, zero,
                zero, zero, one
        );
    }

    /// Transposes the 3x3 matrix.
    /// @return the transposed matrix.
    @Override
    default M transpose(M matrix) {
        return of(
                matrix.m00(), matrix.m10(), matrix.m20(),
                matrix.m01(), matrix.m11(), matrix.m21(),
                matrix.m02(), matrix.m12(), matrix.m22()
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

        return of(
                n00, n01, n02,
                n10, n11, n12,
                n20, n21, n22
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

        final N x = ops.add(ops.add(m00, m01), m02);
        final N y = ops.add(ops.add(m10, m11), m12);
        final N z = ops.add(ops.add(m20, m21), m22);
        return vectorOps().of(x, y, z);
    }

    @Override
    default M multiply(M matrix, N scalar) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.multiply(matrix.m00(), scalar);
        final N m01 = ops.multiply(matrix.m01(), scalar);
        final N m02 = ops.multiply(matrix.m02(), scalar);

        final N m10 = ops.multiply(matrix.m10(), scalar);
        final N m11 = ops.multiply(matrix.m11(), scalar);
        final N m12 = ops.multiply(matrix.m12(), scalar);

        final N m20 = ops.multiply(matrix.m20(), scalar);
        final N m21 = ops.multiply(matrix.m21(), scalar);
        final N m22 = ops.multiply(matrix.m22(), scalar);
        return of(
                m00, m01, m02,
                m10, m11, m12,
                m20, m21, m22
        );
    }

    @Override
    default M add(M op1, M op2) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.add(op1.m00(), op2.m00());
        final N m01 = ops.add(op1.m01(), op2.m01());
        final N m02 = ops.add(op1.m02(), op2.m02());

        final N m10 = ops.add(op1.m10(), op2.m10());
        final N m11 = ops.add(op1.m11(), op2.m11());
        final N m12 = ops.add(op1.m12(), op2.m12());

        final N m20 = ops.add(op1.m20(), op2.m20());
        final N m21 = ops.add(op1.m21(), op2.m21());
        final N m22 = ops.add(op1.m22(), op2.m22());

        return of(
                m00, m01, m02,
                m10, m11, m12,
                m20, m21, m22
        );
    }

    @Override
    default M subtract(M op1, M op2) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.subtract(op1.m00(), op2.m00());
        final N m01 = ops.subtract(op1.m01(), op2.m01());
        final N m02 = ops.subtract(op1.m02(), op2.m02());

        final N m10 = ops.subtract(op1.m10(), op2.m10());
        final N m11 = ops.subtract(op1.m11(), op2.m11());
        final N m12 = ops.subtract(op1.m12(), op2.m12());

        final N m20 = ops.subtract(op1.m20(), op2.m20());
        final N m21 = ops.subtract(op1.m21(), op2.m21());
        final N m22 = ops.subtract(op1.m22(), op2.m22());
        return of(
                m00, m01, m02,
                m10, m11, m12,
                m20, m21, m22
        );
    }

    @Override
    default M multiply(M op1, M op2) {
        final var ops = vectorOps().scalarOps();
        final N n00 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m00()), ops.multiply(op1.m01(), op2.m10())), ops.multiply(op1.m02(), op2.m20()));
        final N n01 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m01()), ops.multiply(op1.m01(), op2.m11())), ops.multiply(op1.m02(), op2.m21()));
        final N n02 = ops.add(ops.add(ops.multiply(op1.m00(), op2.m02()), ops.multiply(op1.m01(), op2.m12())), ops.multiply(op1.m02(), op2.m22()));
        final N n10 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m00()), ops.multiply(op1.m11(), op2.m10())), ops.multiply(op1.m12(), op2.m20()));
        final N n11 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m01()), ops.multiply(op1.m11(), op2.m11())), ops.multiply(op1.m12(), op2.m21()));
        final N n12 = ops.add(ops.add(ops.multiply(op1.m10(), op2.m02()), ops.multiply(op1.m11(), op2.m12())), ops.multiply(op1.m12(), op2.m22()));
        final N n20 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m00()), ops.multiply(op1.m21(), op2.m10())), ops.multiply(op1.m22(), op2.m20()));
        final N n21 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m01()), ops.multiply(op1.m21(), op2.m11())), ops.multiply(op1.m22(), op2.m21()));
        final N n22 = ops.add(ops.add(ops.multiply(op1.m20(), op2.m02()), ops.multiply(op1.m21(), op2.m12())), ops.multiply(op1.m22(), op2.m22()));
        return of(
                n00, n01, n02,
                n10, n11, n12,
                n20, n21, n22
        );
    }

    @Override
    default M remainder(M op1, M op2) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.remainder(op1.m00(), op2.m00());
        final N m01 = ops.remainder(op1.m01(), op2.m01());
        final N m02 = ops.remainder(op1.m02(), op2.m02());

        final N m10 = ops.remainder(op1.m10(), op2.m10());
        final N m11 = ops.remainder(op1.m11(), op2.m11());
        final N m12 = ops.remainder(op1.m12(), op2.m12());

        final N m20 = ops.remainder(op1.m20(), op2.m20());
        final N m21 = ops.remainder(op1.m21(), op2.m21());
        final N m22 = ops.remainder(op1.m22(), op2.m22());
        return of(
                m00, m01, m02,
                m10, m11, m12,
                m20, m21, m22
        );
    }

    @Override
    default M negated(M operand) {

        final var ops = vectorOps().scalarOps();
        final N m00 = ops.negated(operand.m00());
        final N m01 = ops.negated(operand.m01());
        final N m02 = ops.negated(operand.m02());

        final N m10 = ops.negated(operand.m10());
        final N m11 = ops.negated(operand.m11());
        final N m12 = ops.negated(operand.m12());

        final N m20 = ops.negated(operand.m20());
        final N m21 = ops.negated(operand.m21());
        final N m22 = ops.negated(operand.m22());
        return of(
                m00, m01, m02,
                m10, m11, m12,
                m20, m21, m22
        );
    }

    @Override
    Vector3Ops<V, N> vectorOps();
}
