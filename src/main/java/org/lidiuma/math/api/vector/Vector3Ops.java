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

import org.lidiuma.math.api.tuple.UnaryTuple3;

public interface Vector3Ops<N> extends VectorOps<N, Vector3<N>, UnaryTuple3<N>> {

    /// Returns the cross product between `first` vector and the `other` vector.\
    /// The magnitude of the result is equal to `length() * other.length() * sin(theta)`, where theta is the angle between them.
    ///
    /// @return a vector perpendicular to both `first` and `other`.
    /// @apiNote the cross product is anti-commutative; `cross(first, other) = cross(-other, first)`.
    Vector3<N> cross(Vector3<N> first, Vector3<N> second);
}
