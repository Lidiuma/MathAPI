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

import org.lidiuma.math.api.geometry.point.Point;
import java.util.function.UnaryOperator;

public interface Line<N, P extends Point<N, P, ?>> {

    P start();

    P end();

    default P interpolate(N alpha, UnaryOperator<N> easing) {
        return start().interpolate(end(), alpha, easing);
    }

    /// Linearly interpolates between `this` and `target`.
    /// @param alpha the alpha value in the range `[0,1]`.
    default P lerp(N alpha) {
        return interpolate(alpha, UnaryOperator.identity());
    }
}
