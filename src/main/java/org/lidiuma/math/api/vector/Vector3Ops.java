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

public interface Vector3Ops<V extends Vector3<N>, N> extends VectorOps<V, N> {

    /// Constructs a vector using the provided scalars.
    V of(N x, N y, N z);

    /// Returns the cross product between `v1` vector and the `v2` vector.\
    /// The magnitude of the result is equal to `length(v1) * length(v2) * sin(theta)`, where theta is the angle between them.
    ///
    /// @return a vector perpendicular to both `v1` and `v2`.
    /// @apiNote the cross product is anti-commutative; `cross(v1, v2) = cross(-v2, v1)`.
    default V cross(V v1, V v2) {
        final var witness = scalarWitness();
        final N x = witness.subtract(
                witness.multiply(v1.y(), v2.z()),
                witness.multiply(v1.z(), v2.y())
        );
        final N y = witness.subtract(
                witness.multiply(v1.z(), v2.x()),
                witness.multiply(v1.x(), v2.z())
        );
        final N z = witness.subtract(
                witness.multiply(v1.x(), v2.y()),
                witness.multiply(v1.y(), v2.x())
        );
        return of(x, y, z);
    }

    @Override
    default N sum(V vector) {
        final var witness = scalarWitness();
        final N xy = witness.add(vector.x(), vector.y());
        return witness.add(xy, vector.z());
    }

    @Override
    default V multiply(V vector, N scalar) {
        return multiply(vector, of(scalar, scalar, scalar));
    }

    @Override
    default V clamp(V vector, N min, N max) {
        return clamp(vector, of(min, min, min), of(max, max, max));
    }
}
