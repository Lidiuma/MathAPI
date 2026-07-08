import org.jspecify.annotations.NullMarked;

@NullMarked
module lidiuma.math.api.traits {
    requires org.jspecify;
    requires transitive lidiuma.math.api;
    exports org.lidiuma.math.api.traits;
    exports org.lidiuma.math.api.traits.color;
    exports org.lidiuma.math.api.traits.matrix;
    exports org.lidiuma.math.api.traits.point;
    exports org.lidiuma.math.api.traits.rotation;
    exports org.lidiuma.math.api.traits.vector;
}