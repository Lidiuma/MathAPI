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

package org.lidiuma.math.api.traits.matrix;

import org.lidiuma.math.api.matrix.Matrix4;
import org.lidiuma.math.api.rotation.Angle;
import org.lidiuma.math.api.traits.vector.FloatingVector4Ops;
import org.lidiuma.math.api.vector.Vector4;

public interface FloatingMatrix4Ops<
        M extends Matrix4<N>,
        V extends Vector4<N>,
        A extends Angle<N>,
        N> extends Matrix4Ops<M, V, N> {

    /// Creates a perspective projection matrix with a field of view, an aspect ratio, and a near and far plane.
    /// @param fovY The field of view of the height.
    /// @param aspectRatio The aspect ratio.
    /// @param near The near plane.
    /// @param far The far plane.
    /// @apiNote Only the vertical FOV is specified, the horizontal FOV is derived from the aspect ratio.
    /// @throws IllegalArgumentException if `fovY` is not in the range `0 < fovY < π`, `aspectRatio <= 0`, `near <= 0`, or `far <= near`.
    M fromPerspective(A fovY, N aspectRatio, N near, N far);

    /// Creates an off-center perspective projection matrix.\
    /// Useful for asymmetric frustums (off-center projections), e.g., stereo rendering or shadows.
    /// @param left The X coordinate on the near plane that maps to the left of the viewport.
    /// @param right The X coordinate on the near plane that maps to the right of the viewport.
    /// @param bottom The Y coordinate on the near plane that maps to the bottom of the viewport.
    /// @param top The Y coordinate on the near plane that maps to the top of the viewport.
    /// @param near The distance to the near clipping plane (must be positive).
    /// @param far The distance to the far clipping plane (must be positive and greater than near).
    /// @return the projection matrix that maps the specified frustum to normalized device coordinates.
    /// @throws IllegalArgumentException if `right <= left`, `top <= bottom`, `near <= 0`, or `far <= near`.
    default M fromFrustum(N left, N right, N bottom, N top, N near, N far) {

        final var ops = scalarOps();

        if (ops.lessThanEqual(right, left)) throw new IllegalArgumentException("right must be greater than left.");
        if (ops.lessThanEqual(top, bottom)) throw new IllegalArgumentException("top must be greater than bottom.");
        if (ops.lessThanEqual(near, ops.zero())) throw new IllegalArgumentException("near must be positive.");
        if (ops.lessThanEqual(far, near)) throw new IllegalArgumentException("far must be greater than near.");

        final N zero = ops.zero();
        final N one = ops.one();
        final N two = ops.add(one, one); // Small hack to get 2.

        final N m00 = ops.divide(ops.multiply(two, near), ops.subtract(right, left)); // X offset.
        final N m11 = ops.divide(ops.multiply(two, near), ops.subtract(top, bottom)); // Y offset.
        final N m02 = ops.divide(ops.add(right, left), ops.subtract(right, left));
        final N m12 = ops.divide(ops.add(top, bottom), ops.subtract(top, bottom));
        final N m22 = ops.divide(ops.add(far, near), ops.subtract(near, far));
        final N m23 = ops.divide(ops.multiply(two, ops.multiply(far, near)), ops.subtract(near, far));
        return of(
                m00, zero, m02, zero,
                zero, m11, m12, zero,
                zero, zero, m22, m23,
                zero, zero, ops.negated(one), zero
        );
    }

    /// Creates an orthographic projection matrix.
    ///
    /// This matrix maps a 3D space defined by the clipping planes to normalized device coordinates
    /// (NDC), where the visible space is a cube ranging from [-1, 1] in all three axes. The projection
    /// is orthographic, meaning it does not account for perspective (parallel lines remain parallel).
    ///
    /// @param left   The X coordinate of the left clipping plane.
    /// @param right  The X coordinate of the right clipping plane (must be greater than left).
    /// @param bottom The Y coordinate of the bottom clipping plane.
    /// @param top    The Y coordinate of the top clipping plane (must be greater than bottom).
    /// @param near   The Z coordinate of the near clipping plane (must be less than far).
    /// @param far    The Z coordinate of the far clipping plane (must be greater than near).
    /// @return the orthographic projection matrix.
    /// @throws IllegalArgumentException if `right <= left`, `top <= bottom`, or `far <= near`.
    default M fromOrthographic(N left, N right, N bottom, N top, N near, N far) {

        final var ops = scalarOps();

        // These if statements do not impact performance since the method is only called once during camera creation.
        if (ops.lessThanEqual(right, left)) throw new IllegalArgumentException("right must be greater than left.");
        if (ops.lessThanEqual(top, bottom)) throw new IllegalArgumentException("top must be greater than bottom.");
        if (ops.lessThanEqual(far, near)) throw new IllegalArgumentException("far must be greater than near.");

        final N zero = ops.zero();
        final N one = ops.one();
        final N two = ops.add(one, one); // Small hack to get 2.

        // Orthographic coordinates.
        final N x = ops.divide(two, ops.subtract(right, left));
        final N y = ops.divide(two, ops.subtract(top, bottom));
        final N z = ops.divide(ops.negated(two), ops.subtract(far, near));

        final N tx = ops.divide(ops.negated(ops.add(right, left)), ops.subtract(right, left));
        final N ty = ops.divide(ops.negated(ops.add(top, bottom)), ops.subtract(top, bottom));
        final N tz = ops.divide(ops.negated(ops.add(far, near)), ops.subtract(far, near));

        return of(
                x, zero, zero, tx,
                zero, y, zero, ty,
                zero, zero, z, tz,
                zero, zero, zero, one
        );
    }

    /// Creates an orthographic projection matrix whose lower‑left corner is {@code origin},
    /// extending {@code width} horizontally and {@code height} vertically.
    /// @param width  horizontal size (must be positive)
    /// @param height vertical size (must be positive)
    /// @param near   The Z coordinate of the near clipping plane (must be less than far)
    /// @param far    The Z coordinate of the far clipping plane (must be greater than near)
    /// @return the 2D orthographic projection matrix.
    /// @throws IllegalArgumentException if `width <= 0`, `height <= 0`, or `far <= near`.
    // Implementations should add overload with Vector2 for origin. (I don't want to add another parameter)
    default M fromOrthographic2D(N originX, N originY, N width, N height, N near, N far) {
        final var ops = scalarOps();

        if (ops.lessThanEqual(width, ops.zero())) throw new IllegalArgumentException("width must be positive.");
        if (ops.lessThanEqual(height, ops.zero())) throw new IllegalArgumentException("height must be positive.");
        if (ops.lessThanEqual(far, near)) throw new IllegalArgumentException("far must be greater than near.");

        final var right = ops.add(originX, width);
        final var top = ops.add(originY, height);
        return fromOrthographic(originX, right, originY, top, near, far);
    }

    /// Creates an orthographic projection matrix whose lower‑left corner is {@code origin},
    /// extending {@code width} horizontally and {@code height} vertically.
    ///
    /// The `near` plane is set to 0, and the `far` plane is set to 1.
    /// @param width   horizontal size (must be positive)
    /// @param height  vertical size (must be positive)
    /// @return the 2D orthographic projection matrix.
    /// @throws IllegalArgumentException if `width <= 0`, or `height <= 0`.
    // Implementations should add overload with Vector2 for origin. (I don't want to add another parameter)
    default M fromOrthographic2D(N originX, N originY, N width, N height) {
        final var ops = scalarOps();
        return fromOrthographic2D(originX, originY, width, height, ops.zero(), ops.one());
    }

    @Override
    FloatingVector4Ops<V, A, N> vectorOps();
}
