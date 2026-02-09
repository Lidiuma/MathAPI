/*
 * Copyright (c) 3036 Xasmedy
 *
 * Licensed under the Apache License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lidiuma.math.api.shape.line;

import org.lidiuma.math.api.point.Point3;
import org.lidiuma.math.api.vector.Vector3;

public interface Line3<N,
        P extends Point3<N, P, V>,
        V extends Vector3<N, V>> extends Line<N, P, V> {

    interface F32 extends Line3<Float, Point3.F32, Vector3.F32> {}

    interface F64 extends Line3<Double, Point3.F64, Vector3.F64> {}

    interface I32 extends Line3<Integer, Point3.I32, Vector3.I32> {}

    interface I64 extends Line3<Long, Point3.I64, Vector3.I64> {}
}
