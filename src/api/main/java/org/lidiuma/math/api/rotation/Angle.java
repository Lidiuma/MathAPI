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

package org.lidiuma.math.api.rotation;

import org.lidiuma.math.api.vector.Vector2;

/// Angle interface representing the angle in a unit-agnostic way.\
/// The implementation can decide the best unit to save internally,
/// and then declare conversion from/to other units.
/// @param <N> the numeric type (e.g., [Float], [Double]).
public interface Angle<N> {

    /// @return the angle in radians.
    N radians();

    /// @return the angle in degrees.
    N degrees();

    /// @return the angle in turns. (1 turn = 360 degrees)
    N turns();

    /// @return the angle as a unit vector.
    Vector2<N> vector();
}
