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

package org.lidiuma.math.api.matrix;

import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.vector.Vector2;

public interface Affine2Ops<
        A extends Affine2<N>,
        V extends Vector2<N>,
        N> extends AffineOps<A, V, N> {

    /// @return the angle calculated from the rotation component of this matrix.
    Angle<N> rotation(A affine);
}
