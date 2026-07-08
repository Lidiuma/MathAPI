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

package org.lidiuma.math.api.traits.vector;

import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.vector.Vector4;

public interface FloatingVector4Ops<
        V extends Vector4<N>,
        A extends Angle<N>,
        N> extends Vector4Ops<V, N>, FloatingVectorOps<V, A, N> {

    @Override
    default V zero() {
        final N zero = scalarOps().zero();
        return of(zero, zero, zero, zero);
    }

    @Override
    default V one() {
        final N one = scalarOps().one();
        return of(one, one, one, one);
    }

    @Override
    default V sqrt(V operand) {
        final var witness = scalarOps();
        return of(
            witness.sqrt(operand.x()),
            witness.sqrt(operand.y()),
            witness.sqrt(operand.z()),
            witness.sqrt(operand.w())
        );
    }

    @Override
    default V ceil(V operand) {
        final var witness = scalarOps();
        return of(
            witness.ceil(operand.x()),
            witness.ceil(operand.y()),
            witness.ceil(operand.z()),
            witness.ceil(operand.w())
        );
    }

    @Override
    default V floor(V operand) {
        final var witness = scalarOps();
        return of(
            witness.floor(operand.x()),
            witness.floor(operand.y()),
            witness.floor(operand.z()),
            witness.floor(operand.w())
        );
    }

    @Override
    default boolean epsilonEquals(V v1, V v2, N epsilon) {
        final var abs = abs(subtract(v1, v2));
        return lessThanEqual(abs, of(epsilon, epsilon, epsilon, epsilon));
    }

    @Override
    default V signum(V vector) {
        final var witness = scalarOps();
        return of(
                witness.signum(vector.x()),
                witness.signum(vector.y()),
                witness.signum(vector.z()),
                witness.signum(vector.w())
        );
    }
}