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

import org.lidiuma.math.api.tuple.UnaryTuple2;
import org.lidiuma.math.api.vector.Vector2;

public interface Point2<N,
        P extends Point2<N, P, V>,
        V extends Vector2<N, V>> extends Point<N, P, V>, UnaryTuple2<N> {

    interface F32 extends Point2<Float, F32, Vector2.F32> {}

    interface F64 extends Point2<Double, F64, Vector2.F64> {}

    interface I32 extends Point2<Integer, I32, Vector2.I32> {}

    interface I64 extends Point2<Long, I64, Vector2.I64> {}
}
