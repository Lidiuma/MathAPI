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

public interface Angle<N> {

    /*
    Implementations should implement factory methods to create the Radiant instance from at least ALL units present here.
    For example, I have the instance toDegree(), I must implement the static fromDegree().
     */

    N toRadian();

    N toDegree();

    N toTurn();

    @Deprecated
    interface F32 extends Angle<Float> {}

    @Deprecated
    interface F64 extends Angle<Double> {}
}
