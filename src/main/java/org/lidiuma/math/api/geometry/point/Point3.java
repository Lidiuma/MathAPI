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

import org.lidiuma.math.api.tuple.UnaryTuple3;
import org.lidiuma.math.api.vector.Vector3;

public interface Point3<N,
        P extends Point3<N, P, V>,
        V extends Vector3<N, V>> extends Point<N, P, V>, UnaryTuple3<N> {

    interface F32 extends Point3<Float, F32, Vector3.F32> {}

    interface F64 extends Point3<Double, F64, Vector3.F64> {}

    interface I32 extends Point3<Integer, I32, Vector3.I32> {}

    interface I64 extends Point3<Long, I64, Vector3.I64> {}
}
