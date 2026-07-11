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

import org.lidiuma.math.api.vector.Vector3;

/// Axis-Angle interface representing a rotation as an [axis][Vector3] and an [angle][Angle].
/// The rotation is around the given [axis][Vector3] by the specified [angle][Angle].
/// @apiNote The axis is expected to be normalized.
public interface AxisAngle<
        V extends Vector3<N>,
        A extends Angle<N>,
        N> {

    /// @return the normalized rotation axis.
    V axis();

    /// @return the rotation angle around the axis.
    A angle();
}
