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

package org.lidiuma.math.api.geometry.triangle;

import org.lidiuma.math.api.vector.Vector;

/// Generic Triangle interface.
///
/// The triangle uses local coordinates, with `A` being the `origin`.
///
/// Diagram to illustrate the relative position of the points:
/// ```
///   |
///   | B
///   |/ \
/// --A---C----
///   |
/// A = (0,0)
///```
public interface Triangle<N, V extends Vector<N>> {

    /// @return the `origin `[Vector] which is always **zero**.
    V a();

    /// @return the [Vector] starting from the `origin` pointing towards `B`.
    V b();

    /// @return the [Vector] starting from the `origin` pointing towards `C`.
    V c();
}
