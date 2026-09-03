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

package org.lidiuma.math.api.matrix;

/// Specialized [Matrix4] interface for 3D operations.
/// @param <N> the numeric type (e.g., [Integer], [Double]).
public interface Affine3<N> extends Matrix4<N> {

    /// Row 3, Column 0 accessor.
    /// @return Always returns 0.
    @Override
    N m30();

    /// Row 3, Column 1 accessor.
    /// @return Always returns 0.
    @Override
    N m31();

    /// Row 3, Column 2 accessor.
    /// @return Always returns 0.
    @Override
    N m32();

    /// Row 3, Column 3 accessor.
    /// @return Always returns 1.
    @Override
    N m33();
}
