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

public interface Vector2Ops<V extends Vector2<N>, N> extends VectorOps<V, N> {

    /// Constructs a vector using the provided scalars.
    V of(N x, N y);

    /// Returns the 2D cross product of `v1` vector and the `v2` vector.\
    /// The result is equivalent to the Z component of the 3D cross product.
    /// @return the scalar result of the cross product.
    default N cross(V v1, V v2) {
        final var witness = scalarWitness();
        final N a = witness.multiply(v1.x(), v2.y());
        final N b = witness.multiply(v1.y(), v2.x());
        return witness.subtract(a, b);
    }

    @Override
    default N sum(V vector) {
        final var witness = scalarWitness();
        return witness.add(vector.x(), vector.y());
    }

    @Override
    default V multiply(V vector, N scalar) {
        return multiply(vector, of(scalar, scalar));
    }

    @Override
    default V clamp(V vector, N min, N max) {
        return clamp(vector, of(min, min), of(max, max));
    }

    @Override
    default V min(V op1, V op2) {
        final var witness = scalarWitness();
        final N x = witness.min(op1.x(), op2.x());
        final N y = witness.min(op1.y(), op2.y());
        return of(x, y);
    }

    @Override
    default V max(V op1, V op2) {
        final var witness = scalarWitness();
        final N x = witness.max(op1.x(), op2.x());
        final N y = witness.max(op1.y(), op2.y());
        return of(x, y);
    }


    @Override
    default V add(V op1, V op2) {
        final var witness = scalarWitness();
        return of(
                witness.add(op1.x(), op2.x()),
                witness.add(op1.y(), op2.y())
        );
    }

    @Override
    default V multiply(V op1, V op2) {
        final var witness = scalarWitness();
        return of(
                witness.multiply(op1.x(), op2.x()),
                witness.multiply(op1.y(), op2.y())
        );
    }

    @Override
    default V divide(V op1, V op2) {
        final var witness = scalarWitness();
        return of(
                witness.divide(op1.x(), op2.x()),
                witness.divide(op1.y(), op2.y())
        );
    }

    @Override
    default V remainder(V op1, V op2) {
        final var witness = scalarWitness();
        return of(
                witness.remainder(op1.x(), op2.x()),
                witness.remainder(op1.y(), op2.y())
        );
    }

    @Override
    default V negated(V operand) {
        final var witness = scalarWitness();
        return of(
                witness.negated(operand.x()),
                witness.negated(operand.y())
        );
    }
}
