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

/// Represents the Gimbal-lock pole of a quaternion when converted to Euler angles.
///
/// Gimbal lock occurs when the pitch reaches `±90°`, causing a loss of one degree of freedom.
/// In this state, yaw and roll become coupled.
public enum GimbalPole {

    /// Positive Gimbal-lock pole (+90° pitch).
    NORTH(1),
    /// Negative Gimbal=lock pole (-90° pitch).
    SOUTH(-1),
    /// No Gimbal-lock present.
    NONE(0);

    private final int sign;

    GimbalPole(int sign) {
        this.sign = sign;
    }

    /// Returns the pole sign:
    /// - `+1` for [#NORTH]
    /// - `-1` for [#SOUTH]
    /// - `0` for [#NONE]
    public int sign() {
        return sign;
    }
}
