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

package org.lidiuma.math.api.geometry.line;

import org.lidiuma.math.api.geometry.point.Point1;
import org.lidiuma.math.api.vector.Vector1;

public interface Line1<N, F> extends Line<
        N, F,
        Point1<N, F>, Point1<F, F>,
        Vector1<N, F>, Vector1<F, F>> {
}
