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

package org.lidiuma.math.api.traits.point;

import org.lidiuma.math.api.point.Point1;
import org.lidiuma.math.api.traits.vector.FloatingVector1Ops;
import org.lidiuma.math.api.vector.Vector1;

public interface FloatingPoint1Ops<
        P extends Point1<N>,
        V extends Vector1<N>,
        N> extends FloatingPointOps<P, V, N> {

    P of(N x);

    // To avoid re-defining the same calculation twice,
    // I re-use the Vector math with the constraint of the vector used starting from the point [0,0].
    @Override
    FloatingVector1Ops<V, ?, N> vectorOps();

    @Override
    default N distance(P first, P second) {
        final var vOps = vectorOps();
        return vOps.distance(v(first), v(second));
    }

    // Conversion method
    private V v(P point) {
        return vectorOps().of(point.x());
    }
}
