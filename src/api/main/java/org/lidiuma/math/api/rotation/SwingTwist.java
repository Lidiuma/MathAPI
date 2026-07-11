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

/// Represents the swing-twist decomposition of a rotation.
///
/// A rotation can be decomposed into:
/// - a *[#swing]* rotation that moves the axis into place.
/// - a *[#twist]* rotation around a given axis.
///
/// The original quaternion can be reconstructed by doing `swing * twist`.
public interface SwingTwist<Q extends Quaternion<N>, N> {

    /// @return the normalized swing component of the rotation.
    Q swing();

    /// @return the normalized twist component of the rotation.
    Q twist();
}
