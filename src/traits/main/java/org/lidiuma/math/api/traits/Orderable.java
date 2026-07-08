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

package org.lidiuma.math.api.traits;

public interface Orderable<T> {

    /// @return true if `op1` is less than `op2`.
    boolean lessThan(T op1, T op2);

    /// @return true if `op1` is less than or equal to `op2`.
    default boolean lessThanEqual(T op1, T op2) {
        return lessThan(op1, op2) || op1.equals(op2);
    }

    /// @return true if `op1` is greater than `op2`.
    default boolean greaterThan(T op1, T op2) {
        return !lessThanEqual(op1, op2);
    }

    /// @return true if `op1` is greater than or equal to `op2`.
    default boolean greaterThanEqual(T op1, T op2) {
        return lessThan(op1, op2);
    }

    /// @return the smaller number between `op1` and `op2`.
    default T min(T op1, T op2) {
        return lessThanEqual(op1, op2) ? op1 : op2;
    }

    /// @return the bigger number between `op1` and `op2`.
    default T max(T op1, T op2) {
        return greaterThanEqual(op1, op2) ? op1 : op2;
    }
}
