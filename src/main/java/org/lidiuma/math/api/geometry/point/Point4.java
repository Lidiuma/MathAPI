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

import org.lidiuma.math.api.tuple.UnaryTuple4;
import org.lidiuma.math.api.vector.Vector4;

public interface Point4<N,
        P extends Point4<N, P, V>,
        V extends Vector4<N, V>> extends Point<N, P, V>, UnaryTuple4<N> {

    interface F32 extends Point4<Float, F32, Vector4.F32> {}

    interface F64 extends Point4<Double, F64, Vector4.F64> {}

    interface I32 extends Point4<Integer, I32, Vector4.I32> {}

    interface I64 extends Point4<Long, I64, Vector4.I64> {}
}
