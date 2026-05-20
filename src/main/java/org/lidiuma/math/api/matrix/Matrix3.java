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

import org.lidiuma.math.api.tuple.UnaryTuple3;

/// Generic Matrix3 interface.
public interface Matrix3<N> extends SquareMatrixOps<N, Matrix3<N>, UnaryTuple3<N>> {

    int SIZE = 9;

    @Override
    default int size() {
        return SIZE;
    }

    @Override
    default int rows() {
        return 3;
    }

    @Override
    default int columns() {
        return 3;
    }

    N m00();
    N m01();
    N m02();

    N m10();
    N m11();
    N m12();

    N m20();
    N m21();
    N m22();
}
