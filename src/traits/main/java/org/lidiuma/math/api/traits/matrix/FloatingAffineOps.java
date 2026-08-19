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

package org.lidiuma.math.api.traits.matrix;

import org.lidiuma.math.api.matrix.SquareMatrix;
import org.lidiuma.math.api.traits.vector.FloatingVectorOps;
import org.lidiuma.math.api.vector.Vector;

/// Common operations for Affine matrices specialized in floating numeric.
public interface FloatingAffineOps<
        M extends SquareMatrix<N>,
        V extends Vector<N>,
        R,
        N> extends AffineOps<M, V, N> {

    /// Creates a rotation affine matrix from the provided rotation.
    /// @param rotation how much rotation to apply.
    /// @return the rotation matrix.
    M fromRotation(R rotation);

    @Override
    FloatingVectorOps<V, N> vectorOps();
}
