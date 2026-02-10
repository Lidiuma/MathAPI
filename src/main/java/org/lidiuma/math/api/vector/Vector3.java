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

import org.lidiuma.math.api.tuple.UnaryTuple3;
import org.lidiuma.math.api.rotation.Angle;

public interface Vector3<N, V extends Vector3<N, V>> extends Vector<N, V>, UnaryTuple3<N> {

    V cross(V vector);

    interface Real<N,
            V extends Real<N, V, A>,
            A extends Angle<N>> extends Vector3<N, V> {

        V rotate(V axis, A angle);
    }

    interface F32 extends Real<Float, F32, Angle.F32> {}

    interface F64 extends Real<Double, F64, Angle.F64> {}

    interface I32 extends Vector3<Integer, I32> {}

    interface I64 extends Vector3<Long, I64> {}
}
