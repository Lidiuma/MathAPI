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

    /// Returns the 2D cross product of `first` vector and the `second` vector.\
    /// The result is equivalent to the Z component of the 3D cross product.
    /// @return the scalar result of the cross product.
    default N cross(V first, V second) {
        final var witness = scalarWitness();
        final N a = witness.multiply(first.x(), second.y());
        final N b = witness.multiply(first.y(), second.x());
        return witness.subtract(a, b);
    }

    @Override
    default N sum(V vector) {
        final var witness = scalarWitness();
        return witness.add(vector.x(), vector.y());
    }

    @Override
    default V abs(V vector) {

        final var witness = scalarWitness();

        final V zero = zero();
        final N x = vector.x();
        final N y = vector.y();

        final N newX = witness.lessThan(x, zero.x()) ? witness.negated(x) : x;
        final N newY = witness.lessThan(y, zero.y()) ? witness.negated(y) : y;
        return of(newX, newY);
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
    default V min(V left, V right) {
        final var witness = scalarWitness();
        final N x = witness.min(left.x(), right.x());
        final N y = witness.min(left.y(), right.y());
        return of(x, y);
    }

    @Override
    default V max(V left, V right) {
        final var witness = scalarWitness();
        final N x = witness.max(left.x(), right.x());
        final N y = witness.max(left.y(), right.y());
        return of(x, y);
    }
}
