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

package org.lidiuma.math.api.traits.vector;

import org.lidiuma.math.api.vector.Vector4;

public interface Vector4Ops<V extends Vector4<N>, N> extends VectorOps<V, N> {

    /// Constructs a vector using the provided scalars.
    V of(N x, N y, N z, N w);

    @Override
    default N sum(V vector) {
        final var witness = scalarOps();
        final N xy = witness.add(vector.x(), vector.y());
        final N zw = witness.add(vector.z(), vector.w());
        return witness.add(xy, zw);
    }

    @Override
    default V multiply(V vector, N scalar) {
        return multiply(vector, of(scalar, scalar, scalar, scalar));
    }

    @Override
    default V clamp(V vector, N min, N max) {
        final V minV = of(min, min, min, min);
        final V maxV = of(max, max, max, max);
        return clamp(vector, minV, maxV);
    }

    @Override
    default V clamp(V value, V min, V max) {
        final var ops = scalarOps();
        final var x = ops.max(min.x(), ops.min(value.x(), max.x()));
        final var y = ops.max(min.y(), ops.min(value.y(), max.y()));
        final var z = ops.max(min.z(), ops.min(value.z(), max.z()));
        final var w = ops.max(min.w(), ops.min(value.w(), max.w()));
        return of(x, y, z, w);
    }

    @Override
    default V add(V op1, V op2) {
        final var witness = scalarOps();
        return of(
               witness.add(op1.x(), op2.x()),
               witness.add(op1.y(), op2.y()),
               witness.add(op1.z(), op2.z()),
               witness.add(op1.w(), op2.w())
        );
    }

    @Override
    default V multiply(V op1, V op2) {
        final var witness = scalarOps();
        return of(
                witness.multiply(op1.x(), op2.x()),
                witness.multiply(op1.y(), op2.y()),
                witness.multiply(op1.z(), op2.z()),
                witness.multiply(op1.w(), op2.w())
        );
    }

    @Override
    default V divide(V op1, V op2) {
        final var witness = scalarOps();
        return of(
                witness.divide(op1.x(), op2.x()),
                witness.divide(op1.y(), op2.y()),
                witness.divide(op1.z(), op2.z()),
                witness.divide(op1.w(), op2.w())
        );
    }

    @Override
    default V remainder(V op1, V op2) {
        final var witness = scalarOps();
        return of(
                witness.remainder(op1.x(), op2.x()),
                witness.remainder(op1.y(), op2.y()),
                witness.remainder(op1.z(), op2.z()),
                witness.remainder(op1.w(), op2.w())
        );
    }

    @Override
    default V negated(V operand) {
        final var witness = scalarOps();
        return of(
                witness.negated(operand.x()),
                witness.negated(operand.y()),
                witness.negated(operand.z()),
                witness.negated(operand.w())
        );
    }
}
