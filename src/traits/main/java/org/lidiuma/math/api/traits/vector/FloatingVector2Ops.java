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

import org.lidiuma.math.api.vector.Vector2;

public interface FloatingVector2Ops<
        V extends Vector2<N>,
        N> extends Vector2Ops<V, N>, FloatingVectorOps<V, N> {

    @Override
    default V sqrt(V operand) {
        final var witness = scalarOps();
        return of(
            witness.sqrt(operand.x()),
            witness.sqrt(operand.y())
        );
    }

    @Override
    default V ceil(V operand) {
        final var witness = scalarOps();
        return of(
            witness.ceil(operand.x()),
            witness.ceil(operand.y())
        );
    }

    @Override
    default V floor(V operand) {
        final var witness = scalarOps();
        return of(
            witness.floor(operand.x()),
            witness.floor(operand.y())
        );
    }

    @Override
    default boolean epsilonEquals(V v1, V v2, N epsilon) {
        final var ops = scalarOps();
        final var abs = abs(subtract(v1, v2));
        if (ops.greaterThan(abs.x(), epsilon)) return false;
        return ops.lessThanEqual(abs.y(), epsilon);
    }

    @Override
    default V signum(V vector) {
        final var witness = scalarOps();
        return of(
                witness.signum(vector.x()),
                witness.signum(vector.y())
        );
    }
}
