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

package org.lidiuma.math.api.vector.impl;

import java.util.function.UnaryOperator;

public interface FVector<N, V extends FVector<N, V>> /* Cannot extend Vector without breaking things */ {

    V ceil();

    V floor();

    N length();

    V withLength(N length);

    V withLength2(N length2);

    V limit(N limit);

    V limit2(N limit2);

    V normalize();

    N distance(V vector);

    V lerp(V target, N alpha);

    V interpolate(V target, N alpha, UnaryOperator<N> interpolator);

    boolean isUnit(N epsilon);

    boolean isCollinear(V vector, N epsilon);

    boolean isPerpendicular(V vector, N epsilon);

    boolean epsilonEquals(V vector, N epsilon);

    boolean isZero(N epsilon);
}
