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

import org.lidiuma.math.api.vector.Vector1;

/// Operations for [Vector1].
public interface Vector1Ops<V extends Vector1<N>, N> extends VectorOps<V, N> {

    /// Constructs [V] using the provided scalars.
    V of(N x);

    @Override
    default N sum(V vector) {
        return vector.x();
    }

    // In 1D integers vector will always have an integer length.
    default N length(V vector) {
        return abs(vector).x();
    }

    // In 1D integers vector will always have an integer distance.
    default N distance(V v1, V v2) {
        final var witness = scalarOps();
        final N delta = witness.subtract(v1.x(), v2.x());
        return abs(of(delta)).x(); // A bit of a hack to use the abs method for the scalar.
    }

    @Override
    default V clamp(V vector, N min, N max) {
        return clamp(vector, of(min), of(max));
    }

    @Override
    default V clamp(V value, V min, V max) {
        final var ops = scalarOps();
        final var x = ops.max(min.x(), ops.min(value.x(), max.x()));
        return of(x);
    }

    @Override
    default V zero() {
        final var zero = scalarOps().zero();
        return of(zero);
    }

    @Override
    default V one() {
        final var one = scalarOps().one();
        return of(one);
    }

    @Override
    default V add(V op1, V op2) {
        final var witness = scalarOps();
        return of(witness.add(op1.x(), op2.x()));
    }

    @Override
    default V multiply(V op1, V op2) {
        final var witness = scalarOps();
        return of(witness.multiply(op1.x(), op2.x()));
    }

    @Override
    default V multiply(V vector, N scalar) {
        return multiply(vector, of(scalar));
    }

    @Override
    default V divide(V op1, V op2) {
        final var witness = scalarOps();
        return of(witness.divide(op1.x(), op2.x()));
    }

    @Override
    default V remainder(V op1, V op2) {
        final var witness = scalarOps();
        return of(witness.remainder(op1.x(), op2.x()));
    }

    @Override
    default V negated(V operand) {
        final var witness = scalarOps();
        return of(witness.negated(operand.x()));
    }
}
