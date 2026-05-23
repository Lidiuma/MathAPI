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

    /// @return true if `left` is less than `right`.
    boolean lessThan(T left, T right);

    /// @return true if `left` is less than or equal to `right`.
    default boolean lessThanEqual(T left, T right) {
        return lessThan(left, right) || left.equals(right);
    }

    /// @return true if `left` is greater than `right`.
    default boolean greaterThan(T left, T right) {
        return !lessThanEqual(left, right);
    }

    /// @return true if `left` is greater than or equal to `right`.
    default boolean greaterThanEqual(T left, T right) {
        return lessThan(left, right);
    }

    /// @return the smaller number between `left` and `right`.
    default T min(T left, T right) {
        return lessThanEqual(left, right) ? left : right;
    }

    /// @return the bigger number between `left` and `right`.
    default T max(T left, T right) {
        return greaterThanEqual(left, right) ? left : right;
    }
}
