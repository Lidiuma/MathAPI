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

package org.lidiuma.math.api.traits.numeric;

/// Indicates a type that supports basic arithmetic operations.
public interface Numerical<T> {

    T zero();

    T one();

    /// Addition operation, same as `+`.
    T add(T op1, T op2);

    /// Subtraction operation, same as `-`.
    default T subtract(T op1, T op2) {
        return add(op1, negated(op2));
    }

    /// Multiplication operation, same as `*`.
    T multiply(T op1, T op2);

    /// Division operation, same as `/`.
    T divide(T op1, T op2);

    /// Remainder operation, same as `%`.
    T remainder(T op1, T op2);

    /// Negation operation, same as `-` or multiplying by a scalar `-1`.
    T negated(T operand);
}
