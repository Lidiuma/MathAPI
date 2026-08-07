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

import org.lidiuma.math.api.point.Point3;
import org.lidiuma.math.api.traits.vector.FloatingVector3Ops;
import org.lidiuma.math.api.vector.Vector3;

public interface FloatingPoint3Ops<
        P extends Point3<N>,
        V extends Vector3<N>,
        N> extends Point3Ops<P, V, N>, FloatingPointOps<P, V, N> {

    // To avoid re-defining the same calculation twice,
    // I re-use the Vector math with the constraint of the vector used starting from the point [0,0].
    @Override
    FloatingVector3Ops<V, ?, N> vectorOps();

    @Override
    default N distance(P first, P second) {
        final var vOps = vectorOps();
        return vOps.distance(v(first), v(second));
    }

    // Conversion method
    private V v(P point) {
        return vectorOps().of(point.x(), point.y(), point.z());
    }
}
