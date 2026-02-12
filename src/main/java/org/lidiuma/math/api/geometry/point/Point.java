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

package org.lidiuma.math.api.geometry.point;

import org.lidiuma.math.api.tuple.UnaryTuple;
import org.lidiuma.math.api.vector.Vector;
import java.util.function.UnaryOperator;

public interface Point<
        N, F,
        P extends Point<N, F, P, PF, V, VF>, PF extends Point<F, F, PF, PF, VF, VF>,
        V extends Vector<N, F, V, VF>, VF extends Vector<F, F, VF, VF>> extends UnaryTuple<N> {

    P translate(V vector);

    V subtract(P point);

    N distance2(P point);

    P clamp(N min, N max);

    /* ========== Decimal-Only Operations ========== */

    F distance(PF point);

    F lerp(PF target, F alpha);

    F interpolate(PF target, F alpha, UnaryOperator<F> interpolator);
}
