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

import org.lidiuma.math.api.point.Point2;
import org.lidiuma.math.api.traits.vector.Vector2Ops;
import org.lidiuma.math.api.vector.Vector2;

public interface Point2Ops<
        P extends Point2<N>,
        V extends Vector2<N>,
        N> extends PointOps<P, V, N> {

    P of(N x, N y);

    // To avoid re-defining the same calculation twice,
    // I re-use the Vector math with the constraint of the vector used starting from the point [0,0].
    @Override
    Vector2Ops<V, N> vectorOps();

    @Override
    default P add(P point, V vector) {
        final var vOps = vectorOps();
        return p(vOps.add(v(point), vector));
    }

    @Override
    default V subtract(P minuend, P subtrahend) {
        final var vOps = vectorOps();
        return vOps.subtract(v(minuend), v(subtrahend));
    }

    @Override
    default N distanceSquared(P first, P second) {
        final var vOps = vectorOps();
        return vOps.distanceSquared(v(first), v(second));
    }

    @Override
    default P clamp(P point, N min, N max) {
        final var vOps = vectorOps();
        return p(vOps.clamp(v(point), min, max));
    }

    /* Conversion methods */

    private P p(V vector) {
        return of(vector.x(), vector.y());
    }

    private V v(P point) {
        return vectorOps().of(point.x(), point.y());
    }
}
