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

public interface Numerical<T> {

    /// Addition operation, same as `+`.
    T add(T addend, T augend);

    /// Subtraction operation, same as `-`.
    T subtract(T minuend, T subtrahend);

    /// Multiplication operation, same as `*`.
    T multiply(T multiplier, T multiplicand);

    /// Division operation, same as `/`.
    T divide(T dividend, T divisor);

    /// Remainder operation, same as `%`.
    T remainder(T dividend, T divisor);

    /// Negation operation, same as `-` or multiplying by a scalar `-1`.
    T negated(T operand);
}
