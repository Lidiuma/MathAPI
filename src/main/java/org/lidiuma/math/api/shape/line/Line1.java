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

package org.lidiuma.math.api.shape.line;

import org.lidiuma.math.api.point.Point1;
import org.lidiuma.math.api.vector.Vector1;

public interface Line1<N,
        P extends Point1<N, P, V>,
        V extends Vector1<N, V>> extends Line<N, P, V> {

    interface F32 extends Line1<Float, Point1.F32, Vector1.F32> {}

    interface F64 extends Line1<Double, Point1.F64, Vector1.F64> {}

    interface I32 extends Line1<Integer, Point1.I32, Vector1.I32> {}

    interface I64 extends Line1<Long, Point1.I64, Vector1.I64> {}
}
