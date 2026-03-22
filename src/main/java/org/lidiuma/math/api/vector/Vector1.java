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

import org.lidiuma.math.api.tuple.UnaryTuple1;

/// Generic Vector 1D interface.
public interface Vector1<N> extends Vector<N, Vector1<N>>, UnaryTuple1<N> {

    /// @return a vector with a component-wise clamp between `min` and `max`.
    Vector1<N> clamp(UnaryTuple1<N> min, UnaryTuple1<N> max);

    @Override
    N length(); // In 1D integers vector will always have an integer length.

    @Override
    N distance(Vector1<N> vector); // In 1D integers vector will always have an integer distance.
}
