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

import org.lidiuma.math.api.FloatingNumerical;
import org.lidiuma.math.api.vector.Vector;

public interface AffineOps<
        A extends SquareMatrix<N>,
        V extends Vector<N>,
        N> extends SquareMatrixOps<A, V, N> {

    /// @return the translation part of this matrix.
    V translation(A affine);

    /// @return the shearing component of this matrix.
    V shear(A affine);

    /// @return the scale components along each axis.
    V scale(A affine);

    @Override
    FloatingNumerical<N> scalarOps();
}
