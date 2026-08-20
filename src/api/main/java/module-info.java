import org.jspecify.annotations.NullMarked;

@NullMarked
module lidiuma.math.api {
    requires static org.jspecify;
    exports org.lidiuma.math.api.tuple;
    exports org.lidiuma.math.api.rotation;
    exports org.lidiuma.math.api.vector;
    exports org.lidiuma.math.api.matrix;
    exports org.lidiuma.math.api.color;
    exports org.lidiuma.math.api.point;
    exports org.lidiuma.math.api.shapes.segment;
    exports org.lidiuma.math.api.shapes.rectangle;
    exports org.lidiuma.math.api.shapes.sphere;
    exports org.lidiuma.math.api.shapes.triangle;
}