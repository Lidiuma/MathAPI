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

import org.lidiuma.math.api.rotation.Quaternion;
import org.lidiuma.math.api.vector.Vector3;

public interface Affine3Ops<
        A extends Affine3<N>,
        V extends Vector3<N>,
        Q extends Quaternion<N>,
        N> extends AffineOps<A, V, N> {

    /// @return the rotation part of this matrix.
    Q rotation(A affine);

    /// Transposes the 3x3 sub-matrix (linear part) of this affine matrix, ignoring the translation part.
    /// @return the transposed affine matrix.
    @Override
    A transpose(A affine);
}
