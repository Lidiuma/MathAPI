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

import org.lidiuma.math.api.Numeric;

import java.util.function.UnaryOperator;

public interface Vector<V extends Vector<V, N>, N> extends Numeric<V>, UnaryOperator<V> {

    /// Sums the components of the vector together.
    N sum();

    V mul(N scalar);

    V abs();

    V max(V other);

    V min(V other);

    N distance2(V vector);

    N length2();

    N dot(V vector);

    V clamp(N min, N max);

    boolean hasSameDirection(V vector);

    boolean hasOppositeDirection(V vector);
}
