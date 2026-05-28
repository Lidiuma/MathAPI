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

public interface Vector1Ops<V extends Vector1<N>, N> extends VectorOps<V, N> {

    /// Constructs a vector using the provided scalars.
    V of(N x);

    // In 1D integers vector will always have an integer length.
    N length(V vector);

    // In 1D integers vector will always have an integer distance.
    N distance(V v1, V v2);

    @Override
    default V add(V op1, V op2) {
        final var witness = scalarWitness();
        return of(witness.add(op1.x(), op2.x()));
    }

    @Override
    default V multiply(V op1, V op2) {
        final var witness = scalarWitness();
        return of(witness.multiply(op1.x(), op2.x()));
    }

    @Override
    default V divide(V op1, V op2) {
        final var witness = scalarWitness();
        return of(witness.divide(op1.x(), op2.x()));
    }

    @Override
    default V remainder(V op1, V op2) {
        final var witness = scalarWitness();
        return of(witness.remainder(op1.x(), op2.x()));
    }

    @Override
    default V negated(V operand) {
        final var witness = scalarWitness();
        return of(witness.negated(operand.x()));
    }
}
