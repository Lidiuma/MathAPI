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

import org.lidiuma.math.api.vector.Vector3;
import org.lidiuma.math.api.vector.Vector4;

public interface Matrix4Ops<
        M extends Matrix4<N>,
        V extends Vector4<N>,
        N> extends SquareMatrixOps<M, V, N> {

    // Annoying dependency, but this really makes my life easier to implement Matrix4.
    <M3 extends Matrix3<N>, V3 extends Vector3<N>> Matrix3Ops<M3, V3, N> matrix3Ops();

    M of(
            N m00, N m01, N m02, N m03,
            N m10, N m11, N m12, N m13,
            N m20, N m21, N m22, N m23,
            N m30, N m31, N m32, N m33
    );

    @Override
    default M zero() {
        final var ops = scalarOps();
        return of(
                ops.zero(), ops.zero(), ops.zero(), ops.zero(),
                ops.zero(), ops.zero(), ops.zero(), ops.zero(),
                ops.zero(), ops.zero(), ops.zero(), ops.zero(),
                ops.zero(), ops.zero(), ops.zero(), ops.zero()
        );
    }

    @Override
    default M one() {
        return identity();
    }

    @Override
    default M identity() {
        final var ops = scalarOps();
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

        final var ops = scalarOps();
        final var m3Ops = matrix3Ops();

        final N d00 = m3Ops.determinant(m3Ops.of(
                matrix.m11(), matrix.m12(), matrix.m13(),
                matrix.m21(), matrix.m22(), matrix.m23(),
                matrix.m31(), matrix.m32(), matrix.m33()
        ));
        final N d01 = m3Ops.determinant(m3Ops.of(
                matrix.m10(), matrix.m12(), matrix.m13(),
                matrix.m20(), matrix.m22(), matrix.m23(),
                matrix.m30(), matrix.m32(), matrix.m33()
        ));
        final N d02 = m3Ops.determinant(m3Ops.of(
                matrix.m10(), matrix.m11(), matrix.m13(),
                matrix.m20(), matrix.m21(), matrix.m23(),
                matrix.m30(), matrix.m31(), matrix.m33()
        ));
        final N d03 = m3Ops.determinant(m3Ops.of(
                matrix.m10(), matrix.m11(), matrix.m12(),
                matrix.m20(), matrix.m21(), matrix.m22(),
                matrix.m30(), matrix.m31(), matrix.m32()
        ));
        final N f0 = ops.multiply(matrix.m00(), d00);
        final N f1 = ops.multiply(matrix.m01(), d01);
        final N f2 = ops.multiply(matrix.m02(), d02);
        final N f3 = ops.multiply(matrix.m03(), d03);
        return ops.subtract(ops.add(ops.subtract(f0, f1), f2), f3);
    }

    @Override
    default M inverse(M matrix) throws ArithmeticException {

        final var ops = scalarOps();
        final var m3Ops = matrix3Ops();

        final N det = determinant(matrix);
        if (det.equals(ops.zero())) throw new ArithmeticException("The matrix cannot be inverted since singular.");
        final N invDet = ops.divide(ops.one(), det);

        final N d00 = m3Ops.determinant(m3Ops.of(
                matrix.m11(), matrix.m12(), matrix.m13(),
                matrix.m21(), matrix.m22(), matrix.m23(),
                matrix.m31(), matrix.m32(), matrix.m33()
        ));
        final N d01 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m10(), matrix.m12(), matrix.m13(),
                matrix.m20(), matrix.m22(), matrix.m23(),
                matrix.m30(), matrix.m32(), matrix.m33()
        )));
        final N d02 = m3Ops.determinant(m3Ops.of(
                matrix.m10(), matrix.m11(), matrix.m13(),
                matrix.m20(), matrix.m21(), matrix.m23(),
                matrix.m30(), matrix.m31(), matrix.m33()
        ));
        final N d03 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m10(), matrix.m11(), matrix.m12(),
                matrix.m20(), matrix.m21(), matrix.m22(),
                matrix.m30(), matrix.m31(), matrix.m32()
        )));
        final N d10 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m01(), matrix.m02(), matrix.m03(),
                matrix.m21(), matrix.m22(), matrix.m23(),
                matrix.m31(), matrix.m32(), matrix.m33()
        )));
        final N d11 = m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m02(), matrix.m03(),
                matrix.m20(), matrix.m22(), matrix.m23(),
                matrix.m30(), matrix.m32(), matrix.m33()
        ));
        final N d12 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m01(), matrix.m03(),
                matrix.m20(), matrix.m21(), matrix.m23(),
                matrix.m30(), matrix.m31(), matrix.m33()
        )));
        final N d13 = m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m01(), matrix.m02(),
                matrix.m20(), matrix.m21(), matrix.m22(),
                matrix.m30(), matrix.m31(), matrix.m32()
        ));
        final N d20 = m3Ops.determinant(m3Ops.of(
                matrix.m01(), matrix.m02(), matrix.m03(),
                matrix.m11(), matrix.m12(), matrix.m13(),
                matrix.m31(), matrix.m32(), matrix.m33()
        ));
        final N d21 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m02(), matrix.m03(),
                matrix.m10(), matrix.m12(), matrix.m13(),
                matrix.m30(), matrix.m32(), matrix.m33()
        )));
        final N d22 = m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m01(), matrix.m03(),
                matrix.m10(), matrix.m11(), matrix.m13(),
                matrix.m30(), matrix.m31(), matrix.m33()
        ));
        final N d23 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m01(), matrix.m02(),
                matrix.m10(), matrix.m11(), matrix.m12(),
                matrix.m30(), matrix.m31(), matrix.m32()
        )));
        final N d30 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m01(), matrix.m02(), matrix.m03(),
                matrix.m11(), matrix.m12(), matrix.m13(),
                matrix.m21(), matrix.m22(), matrix.m23()
        )));
        final N d31 = m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m02(), matrix.m03(),
                matrix.m10(), matrix.m12(), matrix.m13(),
                matrix.m20(), matrix.m22(), matrix.m23()
        ));
        final N d32 = ops.negated(m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m01(), matrix.m03(),
                matrix.m10(), matrix.m11(), matrix.m13(),
                matrix.m20(), matrix.m21(), matrix.m23()
        )));
        final N d33 = m3Ops.determinant(m3Ops.of(
                matrix.m00(), matrix.m01(), matrix.m02(),
                matrix.m10(), matrix.m11(), matrix.m12(),
                matrix.m20(), matrix.m21(), matrix.m22()
        ));
        return of(
                ops.multiply(d00, invDet), ops.multiply(d10, invDet), ops.multiply(d20, invDet), ops.multiply(d30, invDet),
                ops.multiply(d01, invDet), ops.multiply(d11, invDet), ops.multiply(d21, invDet), ops.multiply(d31, invDet),
                ops.multiply(d02, invDet), ops.multiply(d12, invDet), ops.multiply(d22, invDet), ops.multiply(d32, invDet),
                ops.multiply(d03, invDet), ops.multiply(d13, invDet), ops.multiply(d23, invDet), ops.multiply(d33, invDet)
        );
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
    default M multiply(M matrix, N scalar) {

        final var ops = scalarOps();

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

        final var ops = scalarOps();

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

        final var ops = scalarOps();

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

        final var ops = scalarOps();

        final N m00 = ops.add(
                ops.add(ops.multiply(op1.m00(), op2.m00()), ops.multiply(op1.m01(), op2.m10())),
                ops.add(ops.multiply(op1.m02(), op2.m20()), ops.multiply(op1.m03(), op2.m30()))
        );
        final N m01 = ops.add(
                ops.add(ops.multiply(op1.m00(), op2.m01()), ops.multiply(op1.m01(), op2.m11())),
                ops.add(ops.multiply(op1.m02(), op2.m21()), ops.multiply(op1.m03(), op2.m31()))
        );
        final N m02 = ops.add(
                ops.add(ops.multiply(op1.m00(), op2.m02()), ops.multiply(op1.m01(), op2.m12())),
                ops.add(ops.multiply(op1.m02(), op2.m22()), ops.multiply(op1.m03(), op2.m32()))
        );
        final N m03 = ops.add(
                ops.add(ops.multiply(op1.m00(), op2.m03()), ops.multiply(op1.m01(), op2.m13())),
                ops.add(ops.multiply(op1.m02(), op2.m23()), ops.multiply(op1.m03(), op2.m33()))
        );
        final N m10 = ops.add(
                ops.add(ops.multiply(op1.m10(), op2.m00()), ops.multiply(op1.m11(), op2.m10())),
                ops.add(ops.multiply(op1.m12(), op2.m20()), ops.multiply(op1.m13(), op2.m30()))
        );
        final N m11 = ops.add(
                ops.add(ops.multiply(op1.m10(), op2.m01()), ops.multiply(op1.m11(), op2.m11())),
                ops.add(ops.multiply(op1.m12(), op2.m21()), ops.multiply(op1.m13(), op2.m31()))
        );
        final N m12 = ops.add(
                ops.add(ops.multiply(op1.m10(), op2.m02()), ops.multiply(op1.m11(), op2.m12())),
                ops.add(ops.multiply(op1.m12(), op2.m22()), ops.multiply(op1.m13(), op2.m32()))
        );
        final N m13 = ops.add(
                ops.add(ops.multiply(op1.m10(), op2.m03()), ops.multiply(op1.m11(), op2.m13())),
                ops.add(ops.multiply(op1.m12(), op2.m23()), ops.multiply(op1.m13(), op2.m33()))
        );
        final N m20 = ops.add(
                ops.add(ops.multiply(op1.m20(), op2.m00()), ops.multiply(op1.m21(), op2.m10())),
                ops.add(ops.multiply(op1.m22(), op2.m20()), ops.multiply(op1.m23(), op2.m30()))
        );
        final N m21 = ops.add(
                ops.add(ops.multiply(op1.m20(), op2.m01()), ops.multiply(op1.m21(), op2.m11())),
                ops.add(ops.multiply(op1.m22(), op2.m21()), ops.multiply(op1.m23(), op2.m31()))
        );
        final N m22 = ops.add(
                ops.add(ops.multiply(op1.m20(), op2.m02()), ops.multiply(op1.m21(), op2.m12())),
                ops.add(ops.multiply(op1.m22(), op2.m22()), ops.multiply(op1.m23(), op2.m32()))
        );
        final N m23 = ops.add(
                ops.add(ops.multiply(op1.m20(), op2.m03()), ops.multiply(op1.m21(), op2.m13())),
                ops.add(ops.multiply(op1.m22(), op2.m23()), ops.multiply(op1.m23(), op2.m33()))
        );
        final N m30 = ops.add(
                ops.add(ops.multiply(op1.m30(), op2.m00()), ops.multiply(op1.m31(), op2.m10())),
                ops.add(ops.multiply(op1.m32(), op2.m20()), ops.multiply(op1.m33(), op2.m30()))
        );
        final N m31 = ops.add(
                ops.add(ops.multiply(op1.m30(), op2.m01()), ops.multiply(op1.m31(), op2.m11())),
                ops.add(ops.multiply(op1.m32(), op2.m21()), ops.multiply(op1.m33(), op2.m31()))
        );
        final N m32 = ops.add(
                ops.add(ops.multiply(op1.m30(), op2.m02()), ops.multiply(op1.m31(), op2.m12())),
                ops.add(ops.multiply(op1.m32(), op2.m22()), ops.multiply(op1.m33(), op2.m32()))
        );
        final N m33 = ops.add(
                ops.add(ops.multiply(op1.m30(), op2.m03()), ops.multiply(op1.m31(), op2.m13())),
                ops.add(ops.multiply(op1.m32(), op2.m23()), ops.multiply(op1.m33(), op2.m33()))
        );
        return of(
                m00, m01, m02, m03,
                m10, m11, m12, m13,
                m20, m21, m22, m23,
                m30, m31, m32, m33
        );
    }

    @Override
    default M remainder(M op1, M op2) {

        final var ops = scalarOps();

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

        final var ops = scalarOps();

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
}
