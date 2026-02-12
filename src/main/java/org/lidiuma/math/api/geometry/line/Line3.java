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

package org.lidiuma.math.api.geometry.line;

import org.lidiuma.math.api.geometry.point.Point3;
import org.lidiuma.math.api.vector.Vector3;

public interface Line3<N, F> extends Line<
        N, F,
        Point3<N, F>, Point3<F, F>,
        Vector3<N, F>, Vector3<F, F>> {
}
