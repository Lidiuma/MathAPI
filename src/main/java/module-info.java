import org.jspecify.annotations.NullMarked;

@NullMarked
module lidiuma.math.api {
    requires org.jspecify;
    exports org.lidiuma.math.api;
    exports org.lidiuma.math.api.tuple;
    exports org.lidiuma.math.api.rotation;
    exports org.lidiuma.math.api.vector;
    exports org.lidiuma.math.api.matrix;
    exports org.lidiuma.math.api.geometry;
    exports org.lidiuma.math.api.geometry.line;
    exports org.lidiuma.math.api.geometry.point;
    exports org.lidiuma.math.api.geometry.rectangle;
}