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

package org.lidiuma.math.api.geometry.line;

import org.lidiuma.math.api.geometry.point.Point4;
import org.lidiuma.math.api.vector.Vector4;

public interface Line4<N,
        P extends Point4<N, P, V>,
        V extends Vector4<N, V>> extends Line<N, P, V> {

    interface F32 extends Line4<Float, Point4.F32, Vector4.F32> {}

    interface F64 extends Line4<Double, Point4.F64, Vector4.F64> {}

    interface I32 extends Line4<Integer, Point4.I32, Vector4.I32> {}

    interface I64 extends Line4<Long, Point4.I64, Vector4.I64> {}
}