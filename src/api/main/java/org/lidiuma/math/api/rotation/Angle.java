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

/// Angle interface representing the angle in a unit-agnostic way.
/// Values can be converted to/from radians, degrees, or turns.
/// @param <N> the numeric type (e.g., [Float], [Double]).
/// @implNote Most implementations should internally store `radians` since it is the standard unit for rotation, and all math libraries use it.
public interface Angle<N> {

    /// @return the angle in radians.
    N radian();

    /// @return the angle in degrees.
    N degree();

    /// @return the angle in turns. (1 turn = 360 degrees)
    N turn();
}
