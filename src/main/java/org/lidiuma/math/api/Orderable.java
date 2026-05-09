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

package org.lidiuma.math.api;

public interface Orderable<T> {

    /// @return true if `first` is less than `second`.
    boolean lessThan(T first, T second);

    /// @return true if `first` is less than or equal to `second`.
    boolean lessThanEqual(T first, T second);

    /// @return true if `first` is greater than `second`.
    boolean greaterThan(T first, T second);

    /// @return true if `first` is greater than or equal to `second`.
    boolean greaterThanEqual(T first, T second);

    /// @return the smaller number between `first` and `second`.
    T min(T first, T second);

    /// @return the bigger number between `first` and `second`.
    T max(T first, T second);
}
