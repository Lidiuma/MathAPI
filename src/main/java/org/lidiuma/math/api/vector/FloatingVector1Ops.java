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

package org.lidiuma.math.api.vector;

import org.lidiuma.math.api.rotation.Angle;

public interface FloatingVector1Ops<
        V extends Vector1<N>,
        A extends Angle<N>,
        N> extends Vector1Ops<V, N>, FloatingVectorOps<V, A, N> {

    @Override
    default V zero() {
        return of(scalarWitness().zero());
    }

    @Override
    default V one() {
        return of(scalarWitness().one());
    }

    @Override
    default V sqrt(V operand) {
        return of(scalarWitness().sqrt(operand.x()));
    }

    @Override
    default V ceil(V operand) {
        return of(scalarWitness().ceil(operand.x()));
    }

    @Override
    default V floor(V operand) {
        return of(scalarWitness().floor(operand.x()));
    }

    @Override
    default boolean epsilonEquals(V v1, V v2, N epsilon) {
        final var abs = abs(subtract(v1, v2));
        return lessThanEqual(abs, of(epsilon));
    }

    @Override
    default V signum(V vector) {
        return of(scalarWitness().signum(vector.x()));
    }

    /* Overrides to make the compiler happy and to use the more performance friendly version */

    @Override
    default N distance(V v1, V v2) {
        return Vector1Ops.super.distance(v1, v2);
    }

    @Override
    default N length(V vector) {
        return Vector1Ops.super.length(vector);
    }
}
