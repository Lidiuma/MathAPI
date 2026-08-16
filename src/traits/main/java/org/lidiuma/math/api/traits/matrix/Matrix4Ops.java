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

import org.lidiuma.math.api.matrix.Matrix4;
import org.lidiuma.math.api.vector.Vector4;
import org.lidiuma.math.api.traits.vector.Vector4Ops;

public interface Matrix4Ops<
        M extends Matrix4<N>,
        V extends Vector4<N>,
        N> extends SquareMatrixOps<M, V, N> {

    M of(
            N m00, N m01, N m02, N m03,
            N m10, N m11, N m12, N m13,
            N m20, N m21, N m22, N m23,
            N m30, N m31, N m32, N m33
    );

    /// Constructs the matrix from four basis vectors, treating them as the columns of the matrix.
    /// @param x The first column of the matrix.
    /// @param y The second column of the matrix.
    /// @param z The third column of the matrix.
    /// @param w The fourth column of the matrix.
    /// @return A new matrix with the given vectors as its columns.
    default M fromBasis(V x, V y, V z, V w) {
        return of(
                x.x(), x.y(), x.z(), x.w(),
                y.x(), y.y(), y.z(), y.w(),
                z.x(), z.y(), z.z(), z.w(),
                w.x(), w.y(), w.z(), w.w()
        );
    }

    @Override
    default M zero() {
        final var zero = vectorOps().scalarOps().zero();
        return of(
                zero, zero, zero, zero,
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
                one, one, one, one,
                one, one, one, one
        );
    }

    @Override
    default M identity() {
        final var ops = vectorOps().scalarOps();
        final var zero = ops.zero();
        final var one = ops.one();
        return of(
                 one, zero, zero, zero,
                zero,  one, zero, zero,
                zero, zero,  one, zero,
                zero, zero, zero,  one
        );
    }

    @Override
    default N determinant(M matrix) {

        final var ops = vectorOps().scalarOps();

        // 2×2 minors
        final N det22_23 = ops.subtract(ops.multiply(matrix.m22(), matrix.m33()), ops.multiply(matrix.m23(), matrix.m32()));
        final N det21_23 = ops.subtract(ops.multiply(matrix.m21(), matrix.m33()), ops.multiply(matrix.m23(), matrix.m31()));
        final N det21_22 = ops.subtract(ops.multiply(matrix.m21(), matrix.m32()), ops.multiply(matrix.m22(), matrix.m31()));
        final N det20_23 = ops.subtract(ops.multiply(matrix.m20(), matrix.m33()), ops.multiply(matrix.m23(), matrix.m30()));
        final N det20_22 = ops.subtract(ops.multiply(matrix.m20(), matrix.m32()), ops.multiply(matrix.m22(), matrix.m30()));
        final N det20_21 = ops.subtract(ops.multiply(matrix.m20(), matrix.m31()), ops.multiply(matrix.m21(), matrix.m30()));

        // 3×3 minors
        final N minor00 = ops.add(ops.subtract(ops.multiply(matrix.m11(), det22_23), ops.multiply(matrix.m12(), det21_23)), ops.multiply(matrix.m13(), det21_22));
        final N minor01 = ops.add(ops.subtract(ops.multiply(matrix.m10(), det22_23), ops.multiply(matrix.m12(), det20_23)), ops.multiply(matrix.m13(), det20_22));
        final N minor02 = ops.add(ops.subtract(ops.multiply(matrix.m10(), det21_23), ops.multiply(matrix.m11(), det20_23)), ops.multiply(matrix.m13(), det20_21));
        final N minor03 = ops.add(ops.subtract(ops.multiply(matrix.m10(), det21_22), ops.multiply(matrix.m11(), det20_22)), ops.multiply(matrix.m12(), det20_21));

        // Cofactor expansion (+m00 * minor00 - m01 * minor01 + m02 * minor02 - m03 * minor03)
        return ops.subtract(
                ops.add(ops.subtract(ops.multiply(matrix.m00(), minor00), ops.multiply(matrix.m01(), minor01)), ops.multiply(matrix.m02(), minor02)),
                ops.multiply(matrix.m03(), minor03)
        );
    }

    @Override
    default M inverse(M m) throws ArithmeticException {

        final var ops = vectorOps().scalarOps();

        final N det = determinant(m);
        if (det.equals(ops.zero())) throw new ArithmeticException("The matrix cannot be inverted since singular.");
        final N invDet = ops.divide(ops.one(), det);

        final N n00 = invAdd(mul3(m.m12(), m.m23(), m.m31()), mul3(m.m13(), m.m22(), m.m31()), mul3(m.m13(), m.m21(), m.m32()), mul3(m.m11(), m.m23(), m.m32()), mul3(m.m12(), m.m21(), m.m33()), mul3(m.m11(), m.m22(), m.m33()));
        final N n01 = invSub(mul3(m.m03(), m.m22(), m.m31()), mul3(m.m02(), m.m23(), m.m31()), mul3(m.m03(), m.m21(), m.m32()), mul3(m.m01(), m.m23(), m.m32()), mul3(m.m02(), m.m21(), m.m33()), mul3(m.m01(), m.m22(), m.m33()));
        final N n02 = invAdd(mul3(m.m02(), m.m13(), m.m31()), mul3(m.m03(), m.m12(), m.m31()), mul3(m.m03(), m.m11(), m.m32()), mul3(m.m01(), m.m13(), m.m32()), mul3(m.m02(), m.m11(), m.m33()), mul3(m.m01(), m.m12(), m.m33()));
        final N n03 = invSub(mul3(m.m03(), m.m12(), m.m21()), mul3(m.m02(), m.m13(), m.m21()), mul3(m.m03(), m.m11(), m.m22()), mul3(m.m01(), m.m13(), m.m22()), mul3(m.m02(), m.m11(), m.m23()), mul3(m.m01(), m.m12(), m.m23()));
        final N n10 = invSub(mul3(m.m13(), m.m22(), m.m30()), mul3(m.m12(), m.m23(), m.m30()), mul3(m.m13(), m.m20(), m.m32()), mul3(m.m10(), m.m23(), m.m32()), mul3(m.m12(), m.m20(), m.m33()), mul3(m.m10(), m.m22(), m.m33()));
        final N n11 = invAdd(mul3(m.m02(), m.m23(), m.m30()), mul3(m.m03(), m.m22(), m.m30()), mul3(m.m03(), m.m20(), m.m32()), mul3(m.m00(), m.m23(), m.m32()), mul3(m.m02(), m.m20(), m.m33()), mul3(m.m00(), m.m22(), m.m33()));
        final N n12 = invSub(mul3(m.m03(), m.m12(), m.m30()), mul3(m.m02(), m.m13(), m.m30()), mul3(m.m03(), m.m10(), m.m32()), mul3(m.m00(), m.m13(), m.m32()), mul3(m.m02(), m.m10(), m.m33()), mul3(m.m00(), m.m12(), m.m33()));
        final N n13 = invAdd(mul3(m.m02(), m.m13(), m.m20()), mul3(m.m03(), m.m12(), m.m20()), mul3(m.m03(), m.m10(), m.m22()), mul3(m.m00(), m.m13(), m.m22()), mul3(m.m02(), m.m10(), m.m23()), mul3(m.m00(), m.m12(), m.m23()));
        final N n20 = invAdd(mul3(m.m11(), m.m23(), m.m30()), mul3(m.m13(), m.m21(), m.m30()), mul3(m.m13(), m.m20(), m.m31()), mul3(m.m10(), m.m23(), m.m31()), mul3(m.m11(), m.m20(), m.m33()), mul3(m.m10(), m.m21(), m.m33()));
        final N n21 = invSub(mul3(m.m03(), m.m21(), m.m30()), mul3(m.m01(), m.m23(), m.m30()), mul3(m.m03(), m.m20(), m.m31()), mul3(m.m00(), m.m23(), m.m31()), mul3(m.m01(), m.m20(), m.m33()), mul3(m.m00(), m.m21(), m.m33()));
        final N n22 = invAdd(mul3(m.m01(), m.m13(), m.m30()), mul3(m.m03(), m.m11(), m.m30()), mul3(m.m03(), m.m10(), m.m31()), mul3(m.m00(), m.m13(), m.m31()), mul3(m.m01(), m.m10(), m.m33()), mul3(m.m00(), m.m11(), m.m33()));
        final N n23 = invSub(mul3(m.m03(), m.m11(), m.m20()), mul3(m.m01(), m.m13(), m.m20()), mul3(m.m03(), m.m10(), m.m21()), mul3(m.m00(), m.m13(), m.m21()), mul3(m.m01(), m.m10(), m.m23()), mul3(m.m00(), m.m11(), m.m23()));
        final N n30 = invSub(mul3(m.m12(), m.m21(), m.m30()), mul3(m.m11(), m.m22(), m.m30()), mul3(m.m12(), m.m20(), m.m31()), mul3(m.m10(), m.m22(), m.m31()), mul3(m.m11(), m.m20(), m.m32()), mul3(m.m10(), m.m21(), m.m32()));
        final N n31 = invAdd(mul3(m.m01(), m.m22(), m.m30()), mul3(m.m02(), m.m21(), m.m30()), mul3(m.m02(), m.m20(), m.m31()), mul3(m.m00(), m.m22(), m.m31()), mul3(m.m01(), m.m20(), m.m32()), mul3(m.m00(), m.m21(), m.m32()));
        final N n32 = invSub(mul3(m.m02(), m.m11(), m.m30()), mul3(m.m01(), m.m12(), m.m30()), mul3(m.m02(), m.m10(), m.m31()), mul3(m.m00(), m.m12(), m.m31()), mul3(m.m01(), m.m10(), m.m32()), mul3(m.m00(), m.m11(), m.m32()));
        final N n33 = invAdd(mul3(m.m01(), m.m12(), m.m20()), mul3(m.m02(), m.m11(), m.m20()), mul3(m.m02(), m.m10(), m.m21()), mul3(m.m00(), m.m12(), m.m21()), mul3(m.m01(), m.m10(), m.m22()), mul3(m.m00(), m.m11(), m.m22()));
        return multiply(of(
                n00, n01, n02, n03,
                n10, n11, n12, n13,
                n20, n21, n22, n23,
                n30, n31, n32, n33
        ), invDet);
    }

    /// Helper method for the inverse, it returns: `n1 - n2 + n3 - n4 - n5 + n6`.
    private N invAdd(N n1, N n2, N n3, N n4, N n5, N n6) {
        final var ops = vectorOps().scalarOps();
        final N f1 = ops.subtract(n1, n2);
        final N f2 = ops.add(f1, n3);
        final N f3 = ops.subtract(f2, n4);
        final N f4 = ops.subtract(f3, n5);
        return ops.add(f4, n6);
    }

    /// Helper method for the inverse, it returns: `n1 - n2 - n3 + n4 + n5 - n6`.
    private N invSub(N n1, N n2, N n3, N n4, N n5, N n6) {
        final var ops = vectorOps().scalarOps();
        final N f1 = ops.subtract(n1, n2);
        final N f2 = ops.subtract(f1, n3);
        final N f3 = ops.add(f2, n4);
        final N f4 = ops.add(f3, n5);
        return ops.subtract(f4, n6);
    }

    /// Helper that multiplicative 3 numbers together.
    private N mul3(N n1, N n2, N n3) {
        final var ops = vectorOps().scalarOps();
        return ops.multiply(ops.multiply(n1, n2), n3);
    }

    @Override
    default M transpose(M matrix) {
        return of(
                matrix.m00(), matrix.m10(), matrix.m20(), matrix.m30(),
                matrix.m01(), matrix.m11(), matrix.m21(), matrix.m31(),
                matrix.m02(), matrix.m12(), matrix.m22(), matrix.m32(),
                matrix.m03(), matrix.m13(), matrix.m23(), matrix.m33()
        );
    }

    @Override
    default V multiply(M matrix, V vector) {

        final var ops = vectorOps().scalarOps();

        final N m00 = ops.multiply(matrix.m00(), vector.x());
        final N m01 = ops.multiply(matrix.m01(), vector.y());
        final N m02 = ops.multiply(matrix.m02(), vector.z());
        final N m03 = ops.multiply(matrix.m03(), vector.w());

        final N m10 = ops.multiply(matrix.m10(), vector.x());
        final N m11 = ops.multiply(matrix.m11(), vector.y());
        final N m12 = ops.multiply(matrix.m12(), vector.z());
        final N m13 = ops.multiply(matrix.m13(), vector.w());

        final N m20 = ops.multiply(matrix.m20(), vector.x());
        final N m21 = ops.multiply(matrix.m21(), vector.y());
        final N m22 = ops.multiply(matrix.m22(), vector.z());
        final N m23 = ops.multiply(matrix.m23(), vector.w());

        final N m30 = ops.multiply(matrix.m30(), vector.x());
        final N m31 = ops.multiply(matrix.m31(), vector.y());
        final N m32 = ops.multiply(matrix.m32(), vector.z());
        final N m33 = ops.multiply(matrix.m33(), vector.w());

        final N x = ops.add(ops.add(m00, m01), ops.add(m02, m03));
        final N y = ops.add(ops.add(m10, m11), ops.add(m12, m13));
        final N z = ops.add(ops.add(m20, m21), ops.add(m22, m23));
        final N w = ops.add(ops.add(m30, m31), ops.add(m32, m33));
        return vectorOps().of(x, y, z, w);
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

        final N m30 = ops.multiply(matrix.m30(), scalar);
        final N m31 = ops.multiply(matrix.m31(), scalar);
        final N m32 = ops.multiply(matrix.m32(), scalar);
        final N m33 = ops.multiply(matrix.m33(), scalar);
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
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
        final N m11 = ops.add(op1.m11(), op2.m11());
        final N m12 = ops.add(op1.m12(), op2.m12());
        final N m13 = ops.add(op1.m13(), op2.m13());

        final N m20 = ops.add(op1.m20(), op2.m20());
        final N m21 = ops.add(op1.m21(), op2.m21());
        final N m22 = ops.add(op1.m22(), op2.m22());
        final N m23 = ops.add(op1.m23(), op2.m23());

        final N m30 = ops.add(op1.m30(), op2.m30());
        final N m31 = ops.add(op1.m31(), op2.m31());
        final N m32 = ops.add(op1.m32(), op2.m32());
        final N m33 = ops.add(op1.m33(), op2.m33());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
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

        final N m30 = ops.subtract(op1.m30(), op2.m30());
        final N m31 = ops.subtract(op1.m31(), op2.m31());
        final N m32 = ops.subtract(op1.m32(), op2.m32());
        final N m33 = ops.subtract(op1.m33(), op2.m33());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        );
    }

    @Override
    default M multiply(M op1, M op2) {

        final var ops = vectorOps().scalarOps();

        final N m00 = ops.add(mulAdd(op1.m00(), op2.m00(), op1.m01(), op2.m10()), mulAdd(op1.m02(), op2.m20(), op1.m03(), op2.m30()));
        final N m01 = ops.add(mulAdd(op1.m00(), op2.m01(), op1.m01(), op2.m11()), mulAdd(op1.m02(), op2.m21(), op1.m03(), op2.m31()));
        final N m02 = ops.add(mulAdd(op1.m00(), op2.m02(), op1.m01(), op2.m12()), mulAdd(op1.m02(), op2.m22(), op1.m03(), op2.m32()));
        final N m03 = ops.add(mulAdd(op1.m00(), op2.m03(), op1.m01(), op2.m13()), mulAdd(op1.m02(), op2.m23(), op1.m03(), op2.m33()));
        final N m10 = ops.add(mulAdd(op1.m10(), op2.m00(), op1.m11(), op2.m10()), mulAdd(op1.m12(), op2.m20(), op1.m13(), op2.m30()));
        final N m11 = ops.add(mulAdd(op1.m10(), op2.m01(), op1.m11(), op2.m11()), mulAdd(op1.m12(), op2.m21(), op1.m13(), op2.m31()));
        final N m12 = ops.add(mulAdd(op1.m10(), op2.m02(), op1.m11(), op2.m12()), mulAdd(op1.m12(), op2.m22(), op1.m13(), op2.m32()));
        final N m13 = ops.add(mulAdd(op1.m10(), op2.m03(), op1.m11(), op2.m13()), mulAdd(op1.m12(), op2.m23(), op1.m13(), op2.m33()));
        final N m20 = ops.add(mulAdd(op1.m20(), op2.m00(), op1.m21(), op2.m10()), mulAdd(op1.m22(), op2.m20(), op1.m23(), op2.m30()));
        final N m21 = ops.add(mulAdd(op1.m20(), op2.m01(), op1.m21(), op2.m11()), mulAdd(op1.m22(), op2.m21(), op1.m23(), op2.m31()));
        final N m22 = ops.add(mulAdd(op1.m20(), op2.m02(), op1.m21(), op2.m12()), mulAdd(op1.m22(), op2.m22(), op1.m23(), op2.m32()));
        final N m23 = ops.add(mulAdd(op1.m20(), op2.m03(), op1.m21(), op2.m13()), mulAdd(op1.m22(), op2.m23(), op1.m23(), op2.m33()));
        final N m30 = ops.add(mulAdd(op1.m30(), op2.m00(), op1.m31(), op2.m10()), mulAdd(op1.m32(), op2.m20(), op1.m33(), op2.m30()));
        final N m31 = ops.add(mulAdd(op1.m30(), op2.m01(), op1.m31(), op2.m11()), mulAdd(op1.m32(), op2.m21(), op1.m33(), op2.m31()));
        final N m32 = ops.add(mulAdd(op1.m30(), op2.m02(), op1.m31(), op2.m12()), mulAdd(op1.m32(), op2.m22(), op1.m33(), op2.m32()));
        final N m33 = ops.add(mulAdd(op1.m30(), op2.m03(), op1.m31(), op2.m13()), mulAdd(op1.m32(), op2.m23(), op1.m33(), op2.m33()));
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        );
    }

    /// Helper for the matrix multiplication, it returns: `N1 * N2 + N3 * N4`
    private N mulAdd(N n1, N n2, N n3, N n4) {
        final var ops = vectorOps().scalarOps();
        return ops.add(ops.multiply(n1, n2), ops.multiply(n3, n4));
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

        final N m30 = ops.remainder(op1.m30(), op2.m30());
        final N m31 = ops.remainder(op1.m31(), op2.m31());
        final N m32 = ops.remainder(op1.m32(), op2.m32());
        final N m33 = ops.remainder(op1.m33(), op2.m33());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
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

        final N m30 = ops.negated(operand.m30());
        final N m31 = ops.negated(operand.m31());
        final N m32 = ops.negated(operand.m32());
        final N m33 = ops.negated(operand.m33());
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        );
    }

    @Override
    Vector4Ops<V, N> vectorOps();
}
