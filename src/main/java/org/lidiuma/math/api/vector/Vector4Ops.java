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

public interface Vector4Ops<V extends Vector4<N>, N> extends VectorOps<V, N> {

    /// Constructs a vector using the provided scalars.
    V of(N x, N y, N z, N w);

    @Override
    default N sum(V vector) {
        final var witness = scalarWitness();
        final N xy = witness.add(vector.x(), vector.y());
        final N zw = witness.add(vector.z(), vector.w());
        return witness.add(xy, zw);
    }

    @Override
    default V multiply(V vector, N scalar) {
        return multiply(vector, of(scalar, scalar, scalar, scalar));
    }

    @Override
    default V clamp(V vector, N min, N max) {
        final V minV = of(min, min, min, min);
        final V maxV = of(max, max, max, max);
        return clamp(vector, minV, maxV);
    }
}
