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

import org.lidiuma.math.api.vector.Vector3;

public interface FloatingVector3Ops<
        V extends Vector3<N>,
        N> extends Vector3Ops<V, N>, FloatingVectorOps<V, N> {

    @Override
    default V sqrt(V operand) {
        final var witness = scalarOps();
        return of(
            witness.sqrt(operand.x()),
            witness.sqrt(operand.y()),
            witness.sqrt(operand.z())
        );
    }

    @Override
    default V ceil(V operand) {
        final var witness = scalarOps();
        return of(
            witness.ceil(operand.x()),
            witness.ceil(operand.y()),
            witness.ceil(operand.z())
        );
    }

    @Override
    default V floor(V operand) {
        final var witness = scalarOps();
        return of(
            witness.floor(operand.x()),
            witness.floor(operand.y()),
            witness.floor(operand.z())
        );
    }

    @Override
    default boolean epsilonEquals(V v1, V v2, N epsilon) {
        final var ops = scalarOps();
        final var abs = abs(subtract(v1, v2));
        if (ops.greaterThan(abs.x(), epsilon)) return false;
        if (ops.greaterThan(abs.y(), epsilon)) return false;
        return ops.lessThanEqual(abs.z(), epsilon);
    }

    @Override
    default V signum(V vector) {
        final var witness = scalarOps();
        return of(
                witness.signum(vector.x()),
                witness.signum(vector.y()),
                witness.signum(vector.z())
        );
    }
}