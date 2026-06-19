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

package org.lidiuma.math.api.matrix;

import org.lidiuma.math.api.FloatingNumerical;
import org.lidiuma.math.api.vector.Vector2;
import java.util.function.UnaryOperator;

public interface Affine2Ops<
        A extends Affine2<N>,
        V extends Vector2<N>,
        N> extends SquareMatrixOps<A, V, N> {

    A of(N m00, N m01, N m02,
         N m10, N m11, N m12);

    @Override
    FloatingNumerical<N> scalarOps();

    @Override
    default A identity() {
        final var witness = scalarOps();
        final var one = witness.one();
        final var zero = witness.zero();
        return of(
                one, zero, zero,
                zero, one, zero
        );
    }

    /// Transposes the 2x2 sub-matrix (linear part) of this affine matrix, ignoring the translation part.
    /// @return the transposed affine matrix.
    @Override
    default A transpose(A affine) {
        return of(
                affine.m00(), affine.m10(), affine.m02(),
                affine.m01(), affine.m11(), affine.m12()
        );
    }

    @Override
    default N determinant(A matrix) {
        final var witness = scalarOps();
        final N m0011 = witness.multiply(matrix.m00(), matrix.m11());
        final N m0110 = witness.multiply(matrix.m01(), matrix.m10());
        return witness.subtract(m0011, m0110);
    }

    @Override
    default A inverse(A matrix) throws ArithmeticException {

        final var ops = scalarOps();
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
    default boolean isSingular(A matrix) {
        return determinant(matrix).equals(scalarOps().zero());
    }

    @Override
    default A interpolate(A start, A end, N alpha, UnaryOperator<N> easing) {

        final var ops = scalarOps();
        final N eased = easing.apply(alpha);

        final N m00 = interpolate(start.m00(), end.m00(), eased, ops);
        final N m01 = interpolate(start.m01(), end.m01(), eased, ops);

        final N m10 = interpolate(start.m10(), end.m10(), eased, ops);
        final N m11 = interpolate(start.m11(), end.m11(), eased, ops);

        final N m02  = interpolate(start.m02(), end.m02(),  eased, ops);
        final N m12  = interpolate(start.m12(), end.m12(),  eased, ops);

        return of(
                m00, m01, m02,
                m10, m11, m12
        );
    }

    // Helper method for the interpolation.
    private N interpolate(N start, N end, N eased, FloatingNumerical<N> ops) {

        final N invAlpha = ops.subtract(ops.one(), eased);

        final N invStart = ops.multiply(start, invAlpha);
        final N invEnd = ops.multiply(end, eased);

        return ops.add(invStart, invEnd);
    }

    @Override
    default A multiply(A matrix, N scalar) {
        final var ops = scalarOps();
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
    default A add(A op1, A op2) {
        final var ops = scalarOps();
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
    default A subtract(A op1, A op2) {
        final var ops = scalarOps();
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
    default A multiply(A op1, A op2) {
        final var ops = scalarOps();
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
    default A remainder(A op1, A op2) {
        final var ops = scalarOps();
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
    default A negated(A operand) {
        final var ops = scalarOps();
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
}
